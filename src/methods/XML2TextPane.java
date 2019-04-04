package methods;


import java.awt.Dimension;
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
import beast.app.draw.InputEditor;
import beast.app.draw.InputEditor.ExpandOption;
import beast.core.BEASTInterface;
import beast.core.Description;
import beast.core.Distribution;
import beast.core.Input;
import beast.core.StateNode;
import beast.core.util.CompoundDistribution;
import beast.core.util.Log;
import beast.evolution.alignment.Alignment;
import beast.evolution.likelihood.GenericTreeLikelihood;
import beast.evolution.operators.DeltaExchangeOperator;
import beast.evolution.tree.TreeInterface;
import beast.util.XMLParser;
import beast.core.MCMC;
import beast.core.Operator;

@Description("Convert MCMC analysis in XML file to a methods section")
public class XML2TextPane extends JTextPane implements ActionListener {
	
	BeautiDoc beautiDoc;
	public XML2TextPane(String [] args) throws Exception {
		beautiDoc = new BeautiDoc();
		File file = new File(args[0]);
		beautiDoc.setFileName(file.getAbsolutePath());
		beautiDoc.beautiConfig = new BeautiConfig();
		beautiDoc.beautiConfig.initAndValidate();		
		String xml = beautiDoc.load(file);
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
		
		MethodsText.initNameMap();
		initialise((MCMC) beautiDoc.mcmc.get());
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

		StyledDocument doc = getStyledDocument();
		doc.remove(0, doc.getLength());
		MethodsText.done.clear();
		initialise((MCMC) beautiDoc.mcmc.get());
	}
	
	public void initialise(MCMC mcmc) throws Exception {
        CompoundDistribution posterior = (CompoundDistribution) mcmc.posteriorInput.get();
		StringBuilder b = new StringBuilder();


		List<Phrase> m = new ArrayList<>();

		
		addPartitionDescription();
				
        
        
        // collect model descriptions of all partitions
        List<String> partitionIDs = new ArrayList<>();
        List<List<Phrase>> siteModels = new ArrayList<>();
        List<List<Phrase>> clockModels = new ArrayList<>();
        
        for (Distribution distr : posterior.pDistributions.get()) {
            if (distr.getID().equals("likelihood")) {
                for (Distribution likelihood : ((CompoundDistribution) distr).pDistributions.get()) {
                    if (likelihood instanceof GenericTreeLikelihood) {
                        GenericTreeLikelihood treeLikelihood = (GenericTreeLikelihood) likelihood;
                    	partitionIDs.add(treeLikelihood.dataInput.get().getID());
                    	
                		List<Phrase> sm = MethodsTextFactory.getModelDescription(treeLikelihood.siteModelInput.get());
                		sm.get(0).setInput(treeLikelihood, treeLikelihood.siteModelInput);
                		sm.add(new Phrase("\n"));
                		siteModels.add(sm);
                		
                		List<Phrase> cm = MethodsTextFactory.getModelDescription(treeLikelihood.branchRateModelInput.get());
                		cm.get(0).setInput(treeLikelihood, treeLikelihood.branchRateModelInput);
                		cm.add(new Phrase("\n"));
                		clockModels.add(cm);
                    }
                }
            }
        }
        
        // amalgamate partitions
        amalgamate(siteModels, partitionIDs, b);
        amalgamate(clockModels, partitionIDs, b);


        List<Phrase> [] phrases = new List[1];
    	m.add(new Phrase(b.toString()));
    	phrases[0] = m;
//        Phrase.addTextToDocument(getStyledDocument(), this, beautiDoc, phrases);
        
        // tree priors        
        Set<TreeInterface> trees = new LinkedHashSet<>();
        for (Distribution distr : posterior.pDistributions.get()) {
            if (distr.getID().equals("likelihood")) {
                for (Distribution likelihood : ((CompoundDistribution) distr).pDistributions.get()) {
                    if (likelihood instanceof GenericTreeLikelihood) {
                        GenericTreeLikelihood treeLikelihood = (GenericTreeLikelihood) likelihood;
                    	trees.add(treeLikelihood.treeInput.get());
                    }
                }
            }
        }
        
        if (trees.size() == 1) {
        	TreeInterface tree = (TreeInterface) trees.toArray()[0];
        	m = MethodsTextFactory.getModelDescription(tree);
        	m.set(0, new Phrase("\nThere is a single tree with "));
        	phrases = new List[1];
        	phrases[0] = m;
            Phrase.addTextToDocument(getStyledDocument(), this, beautiDoc, phrases);        	
        } else {
	        for (TreeInterface tree : trees) {
	        	m.set(0, new Phrase("\nTree prior: "));
	        	b.append(Phrase.toString(m));
	        	phrases = new List[1];
	        	phrases[0] = m;
	            Phrase.addTextToDocument(getStyledDocument(), this, beautiDoc, phrases);
	        }
        }
		Log.warning(b.toString());
        b = new StringBuilder();
        b.append("\n\n");
        
        // has FixMeanMutationRatesOperator?
        for (Operator op : mcmc.operatorsInput.get()) {
        	if (op.getID().equals("FixMeanMutationRatesOperator")) {
        		b.append("Relative substitution rates among partitions ");
                partitionIDs = new ArrayList<>();
                for (StateNode s : ((DeltaExchangeOperator)op).parameterInput.get()) {
                	partitionIDs.add(BeautiDoc.parsePartition(s.getID()));
                }
                b.append(XML2Text.printParitions(partitionIDs));
        		b.append("are estimated.\n");
        		m.clear();
        		m.add(new Phrase(b.toString()));
                Phrase.addTextToDocument(getStyledDocument(), this, beautiDoc, m);
        	}
        }

		Log.warning(b.toString());
		Log.warning("Done!");
	}
	

	private void addPartitionDescription() {
		StringBuilder b = new StringBuilder();
		List<Phrase> m = new ArrayList<>();

		List<BEASTInterface> parts = beautiDoc.getPartitions("Partitions");
		if (parts.size() == 1) {
			b.append("There is one alignment with ");
			Alignment data = (Alignment) parts.get(0);
			b.append(data.getTaxonCount());
			b.append(" taxa, and ");
			b.append(data.getSiteCount());
			b.append(" characters.");
		} else {
			List<String> strs = new ArrayList<>();
			for (BEASTInterface o : parts) {
				Alignment data = (Alignment) o;
				strs.add(data.getID());
			}	
			b.append("There are " + parts.size() + " partitions (" + XML2Text.printParitions(strs) + ") with ");
			strs.clear();
			for (BEASTInterface o : parts) {
				Alignment data = (Alignment) o;
				strs.add(data.getSiteCount() + "");
			}	
			b.append(XML2Text.printParitions(strs));
			b.append(" sites respectively.");
		}
		b.append("\n");
		m.add(new PartitionPhrase(b.toString()));
        Phrase.addTextToDocument(getStyledDocument(), this, beautiDoc, m);
		
	}


	private void amalgamate(List<List<Phrase>> models, List<String> partitionIDs, StringBuilder b) {
		List<Phrase> m = new ArrayList<>();

		for (int i = 0; i < partitionIDs.size(); i++) {
        	if (models.get(i) != null) {
                List<String> currentPartitionIDs = new ArrayList<>();
                currentPartitionIDs.add(partitionIDs.get(i));
                String model = Phrase.toSimpleString(models.get(i));

                List<List<Phrase>> selected = new ArrayList<>();
                selected.add(models.get(i));
                for (int j = i + 1; j < partitionIDs.size(); j++) {
                	if (Phrase.toSimpleString(models.get(j)).equals(model)) {
                        selected.add(models.get(j));
                		models.set(j, null);
                		currentPartitionIDs.add(partitionIDs.get(j));
                	}
                }
                // translate to text
                
                boolean shared = isShared(selected);
                model = Phrase.toString(selected.toArray(new List[]{}));
            	m.clear();
                if (currentPartitionIDs.size() == partitionIDs.size()) {
                	StringBuilder b2 = new StringBuilder();
                	b2.append("\nAll partitions ");
            		if (selected.size() > 1) {
            			if (shared) {
                			b2.append(" share a ");
                		} else {
                			b2.append(" each have a ");
                		}
                	} else {
                		b2.append(" has ");
                	}
                	b.append("\nAll paritions ");
                	m.add(new Phrase(b2.toString()));
                	b2.append(model + "\n");
                } else if (currentPartitionIDs.size() > 1) {
                	StringBuilder b2 = new StringBuilder();
                	b2.append("\nPartitions ");
                	b2.append(XML2Text.printParitions(currentPartitionIDs));
            		if (selected.size() > 1) {
            			if (shared) {
                			b2.append(" share a ");
                		} else {
                			b2.append(" each have a ");
                		}
                	} else {
                		b2.append(" has ");
                	}
                	b.append(b2.toString());
                	m.add(new Phrase(b2.toString()));
                	b2.append(model + "\n");

                } else {
                	m.add(new Phrase("\nPartition " + currentPartitionIDs.get(0) + " has a "));
                	b.append("\nPartitions " + currentPartitionIDs.get(0) + " has a " + model + "\n");                	
                }
                
                if (model.trim().length() > 0) {
                	List<Phrase> [] phrases = new List[1];
                	phrases[0] = m;
                	Phrase.addTextToDocument(getStyledDocument(), this, beautiDoc, phrases);
                }

                Phrase.addTextToDocument(getStyledDocument(), this, beautiDoc, selected.toArray(new List[]{}));
        	}
        }
     }


	private boolean isShared(List<List<Phrase>> selected) {
		if (selected.size() == 1) {
			return true;
		}
			
		Set<BEASTInterface> stateNodes = new LinkedHashSet<>();
		for (Phrase phrase : selected.get(0)) {
			if (phrase.source instanceof BEASTInterface) {
				stateNodes.add((BEASTInterface)phrase.source);
			}
		}
		
		for (int i = 1; i < selected.size(); i++) {
			Set<BEASTInterface> otherNodes = new LinkedHashSet<>();
			for (Phrase phrase : selected.get(i)) {
				if (phrase.source instanceof BEASTInterface) {
					otherNodes.add((BEASTInterface)phrase.source);
					
				}
			}
			if (!stateNodes.containsAll(otherNodes) || !otherNodes.containsAll(stateNodes)) {
				return false;
			}
		}
		
		return true;
	}


	@Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() instanceof JButton) {
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

            final JDialog dialog = optionPane.createDialog(null, "Partition panel");
            dialog.setResizable(true);
            dialog.pack();

            dialog.setVisible(true);
            
            try {
				refreshText();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	} if (e.getSource() instanceof JComboBox) {
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
    		
    		
    	} else if (e.getSource() instanceof JTextField) {
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
    }


	private List<Phrase> getModelDescription(GenericTreeLikelihood treeLikelihood) {
		return MethodsTextFactory.getModelDescription(treeLikelihood);
	}

	public static void main(String[] args) throws Exception {
        XML2TextPane textPane = new XML2TextPane(args);
        
        JScrollPane paneScrollPane = new JScrollPane(textPane);
        paneScrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        paneScrollPane.setPreferredSize(new Dimension(650, 455));
        paneScrollPane.setMinimumSize(new Dimension(10, 10));

        //Create and set up the window.
        JFrame frame = new JFrame("XML2TextPane");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(paneScrollPane);

        frame.pack();
        frame.setVisible(true);
        
	}
}
