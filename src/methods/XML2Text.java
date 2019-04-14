package methods;

import java.io.PrintStream;
import java.util.*;

import beast.app.beauti.BeautiDoc;
import beast.app.util.Application;
import beast.app.util.OutFile;
import beast.app.util.XMLFile;
import beast.core.Description;
import beast.core.Distribution;
import beast.core.Input;
import beast.core.Runnable;
import beast.core.StateNode;
import beast.core.util.CompoundDistribution;
import beast.core.util.Log;
import beast.evolution.likelihood.GenericTreeLikelihood;
import beast.evolution.operators.DeltaExchangeOperator;
import beast.evolution.tree.TreeInterface;
import beast.util.XMLParser;
import beast.core.Input.Validate;
import beast.core.MCMC;
import beast.core.Operator;

@Description("Convert MCMC analysis in XML file to a methods section")
public class XML2Text extends Runnable {
	public Input<XMLFile> xmlInput = new Input<>("xml",
			"file name of BEAST XML file containing the model for which to create a methods text file for",
			new XMLFile("examples/normalTest-1XXX.xml"), Validate.REQUIRED);
	public Input<OutFile> outputInput = new Input<>("output", "where to save the file", new OutFile("methods.txt"));
	

	
	@Override
	public void initAndValidate() {}
	
	@Override
	public void run() throws Exception {
		XMLParser parser = new XMLParser();
		MCMC mcmc = (MCMC) parser.parseFile(xmlInput.get());
		StringBuilder b = new StringBuilder();
		
		MethodsText.initNameMap();
		
        CompoundDistribution posterior = (CompoundDistribution) mcmc.posteriorInput.get();

        for (Distribution distr : posterior.pDistributions.get()) {
            if (distr.getID().equals("likelihood")) {
            	String partitionDescription = getPartitionDescription((CompoundDistribution) distr);
            	b.append(partitionDescription);
            	b.append("\n");
            }
        }
		
        
        
        // collect model descriptions of all partitions
        List<String> partitionIDs = new ArrayList<>();
        List<List<Phrase>> partitionModels = new ArrayList<>();
        
        for (Distribution distr : posterior.pDistributions.get()) {
            if (distr.getID().equals("likelihood")) {
                for (Distribution likelihood : ((CompoundDistribution) distr).pDistributions.get()) {
                    if (likelihood instanceof GenericTreeLikelihood) {
                        GenericTreeLikelihood treeLikelihood = (GenericTreeLikelihood) likelihood;
                    	partitionIDs.add(treeLikelihood.dataInput.get().getID());
                    	List<Phrase> modelDescription = getModelDescription(treeLikelihood);
                    	partitionModels.add(modelDescription);
                    }
                }
            }
        }
        
        // amalgamate partitions
        for (int i = 0; i < partitionIDs.size(); i++) {
        	if (partitionModels.get(i) != null) {
                List<String> currentPartitionIDs = new ArrayList<>();
                currentPartitionIDs.add(partitionIDs.get(i));
                String model = Phrase.toSimpleString(partitionModels.get(i));

                List<List<Phrase>> selected = new ArrayList<>();
                selected.add(partitionModels.get(i));
                for (int j = i + 1; j < partitionIDs.size(); j++) {
                	if (Phrase.toSimpleString(partitionModels.get(j)).equals(model)) {
                        selected.add(partitionModels.get(j));
                		partitionModels.set(j, null);
                		currentPartitionIDs.add(partitionIDs.get(j));
                	}
                }
                // translate to text
                model = Phrase.toString(selected.toArray(new List[]{}));
                if (currentPartitionIDs.size() > 1) {
                	b.append("Partitions ");
                	b.append(printParitions(currentPartitionIDs, partitionIDs.size()));
                	b.append(model + "\n");
                } else {
                	b.append("Partitions " + currentPartitionIDs.get(0) + " " + model + "\n");                	
                }
        	}
        }
        
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
        
        b.append("Tree prior: ");
        for (TreeInterface tree : trees) {
        	b.append(Phrase.toString(MethodsTextFactory.getModelDescription(tree, null, null, null)));
        }
        b.append("\n\n");
        
        // has FixMeanMutationRatesOperator?
        for (Operator op : mcmc.operatorsInput.get()) {
        	if (op.getID().equals("FixMeanMutationRatesOperator")) {
        		b.append("Relative substitution rates among ");
                partitionIDs = new ArrayList<>();
                for (StateNode s : ((DeltaExchangeOperator)op).parameterInput.get()) {
                	partitionIDs.add(BeautiDoc.parsePartition(s.getID()));
                }
                b.append(printParitions(partitionIDs, partitionIDs.size()));
        		b.append("are estimated.\n");
        	}
        }

        PrintStream out = new PrintStream(outputInput.get());
        out.print(b.toString());
		out.close();
		Log.warning(b.toString());
		Log.warning("Done!");
	}
	
	static String printParitions(List<String> partitionIDs) {
		return printParitions(partitionIDs, -1);	
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

	private String getPartitionDescription(CompoundDistribution distr) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Phrase> getModelDescription(GenericTreeLikelihood treeLikelihood) {
		return MethodsTextFactory.getModelDescription(treeLikelihood, null, null, null);
	}

	public static void main(String[] args) throws Exception {
		new Application(new XML2Text(), "XML 2 methods section", args);
	}
}
