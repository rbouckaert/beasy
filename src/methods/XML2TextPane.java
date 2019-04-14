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
import beast.core.Distribution;
import beast.core.Input;
import beast.core.StateNode;
import beast.core.parameter.RealParameter;
import beast.core.util.CompoundDistribution;
import beast.core.util.Log;
import beast.evolution.alignment.Alignment;
import beast.evolution.likelihood.GenericTreeLikelihood;
import beast.evolution.operators.DeltaExchangeOperator;
import beast.evolution.tree.TraitSet;
import beast.evolution.tree.Tree;
import beast.evolution.tree.TreeDistribution;
import beast.evolution.tree.TreeInterface;
import beast.util.XMLParser;
import methods.implementation.BEASTObject;
import beast.core.MCMC;
import beast.core.Operator;

@Description("Convert MCMC analysis in XML file to a methods section")
public class XML2TextPane extends JTextPane implements ActionListener {
	
	BeautiDoc beautiDoc;
	String text;
	
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
		BEASTObject.setBeautiCFG(beautiDoc.beautiConfig);
		
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

		StyledDocument doc = getStyledDocument();
		doc.remove(0, doc.getLength());
		MethodsText.clear();
		initialise((MCMC) beautiDoc.mcmc.get());
	}
	
	public void initialise(MCMC mcmc) throws Exception {
        CompoundDistribution posterior = (CompoundDistribution) mcmc.posteriorInput.get();
		StringBuilder b = new StringBuilder();


		List<Phrase> m = new ArrayList<>();
		methods.implementation.BeautiSubTemplate.initialise();
		for (String analysisIdentifier : methods.implementation.BeautiSubTemplate.analysisIdentifiers) {
			if (beautiDoc.pluginmap.containsKey(analysisIdentifier)) {
	        	BEASTInterface speciesTree = (BEASTInterface) beautiDoc.pluginmap.get(analysisIdentifier);
	        	m = MethodsTextFactory.getModelDescription(speciesTree, null, null, beautiDoc);
	        	b.append(Phrase.toString(m));
	            Phrase.addTextToDocument(getStyledDocument(), this, beautiDoc, m);
				addDot(b);
				m.clear();
			}
		}
		
		addPartitionDescription(b);
				
        
        // collect model descriptions of all partitions
        List<String> partitionIDs = new ArrayList<>();
        List<String> smPartitionIDs = new ArrayList<>();
        List<String> cmPartitionIDs = new ArrayList<>();
        List<List<Phrase>> siteModels = new ArrayList<>();
        List<List<Phrase>> clockModels = new ArrayList<>();
        
        for (Distribution distr : posterior.pDistributions.get()) {
            if (distr.getID().equals("likelihood")) {
                for (Distribution likelihood : ((CompoundDistribution) distr).pDistributions.get()) {
                    if (likelihood instanceof GenericTreeLikelihood) {
                        GenericTreeLikelihood treeLikelihood = (GenericTreeLikelihood) likelihood;
                    	partitionIDs.add(treeLikelihood.dataInput.get().getID());
                    	
                    	BEASTInterface siteModel = (BEASTInterface) treeLikelihood.siteModelInput.get();
                		List<Phrase> sm = MethodsTextFactory.getModelDescription(siteModel, treeLikelihood, treeLikelihood.siteModelInput, beautiDoc);
                		// sm.get(0).setInput(treeLikelihood, treeLikelihood.siteModelInput);
                		// sm.add(new Phrase("\n"));
                		siteModels.add(sm);
                		smPartitionIDs.add(beautiDoc.parsePartition(siteModel.getID()));
                		
                		BEASTInterface clockModel = treeLikelihood.branchRateModelInput.get();
                		List<Phrase> cm = MethodsTextFactory.getModelDescription(clockModel, treeLikelihood, treeLikelihood.branchRateModelInput, beautiDoc);
                		// cm.get(0).setInput(treeLikelihood, treeLikelihood.branchRateModelInput);
                		// cm.add(new Phrase("\n"));
                		clockModels.add(cm);
                		cmPartitionIDs.add(beautiDoc.parsePartition(clockModel.getID()));
                    }
                }
            }
        }
        
        // amalgamate partitions
        amalgamate(siteModels, partitionIDs, smPartitionIDs, b);
        addDot(b);
        amalgamate(clockModels, partitionIDs, cmPartitionIDs, b);
        addDot(b);

        List<Phrase> [] phrases = new List[1];
//    	m.add(new Phrase(b.toString()));
    	phrases[0] = m;
//        Phrase.addTextToDocument(getStyledDocument(), this, beautiDoc, phrases);
        
        // tree priors        
        Set<TreeInterface> trees = new LinkedHashSet<>();
        // List<GenericTreeLikelihood> likelihoods = new ArrayList<>();
        for (Distribution distr : posterior.pDistributions.get()) {
            if (distr.getID().equals("likelihood")) {
                for (Distribution likelihood : ((CompoundDistribution) distr).pDistributions.get()) {
                    if (likelihood instanceof GenericTreeLikelihood) {
                        GenericTreeLikelihood treeLikelihood = (GenericTreeLikelihood) likelihood;
                    	trees.add(treeLikelihood.treeInput.get());
                    	// likelihoods.add(treeLikelihood);
                    }
                }
            }
        }
        
        if (trees.size() == 1) {
        	TreeInterface tree = (TreeInterface) trees.toArray()[0];
        	m = MethodsTextFactory.getModelDescription(tree, null, null, beautiDoc);
        	m.add(0, new Phrase("\nThere is a single tree with "));
        	TraitSet traitSet = ((Tree) tree).getDateTrait();
        	if (traitSet != null) {
        		String direction = traitSet.getDateType().equals(TraitSet.DATE_BACKWARD_TRAIT) ? 
        				"ages not dates" : "dates not ages";
         		m.add(1, new Phrase(" dated tips (in " + direction + ") "));
        	}
        	b.append(Phrase.toString(m));
            Phrase.addTextToDocument(getStyledDocument(), this, beautiDoc, m);
        } else if (beautiDoc.pluginmap.containsKey("Tree.t:Species")) {
        	BEASTInterface speciesTree = (BEASTInterface) beautiDoc.pluginmap.get("Tree.t:Species");
        	m = MethodsTextFactory.getModelDescription(speciesTree, null, null, beautiDoc);
        	if (speciesTree instanceof Tree) {
	        	TraitSet traitSet = ((Tree) speciesTree).getDateTrait();
	        	if (traitSet != null) {
	        		String direction = traitSet.getDateType().equals(TraitSet.DATE_BACKWARD_TRAIT) ? 
	        				"ages not dates" : "dates not ages";
	        		m.add(1, new Phrase("Tree " + speciesTree.getID() +" has dated tips (in " + direction + ")."));
	        	}
        	}
        	m.add(0, new Phrase("\nTree prior: "));
        	b.append(Phrase.toString(m));
            Phrase.addTextToDocument(getStyledDocument(), this, beautiDoc, m);        	
        } else {
	        for (TreeInterface tree : trees) {
	        	m = MethodsTextFactory.getModelDescription(tree, null, null, beautiDoc);
	        	TraitSet traitSet = ((Tree) tree).getDateTrait();
	        	if (traitSet != null) {
	        		String direction = traitSet.getDateType().equals(TraitSet.DATE_BACKWARD_TRAIT) ? 
	        				"ages not dates" : "dates not ages";
	        		m.add(1, new Phrase("Tree " + tree.getID() +" has dated tips (in " + direction + ")."));
	        	}
	        	m.add(0, new Phrase("\nTree prior: "));
	        	b.append(Phrase.toString(m));
	            Phrase.addTextToDocument(getStyledDocument(), this, beautiDoc, m);
	        }
        }
        
        addDot(b);
        
        // has FixMeanMutationRatesOperator?
        StringBuilder b2 = new StringBuilder();
        b2.append("\n\n");
        addFixMeanMutationRatesOperator(mcmc, b2, m);
        b.append(b2.toString());

        // any priors other than parameter and tree priors?
        for (Distribution distr : posterior.pDistributions.get()) {
            if (!distr.getID().equals("likelihood")) {
                for (Distribution prior : ((CompoundDistribution) distr).pDistributions.get()) {
                	if (!(prior instanceof beast.math.distributions.Prior || prior instanceof TreeDistribution)) {
                    	m = MethodsTextFactory.getModelDescription(prior, null, null, beautiDoc);
                    	if (m.size() > 0) {
	                    	m.add(0, new Phrase("Other prior: "));
	        	        	b.append(Phrase.toString(m));
	        	            Phrase.addTextToDocument(getStyledDocument(), this, beautiDoc, m);
	        	            addDot(b);
                    	}
                	}
                }
            }
        }
        
        
		text = b.toString();
		
		text = text.replaceAll("  ", " ");
		text = text.replaceAll("\n\n", "\n");
		for (char c : new char[]{'a','e','i','o','u'}) {
			text = text.replaceAll(" a " + c, " an " + c);					
		}				

		Log.warning(text);
		Log.warning("Done!");
				
	}
	
	private void addDot(StringBuilder b) {
        b.append(".\n\n");
        List<Phrase> [] phrases = new List[1];
		List<Phrase> m = new ArrayList<>();
		m.add(new Phrase(".\n\n"));
    	phrases[0] = m;
        Phrase.addTextToDocument(getStyledDocument(), this, beautiDoc, phrases);
	}


	private void addPartitionDescription(StringBuilder b) {
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
			b.append(XML2Text.printParitions(strs, -1));
			b.append(" sites respectively.");
		}
		b.append("\n");
		m.add(new PartitionPhrase(b.toString()));
        Phrase.addTextToDocument(getStyledDocument(), this, beautiDoc, m);
		
	}


	private void amalgamate(List<List<Phrase>> models, List<String> partitionIDs, 
			List<String> xPartitionIDs, StringBuilder b) {
		List<Phrase> m = new ArrayList<>();

		for (int i = 0; i < partitionIDs.size(); i++) {
        	if (models.get(i) != null) {
                List<String> currentPartitionIDs = new ArrayList<>();
                currentPartitionIDs.add(partitionIDs.get(i));
                String model = Phrase.toSimpleString(models.get(i));

                List<List<Phrase>> selected = new ArrayList<>();
                selected.add(models.get(i));
                String modelID = xPartitionIDs.get(i);
                for (int j = i + 1; j < partitionIDs.size(); j++) {
                	String modelj = Phrase.toSimpleString(models.get(j));
                	if (modelj.equals(model)) {
                	//if (xPartitionIDs.get(j).equals(modelID)) {
                        selected.add(models.get(j));
                		models.set(j, null);
                		currentPartitionIDs.add(partitionIDs.get(j));
                	}
                }
                
                // update MethodsText.partitionGroupMap
                if (selected.size() > 0) {
	                for (int k = 0; k < selected.get(0).size(); k++) {
	                	Object source = models.get(i).get(k).source;
	                	Set<Phrase> set = MethodsText.partitionGroupMap.get(source);
	                	for (int j = 0; j < selected.size(); j++) {
	                		Phrase phrase = selected.get(j).get(k);
	                		set.add(phrase);
	                		MethodsText.partitionGroupMap.put(phrase.source, set);	                		
	                	}
	                }
                }
                
                // translate to text                
                boolean shared = isShared(selected);
                // model = Phrase.toString(selected.toArray(new List[]{}));
            	m.clear();
                if (currentPartitionIDs.size() == partitionIDs.size()) {
                	StringBuilder b2 = new StringBuilder();
                	if (currentPartitionIDs.size() == 1) {
                		b2.append("\nThe partition ");
                	} else {
                		b2.append("\nAll partitions ");
                	}
            		if (selected.size() > 1) {
            			if (shared) {
                			b2.append(" share a ");
                		} else {
                			b2.append(" individually have a ");
                		}
                	} else {
                		b2.append(" has a ");
                	}
                	m.add(new PartitionPhrase(b2.toString()));
                	b2.append(model);
                	b.append(b2.toString());
                } else if (currentPartitionIDs.size() > 1) {
                	StringBuilder b2 = new StringBuilder();
                	b2.append("\nPartitions ");
                	b2.append(XML2Text.printParitions(currentPartitionIDs));
            		if (selected.size() > 1) {
            			if (shared) {
                			b2.append(" share a ");
                		} else {
                			b2.append(" individually have a ");
                		}
                	} else {
                		b2.append(" has a ");
                	}
                	m.add(new PartitionPhrase(b2.toString()));
                	b2.append(model);
                	b.append(b2.toString());

                } else {
                	m.add(new PartitionPhrase("\nPartition " + currentPartitionIDs.get(0) + " has a "));
                	b.append("\nPartitions " + currentPartitionIDs.get(0) + " has a " + model);                	
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


	private void addFixMeanMutationRatesOperator(MCMC mcmc, StringBuilder b, List<Phrase> m) {
        for (Operator op : mcmc.operatorsInput.get()) {
        	if (op.getID().equals("FixMeanMutationRatesOperator")) {
                List<String> partitionIDs = new ArrayList<>();
                for (StateNode s : ((DeltaExchangeOperator)op).parameterInput.get()) {
                	partitionIDs.add(BeautiDoc.parsePartition(s.getID()));
                }
        		b.append("Relative substitution rates among ");
        		if (partitionIDs.size() != beautiDoc.alignments.size()) {
        			b.append("partitions ");
        		}
                b.append(XML2Text.printParitions(partitionIDs, beautiDoc.alignments.size()));
        		b.append("are estimated");
        		m.clear();
        		m.add(new Phrase(b.toString()));
                Phrase.addTextToDocument(getStyledDocument(), this, beautiDoc, m);
                addDot(b);
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
		}
		
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
			// TODO Auto-generated catch block
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

	public static void main(String[] args) throws Exception {
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
