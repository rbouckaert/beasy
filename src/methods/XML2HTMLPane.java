package methods;


import static javafx.concurrent.Worker.State.FAILED;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javafx.application.*;
import javafx.beans.value.*;
import javafx.collections.*;
import javafx.concurrent.*;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.web.*;
import methods.implementation.BEASTObjectMethodsText;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventListener;
import org.xml.sax.SAXException;

import beast.app.beauti.BeautiConfig;
import beast.app.beauti.BeautiDoc;
import beast.app.beauti.InputFilter;
import beast.core.BEASTInterface;
import beast.core.MCMC;
import beast.util.XMLParser;
import beast.util.XMLParserException;


public class XML2HTMLPane extends JPanel {
	private static final long serialVersionUID = 1L;

	private final JFXPanel jfxPanel = new JFXPanel();
	private WebEngine engine;
	private final JPanel panel = new JPanel(new BorderLayout());
	double zoom = 1.0;

	
	BeautiDoc beautiDoc;
	String html;
	List<Phrase> m;
	XML2Text xml2textProducer;

	
	public XML2HTMLPane(String [] args) throws Exception {
		this();
		
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
		initialise((MCMC) beautiDoc.mcmc.get());
	}
	
	final static String header = "<!DOCTYPE html>\n" +
			"<html>\n" +
			"<style>\n" +
			".tooltip {\n" +
			"  position: relative;\n" +
//			"  display: inline-block;\n" +
//			"  border-bottom: 1px dotted black;\n" +
			"}\n" +
			"\n" +
			".tooltip .tooltiptext {\n" +
			"  visibility: hidden;\n" +
			"  width: 120px;\n" +
			"  background-color: #555;\n" +
			"  color: #fff;\n" +
			"  text-align: center;\n" +
			"  border-radius: 6px;\n" +
			"  padding: 5px 0;\n" +
			"  position: absolute;\n" +
			"  z-index: 1;\n" +
			"  bottom: 125%;\n" +
			"  left: 50%;\n" +
			"  margin-left: -60px;\n" +
			"  opacity: 0;\n" +
			"  transition: opacity 0.3s;\n" +
			"}\n" +
			"\n" +
			".tooltip:hover .tooltiptext {\n" +
			"  visibility: visible;\n" +
			"  opacity: 1;\n" +
			"}\n" +
			"</style>\n<body>\n";
	
	public void initialise(MCMC mcmc) throws Exception {		
		xml2textProducer = new XML2Text(beautiDoc);
		xml2textProducer.initialise((MCMC) beautiDoc.mcmc.get());
		m = xml2textProducer.getPhrases();
		
		html = header + Phrase.toHTML(beautiDoc, m) + "</body>\n</html>";
		
        FileWriter outfile = new FileWriter("/tmp/index.html");
        outfile.write(html);
        outfile.close();
		
		updateState(html);
	}

	
	public XML2HTMLPane() {
		setLayout(new BorderLayout());

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				initComponents();
			}
		});

		JScrollPane scroller = new JScrollPane(panel);
		add(scroller, BorderLayout.CENTER);

		try {
			updateState(new URL("file://" + "/Users/remco/workspace/beasy/src/methods/index.html"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	} // c'tor

	private void initComponents() {
		createScene();

		JPanel statusBar = new JPanel(new BorderLayout(5, 0));
		statusBar.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));

		panel.add(jfxPanel, BorderLayout.CENTER);
		panel.add(statusBar, BorderLayout.SOUTH);

		jfxPanel.setPreferredSize(new Dimension(400, 400));
		setPreferredSize(new Dimension(400, 400));
	}

	private void createScene() {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				WebView view = new WebView();
				engine = view.getEngine();

				engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
					@Override
					public void changed(ObservableValue ov, State oldState, State newState) {
						if (newState == Worker.State.SUCCEEDED) {
							// note next classes are from org.w3c.dom domain
							EventListener listener = new EventListener() {
								@Override
								public void handleEvent(org.w3c.dom.events.Event evt) {
									String href = ((Element) evt.getTarget()).getAttribute("href");
									System.out.println("link:" + href);
									// goToLink(href);
								}
							};

							org.w3c.dom.Document doc = engine.getDocument();
							Element el = doc.getElementById("a");
							NodeList lista = doc.getElementsByTagName("a");
							for (int i = 0; i < lista.getLength(); i++)
								((org.w3c.dom.events.EventTarget) lista.item(i)).addEventListener("click", listener,
										false);
						}
					}
				});
				engine.titleProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							final String newValue) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								// System.out.println(newValue);
							}
						});
					}
				});

				engine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
					@Override
					public void handle(final WebEvent<String> event) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								System.out.println(event.getData());
							}
						});
					}
				});

				engine.locationProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> ov, String oldValue, final String newValue) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								System.out.println("changed:");
								System.out.println(newValue);
							}
						});
					}
				});

				engine.getLoadWorker().exceptionProperty().addListener(new ChangeListener<Throwable>() {

					@Override
					public void changed(ObservableValue<? extends Throwable> o, Throwable old, final Throwable value) {
						if (engine.getLoadWorker().getState() == FAILED) {
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									JOptionPane.showMessageDialog(panel,
											(value != null) ? engine.getLocation() + "\n" + value.getMessage()
													: engine.getLocation() + "\nUnexpected error.",
											"Loading error...", JOptionPane.ERROR_MESSAGE);
								}
							});
						}
					}
				});

				jfxPanel.setScene(new Scene(view));
				jfxPanel.addKeyListener(new KeyListener() {

					@Override
					public void keyTyped(java.awt.event.KeyEvent e) {
					}

					@Override
					public void keyReleased(java.awt.event.KeyEvent e) {
						System.out.println(e);
						if (e.getKeyChar() == '-') {
							zoomOut();
						}
						if (e.getKeyChar() == '+') {
							zoomIn();
						}

					}

					@Override
					public void keyPressed(java.awt.event.KeyEvent e) {
					}
				});

			}
		});
	}


	/**
	 * change html text and enable/disable buttons (where appropriate) *
	 */
	void updateState(Object page) {
		if (page instanceof String) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					engine.loadContent(page.toString());
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

	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame();
		frame.setSize(700, 500);
		frame.add(new XML2HTMLPane(args));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
