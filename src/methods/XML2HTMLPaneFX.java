package methods;


import static javafx.concurrent.Worker.State.FAILED;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import javafx.application.*;
import javafx.beans.value.*;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import methods.implementation.BEASTObjectMethodsText;

import netscape.javascript.JSObject;
import javafx.concurrent.Worker.State;

import org.tbee.javafx.scene.layout.MigPane;

import beast.app.beauti.BeautiConfig;
import beast.app.beauti.BeautiDoc;
import beast.app.beauti.InputFilter;
import beast.core.BEASTInterface;
import beast.core.MCMC;
import beast.util.XMLParser;
import beast.util.XMLProducer;


public class XML2HTMLPaneFX extends Application {

	private WebEngine engine;
	double zoom = 1.0;
	
	BeautiDoc beautiDoc;
	String html;
	List<Phrase> m;
	XML2Text xml2textProducer;
	
	File tmpFile = null;

	XML2HTMLPaneFX thisPane;
	ModelEditor me = new ModelEditor(false);
	Stage mainStage;

	public XML2HTMLPaneFX() {
		thisPane = this;
	}

	public void processArgs(String [] args) throws Exception {		
		beautiDoc = new BeautiDoc();
		File file = new File(args[0]);
		beautiDoc.setFileName(file.getAbsolutePath());
		beautiDoc.beautiConfig = new BeautiConfig();
		beautiDoc.beautiConfig.initAndValidate();		
		String xml = BeautiDoc.load(file);
		int i = xml.indexOf("beautitemplate=");
		if (i > 0) {
			i += 15;
			char c = xml.charAt(i);
			i++;
			int start = i;
			while (xml.charAt(i) != c) {
				i++;
			}
			String template = xml.substring(start, i);
			if (!template.endsWith("xml")) {
				template = template + ".xml";
			}
			beautiDoc.loadNewTemplate(template);
		} else {
			beautiDoc.loadNewTemplate("Standard.xml");
		}
		
		XMLParser parser = new XMLParser();
		MCMC mcmc = (MCMC) parser.parseFile(file);
		beautiDoc.mcmc.setValue(mcmc, beautiDoc);
		for (BEASTInterface o : InputFilter.getDocumentObjects(beautiDoc.mcmc.get())) {
			beautiDoc.registerPlugin(o);
		}
		beautiDoc.determinePartitions();
		BEASTObjectMethodsText.setBeautiCFG(beautiDoc.beautiConfig);
		
		MethodsText.initNameMap();
		initialise((MCMC) beautiDoc.mcmc.get(), true);		
	}
	
	@Override
	public void start(javafx.stage.Stage stage) throws Exception {
		mainStage = stage;
		WebView view = new WebView();
		view.setPrefHeight(1024);
		view.setContextMenuEnabled(false);
		engine = view.getEngine();

		engine.locationProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String oldValue, final String newValue) {
				System.out.println("changed:");
				System.out.println(newValue);
				if (me.handleCmd(newValue, beautiDoc, null)) {
					beautiDoc.determinePartitions();
					beautiDoc.scrubAll(false, false);
					CitationPhrase.citations.clear();

					MethodsText.clear();
					try {
						initialise((MCMC) beautiDoc.mcmc.get(), false);
						load(html);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		engine.getLoadWorker().exceptionProperty().addListener(new ChangeListener<Throwable>() {

			@Override
			public void changed(ObservableValue<? extends Throwable> o, Throwable old, final Throwable value) {
				if (engine.getLoadWorker().getState() == FAILED) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Loading error...");
					alert.setContentText((value != null) ? engine.getLocation() + "\n" + value.getMessage()
					: engine.getLocation() + "\nUnexpected error.");

					alert.showAndWait();
				}
			}
		});

		
		 // process page loading
        engine.getLoadWorker().stateProperty().addListener(
            (ObservableValue<? extends State> ov, State oldState, 
                State newState) -> {
                    if (newState == State.SUCCEEDED) {
                        JSObject win
                                = (JSObject) engine.executeScript("window");
                        win.setMember("myObject", thisPane);// new MyObject());
                    }
        });
        
		view.setOnKeyReleased((KeyEvent e) -> {
			System.out.println(e);
			if (e.getText().equals("-")) {
				zoomOut();
			}
			if (e.getText().equals("+")) {
				zoomIn();
			}
		});
		
		MigPane pane = new MigPane("","[grow]","[grow][shrink]");
		pane.add(view, "cell 0 0 2 1");
		Button copyButton = new Button("Export");
		pane.add(copyButton, "cell 0 1");
		copyButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
			public void handle(javafx.event.ActionEvent event) {

				ChoiceDialog<CitationPhrase.mode> dialog = new ChoiceDialog<>(CitationPhrase.CitationMode, 
						CitationPhrase.mode.values());
				dialog.setTitle("Export Dialog");
				dialog.setHeaderText("Export methods section");
				dialog.setContentText("Choose format:");

				// Traditional way to get the response value.
				Optional<CitationPhrase.mode> result = dialog.showAndWait();
				if (result.isPresent()){
				    System.out.println("Your choice: " + result.get());
					CitationPhrase.mode mode = result.get();
					StringSelection stringSelection;
					try {
						stringSelection = new StringSelection(getText(mode));
						Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
						clipboard.setContents(stringSelection, null);				
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		Button saveButton = new Button("Save");
		pane.add(saveButton, "cell 1 1");
		saveButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
			public void handle(javafx.event.ActionEvent event) {

				 FileChooser fileChooser = new FileChooser();
				 fileChooser.setTitle("Open Resource File");
				 fileChooser.getExtensionFilters().addAll(
				         new FileChooser.ExtensionFilter("XML Files", "*.xml"),
				         new FileChooser.ExtensionFilter("All Files", "*.*"));
				 File selectedFile = fileChooser.showSaveDialog(mainStage);
				 if (selectedFile != null) {
				    XMLProducer producer = new XMLProducer();
				    String xml = producer.toXML(beautiDoc.mcmc.get());
					try {
				        FileWriter outfile = new FileWriter(selectedFile.getPath());
				        outfile.write(xml);
				        outfile.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
			}
		});
				
		Scene scene = new Scene(pane, 768, 668);
		stage.setScene(scene);
		stage.show();
		
		List<String> args = getParameters().getRaw();
		processArgs(args.toArray(new String[]{}));
	};


	final static String header = "<!DOCTYPE html>\n" +
			"<html>\n" +
			"<style>\n" +
			".reference {font-size:10pt;color:#aaa;}\n" +
			".tipdates {display:inline;}\n" +
			"a{color:#555;text-decoration:none;background-color:#fafafa;}\n" + 
			".pe {color:#555;background-color:#fafafa;}\n" + 
			".para {color:#555;background-color:#fafafa;}\n" + 
			"select{color:#555;font-weight:normal;-webkit-appearance:none;background-color:#fafafa;border-width:5pt;}\n" + 
			"a:hover{background-color:#aaa;}\n" + 
			"select:hover{background-color:#aaa;}\n" + 
			"</style>\n" +
			"<body style='font: 12pt arial, sans-serif;'>"
			//+ "<input type='button' onclick='window.myObject.doIt(\"ok\");' value='Click me'/>\n"
			;
	
	public void initialise(MCMC mcmc, boolean update) throws Exception {		
		xml2textProducer = new XML2Text(beautiDoc);
		xml2textProducer.initialise((MCMC) beautiDoc.mcmc.get());
		m = xml2textProducer.getPhrases();
		
		html = header + Phrase.toHTML(beautiDoc, m) + "</body>\n</html>";
		
        if (update) {
        	updateState(html);
        } else {
        	load(html);
        }

        FileWriter outfile = new FileWriter("/tmp/index.html");
        outfile.write(html);
        outfile.close();
	}

	
	/**
	 * change html text and enable/disable buttons (where appropriate) *
	 */
	void updateState(Object page) {
		if (page instanceof String) {

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					load((String) page);
					//engine.loadContent((String) page);
				}

			});
			zoom(zoom);
		} else if (page instanceof URL) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					String tmp = null;
					try {
						tmp =  new URL("" + page).toExternalForm();
					} catch (MalformedURLException e) {
						try {
							tmp =  new URL("http://" + page).toExternalForm();
						} catch (MalformedURLException e2) {
							return;
						}
					}

					engine.load(tmp);
				}
			});
			zoom(zoom);
		}
	} // updateState

	public void load(String page) {
		try {
			if (tmpFile == null) {
				tmpFile = File.createTempFile("index", ".html");
				System.err.println(tmpFile.getPath());
			}
			FileWriter outfile = new FileWriter(tmpFile);
			outfile.write(html);
			outfile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// the following does not appear to be
		// able the handle select.onchanged events
		// but loading from file does -- not sure why
//		engine.loadContent((String)page, "text/html");
//		engine.setJavaScriptEnabled(true);
		engine.load("file://" + tmpFile);
	}

	
	private void zoomIn() {
		zoom(zoom * 1.1);
	}

	private void zoomOut() {
		zoom(zoom / 1.1);

	}

	void zoom(double _zoom) {
		this.zoom = _zoom;

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				engine.executeScript("document.body.style.zoom=" + zoom);
			}
		});
	}
	
	public String getText(CitationPhrase.mode mode) throws Exception {		
		CitationPhrase.CitationMode = mode;
		xml2textProducer = new XML2Text(beautiDoc);
		xml2textProducer.initialise((MCMC) beautiDoc.mcmc.get());
		m = xml2textProducer.getPhrases();
		return Phrase.toString(m);
	}

    public void doIt(String str) {
        System.out.println("doIt(" + str + ") called");
		if (me.handleCmd("/cmd=" + str, beautiDoc, null)) {
			beautiDoc.determinePartitions();
			beautiDoc.scrubAll(false, false);
			CitationPhrase.citations.clear();

			MethodsText.clear();
			try {
				initialise((MCMC) beautiDoc.mcmc.get(), false);
				load(html);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

    }
    
    public void doIt(String str, String source) {
        System.out.println("doIt(" + str + ") called with source = " + source);
        me.handleCmd("/cmd=Text value=\""+str+"\" source=\"" + source + "\"", beautiDoc, null);
    }

    public static void main(String[] args) throws Exception {		
		launch(args);
	}

}
