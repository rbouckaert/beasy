package methods;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import beast.app.beauti.AlignmentListInputEditor;
import beast.app.beauti.BeautiDoc;
import beast.app.beauti.BeautiPanelConfig;
import beast.app.beauti.BeautiSubTemplate;
import beast.app.draw.BEASTObjectDialog;
import beast.app.draw.InputEditor;
import beast.app.draw.InputEditorFactory;
import beast.app.draw.InputEditor.ExpandOption;
import beast.core.BEASTInterface;
import beast.core.Input;
import beast.core.parameter.RealParameter;

public class ModelEditor {
	
	
	public boolean handleCmd(String cmd, BeautiDoc doc, Component w) {
		String c = getAttribute("cmd", cmd);
		if (c == null) {
			return false;
		}
		System.out.println(c);
		switch (c) {
		case "PartitionEditor": return editPartition(cmd, doc, w);
		case "CitationPhrase": return showCitation(cmd, doc, w);
		case "ref": return showCitation(cmd, doc, w);
		case "text": return handleTextField(cmd, doc, w);
		case "RealParameter": return editRealParameter(cmd, doc, w);
		case "select": return handleComboBox(cmd, doc, w);
		}
		
		return false;
	}

	public boolean showCitation(String cmd, BeautiDoc doc, Component w) {
		int counter = Integer.parseInt(getAttribute("counter", cmd));
		String citation = "Unknown citation";
		for (CitationPhrase p : CitationPhrase.citations.values()) {
			if (p.counter == counter) {
				try {
					citation = p.toReference();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
    	JTextArea textArea = new JTextArea(citation);
    	textArea.setLineWrap(true);
    	textArea.setRows(5);
    	textArea.setColumns(50);
    	textArea.setEditable(true);
    	JScrollPane scroller = new JScrollPane(textArea);
    	JOptionPane.showMessageDialog(w, scroller);
    	return false;
	}


	private boolean editRealParameter(String cmd, BeautiDoc doc, Component w) {
		String id = getAttribute("id", cmd);
		RealParameter param = (RealParameter) doc.pluginmap.get(id);
		
		List<Phrase> set = new ArrayList<>(); 
		set.addAll(MethodsText.partitionGroupMap.get(param));
		if (set.size() > 1) {
			String [] options = new String[set.size() + 1];
			int i = 0;
			options[i++] = "All";
			for (Phrase p : set) {
				options[i++] = ((RealParameter) p.source).getID();
			}
			i = JOptionPane.showOptionDialog(w, "Which parameter?", null, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "All");
			if (i > 0) {
				Phrase x = set.get(i-1);
				set.clear();
				set.add(x);
				param = (RealParameter) doc.pluginmap.get(options[i]);
			}
		}
		
        BEASTObjectDialog dlg = new BEASTObjectDialog(param, RealParameter.class, doc);
        if (dlg.showDialog()) {
        	for (Phrase p : set) {
        		param = (RealParameter) p.source;
                String id2 = param.getID();
        		dlg.accept((BEASTInterface) param, doc);
        		param.setID(id2);
        	}
            try {
            	return true;
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        }		
    	return false;
	}


	private boolean editPartition(String cmd, BeautiDoc doc, Component w) {
		BeautiPanelConfig config = new BeautiPanelConfig();
		config.initByName("path","distribution/distribution[id=\"likelihood\"]/distribution/data",
				"panelname", "Partitions", "tiptext", "Data Partitions",
	            "hasPartitions", "none", "forceExpansion", "FALSE",
	            "type", "beast.evolution.alignment.Alignment"    				
				);
		final Input<?> input = config.resolveInput(doc, 0);    		
		AlignmentListInputEditor ie = new AlignmentListInputEditor(doc);
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

        final JDialog dialog = optionPane.createDialog(w, "Partition panel");
        dialog.setResizable(true);
        dialog.pack();

        dialog.setVisible(true);
        
        try {
			return true;
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
    	return false;
	}


	private boolean handleComboBox(String cmd, BeautiDoc doc, Component w) {
		//JComboBox<String> b = (JComboBox<String>) e.getSource();
		String pid = getAttribute("source", cmd);
		String inputName = getAttribute("input", cmd);
		
		String selectedValue = getAttribute("value", cmd); 
		System.out.println("You selected " + selectedValue + " for " + cmd);
		BEASTInterface m_beastObject = doc.pluginmap.get(pid);
		Input<?> input = m_beastObject.getInput(inputName);
		
        InputEditorFactory inputEditorFactory = doc.getInputEditorFactory();
        List<BeautiSubTemplate> plugins = inputEditorFactory.getAvailableTemplates(input, m_beastObject, null, doc);

        BeautiSubTemplate selected = null;
        for (BeautiSubTemplate template : plugins) {
        	if (template.toString().equals(selectedValue)) {
        		selected = template;
        		break;
        	}
        }
        
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
                beastObject = selected.createSubNet(doc.getContextFor(beastObject), m_beastObject, input, true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Could not select beastObject: " +
                        ex.getClass().getName() + " " +
                        ex.getMessage()
                );
            }
        }


        try {
            if (beastObject == null) {
                //b.setSelectedItem(InputEditor.NO_VALUE);
            } else {
                if (!input.canSetValue(beastObject, m_beastObject)) {
                    throw new IllegalArgumentException("Cannot set input to this value");
                }
            }

            input.setValue(beastObject, m_beastObject);

            return true;
        } catch (Exception ex) {
            id = ((BEASTInterface) input.get()).getID();
            //b.setSelectedItem(id);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Could not change beastObject: " +
                    ex.getClass().getName() + " " +
                    ex.getMessage() 
            );
        }
    	return false;
	}


	private boolean handleTextField(String cmd, BeautiDoc doc, Component w) {
		//JTextField b = (JTextField) e.getSource();
		int k = cmd.lastIndexOf(' ');
		String id = cmd.substring(0, k);
		String inputName = cmd.substring(k + 1);
		String value = getAttribute("value", cmd);
		//System.out.println("You selected " + b.getText() + " for " + cmd);
		BEASTInterface o = doc.pluginmap.get(id);
		Input<?> input = o.getInput(inputName);
		if (input.canSetValue(value, o)) {
			try {
				input.setValue(value, o);
			} catch (RuntimeException ex) {
				// could not set the value after all...
			}
		}
		System.out.println(id + "." + input.getName() + " set to " + input.get().toString());
    	return false;
	}


	private String getAttribute(String attr, String cmd) {
		cmd = cmd.replaceAll("%20", " ");
		int i = cmd.indexOf(attr);
		if (i < 0) {
			return null;
		}
		String value = cmd.substring(i + attr.length() + 1);
		if (value.charAt(0) == '"') {
			value = value.substring(1, value.indexOf('"', 1));
		} else if (value.charAt(0) == '\'') {
			value = value.substring(1, value.indexOf('\'', 1));
		} else if (value.indexOf(' ') > 0) {
			value = value.substring(0, value.indexOf(' '));
		}
		return value;
	}

}
