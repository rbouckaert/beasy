package methods;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintStream;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledDocument;

import beast.app.beauti.AlignmentListInputEditor;
import beast.app.beauti.BeautiConfig;
import beast.app.beauti.BeautiDoc;
import beast.app.beauti.BeautiPanelConfig;
import beast.app.beauti.BeautiSubTemplate;
import beast.app.beauti.InputFilter;
import beast.app.draw.BEASTObjectDialog;
import beast.app.draw.InputEditor;
import beast.app.draw.InputEditor.ExpandOption;
import beast.core.BEASTInterface;
import beast.core.Description;
import beast.core.Input;
import beast.core.parameter.RealParameter;
import beast.util.XMLParser;
import methods.CitationPhrase.mode;
import methods.implementation.BEASTObjectMethodsText;
import beast.core.MCMC;

@Description("Convert MCMC analysis in XML file to a methods section")
public class XML2TextPane extends JTextPane implements ActionListener {
	private static final long serialVersionUID = 1L;

	BeautiDoc beautiDoc;
	String text;
	
	List<Phrase> m;
	
	XML2Text xml2textProducer;
	
	public XML2TextPane(String [] args) throws Exception {
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
		
		
		if (args.length > 1) {
			PrintStream out = new PrintStream(args[1]);
			out.print(text);
			out.close();
			System.exit(0);
		}
	}
	
	
	private void refreshText()  throws Exception {
//		XMLProducer p = new XMLProducer();
//		String xml = p.toXML(beautiDoc.mcmc.get());
//		PrintStream ps = new PrintStream(new File("/tmp/beast-raw.xml"));
//		ps.println(xml);
//		ps.close();
//		
//		beautiDoc.save("/tmp/beast.xml");		
		beautiDoc.determinePartitions();
		beautiDoc.scrubAll(false, false);
		CitationPhrase.citations.clear();

		StyledDocument doc = getStyledDocument();
		doc.remove(0, doc.getLength());
		MethodsText.clear();
		text = xml2textProducer.initialise((MCMC) beautiDoc.mcmc.get());
		m = xml2textProducer.getPhrases();
        Phrase.addTextToDocument(getStyledDocument(), this, beautiDoc, m);
	}
	
	public void initialise(MCMC mcmc) throws Exception {		
		xml2textProducer = new XML2Text(beautiDoc);
		text = xml2textProducer.initialise((MCMC) beautiDoc.mcmc.get());
		m = xml2textProducer.getPhrases();
        Phrase.addTextToDocument(getStyledDocument(), this, beautiDoc, m);
	}

	@Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() instanceof JButton) {
    		handleButton(e);
    	} if (e.getSource() instanceof JComboBox) {
    		handleComboBox(e);
    	} else if (e.getSource() instanceof JTextField) {
    		handleTextFeild(e);
    		
    	}
    }

	private void handleButton(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.startsWith("PartitionEditor")) {
			editPartition(e);
		} else if (cmd.startsWith("RealParameter")) {
			editRealParameter(e);
		} else if (cmd.startsWith("CitationPhrase")) {
			showCitation(e);
		}
		
	}

	private void showCitation(ActionEvent e) {
		String cmd = e.getActionCommand();
		String citation = cmd.substring(cmd.indexOf(' ') + 1);
    	JTextArea textArea = new JTextArea(citation);
    	textArea.setLineWrap(true);
    	textArea.setRows(5);
    	textArea.setColumns(50);
    	textArea.setEditable(true);
    	JScrollPane scroller = new JScrollPane(textArea);
    	JOptionPane.showMessageDialog(this, scroller);
	}


	private void editRealParameter(ActionEvent e) {
		String cmd = e.getActionCommand();
		String id = cmd.split(" ")[1];
		RealParameter param = (RealParameter) beautiDoc.pluginmap.get(id);
		
		List<Phrase> set = new ArrayList<>(); 
		set.addAll(MethodsText.partitionGroupMap.get(param));
		if (set.size() > 1) {
			String [] options = new String[set.size() + 1];
			int i = 0;
			options[i++] = "All";
			for (Phrase p : set) {
				options[i++] = ((RealParameter) p.source).getID();
			}
			i = JOptionPane.showOptionDialog(this, "Which parameter?", null, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "All");
			if (i > 0) {
				Phrase x = set.get(i-1);
				set.clear();
				set.add(x);
				param = (RealParameter) beautiDoc.pluginmap.get(options[i]);
			}
		}
		
        BEASTObjectDialog dlg = new BEASTObjectDialog(param, RealParameter.class, beautiDoc);
        if (dlg.showDialog()) {
        	for (Phrase p : set) {
        		param = (RealParameter) p.source;
                String id2 = param.getID();
        		dlg.accept((BEASTInterface) param, beautiDoc);
        		param.setID(id2);
        	}
            try {
            	refreshText();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        }		
	}


	private void editPartition(ActionEvent e) {
		BeautiPanelConfig config = new BeautiPanelConfig();
		config.initByName("path","distribution/distribution[id=\"likelihood\"]/distribution/data",
				"panelname", "Partitions", "tiptext", "Data Partitions",
	            "hasPartitions", "none", "forceExpansion", "FALSE",
	            "type", "beast.evolution.alignment.Alignment"    				
				);
		final Input<?> input = config.resolveInput(beautiDoc, 0);    		
		AlignmentListInputEditor ie = new AlignmentListInputEditor(beautiDoc);
		ie.init(input, config, -1, ExpandOption.FALSE, false);
        ((JComponent) ie).setBorder(BorderFactory.createEmptyBorder());
        ie.getComponent().setVisible(true);
        JOptionPane optionPane = new JOptionPane(ie,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION,
                null,
                new String[]{"OK"},
                "OK");
        optionPane.setBorder(new EmptyBorder(12, 12, 12, 12));

        final JDialog dialog = optionPane.createDialog(this, "Partition panel");
        dialog.setResizable(true);
        dialog.pack();

        dialog.setVisible(true);
        
        try {
			refreshText();
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
	}


	private void handleComboBox(ActionEvent e) {
		JComboBox<String> b = (JComboBox<String>) e.getSource();
		String cmd = e.getActionCommand();
		int k = cmd.lastIndexOf(' ');
		String pid = cmd.substring(0, k);
		String inputName = cmd.substring(k + 1);
		System.out.println("You selected " + b.getSelectedItem() + " for " + e.getActionCommand());
		BEASTInterface m_beastObject = beautiDoc.pluginmap.get(pid);
		Input<?> input = m_beastObject.getInput(inputName);
		
        BeautiSubTemplate selected = (BeautiSubTemplate) b.getSelectedItem();
        BEASTInterface beastObject = (BEASTInterface) input.get();
        String id = beastObject.getID();
        String partition = id.indexOf('.') >= 0 ? 
        		id.substring(id.indexOf('.') + 1) : "";
        if (partition.indexOf(':') >= 0) {
        	partition = id.substring(id.indexOf(':') + 1);
        }
        if (selected.equals(InputEditor.NO_VALUE)) {
            beastObject = null;
        } else {
            try {
                beastObject = selected.createSubNet(beautiDoc.getContextFor(beastObject), m_beastObject, input, true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Could not select beastObject: " +
                        ex.getClass().getName() + " " +
                        ex.getMessage()
                );
            }
        }


        try {
            if (beastObject == null) {
                b.setSelectedItem(InputEditor.NO_VALUE);
            } else {
                if (!input.canSetValue(beastObject, m_beastObject)) {
                    throw new IllegalArgumentException("Cannot set input to this value");
                }
            }

            input.setValue(beastObject, m_beastObject);

            refreshText();
        } catch (Exception ex) {
            id = ((BEASTInterface) input.get()).getID();
            b.setSelectedItem(id);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Could not change beastObject: " +
                    ex.getClass().getName() + " " +
                    ex.getMessage() 
            );
        }
	}


	private void handleTextFeild(ActionEvent e) {
		JTextField b = (JTextField) e.getSource();
		String cmd = e.getActionCommand();
		int k = cmd.lastIndexOf(' ');
		String id = cmd.substring(0, k);
		String inputName = cmd.substring(k + 1);
		String value = b.getText();
		System.out.println("You selected " + b.getText() + " for " + cmd);
		BEASTInterface o = beautiDoc.pluginmap.get(id);
		Input<?> input = o.getInput(inputName);
		if (input.canSetValue(value, o)) {
			try {
				input.setValue(value, o);
			} catch (RuntimeException ex) {
				// could not set the value after all...
			}
		}
		System.out.println(id + "." + input.getName() + " set to " + input.get().toString());
	}

	static String printParitions(List<String> partitionIDs, int totalPartitionCount) {
		StringBuilder b = new StringBuilder();
		if (partitionIDs.size() == totalPartitionCount) {
			if (partitionIDs.size() == 2) {
				b.append("both partitions ");
			} else {
				b.append("all partitions ");
			}
		} else {
	    	for (int j = 0; j < partitionIDs.size() - 1; j++) {
	    		b.append(partitionIDs.get(j));
	    		if (j < partitionIDs.size() - 2) {
	    			b.append(", ");
	    		} else {
	    			b.append(" and ");
	    		}
	    	}
	    	b.append(partitionIDs.get(partitionIDs.size() - 1) + " ");
		}
		return b.toString();
	}


	static String printParitions(List<String> partitionIDs) {
		return XML2TextPane.printParitions(partitionIDs, -1);	
	}

	public static void main(String[] args) throws Exception {
		if (System.getProperty("beasy.style") != null) {
			String style = System.getProperty("beasy.style");
			CitationPhrase.CitationMode = mode.valueOf(style);
		}

		final XML2TextPane textPane = new XML2TextPane(args);
        
        JScrollPane paneScrollPane = new JScrollPane(textPane);
        paneScrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        paneScrollPane.setPreferredSize(new Dimension(650, 455));
        paneScrollPane.setMinimumSize(new Dimension(10, 10));

        //Create and set up the window.
        JFrame frame = new JFrame("XML2TextPane");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(paneScrollPane, BorderLayout.CENTER);
        JButton copyButton = new JButton("Copy text to clipboard");
        frame.add(copyButton, BorderLayout.SOUTH);
        copyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(textPane.text);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);				
			}
		});

        frame.pack();
        frame.setVisible(true);
        
	}
}
