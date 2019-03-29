package methods;

import java.io.PrintStream;

import beast.app.util.Application;
import beast.app.util.OutFile;
import beast.app.util.XMLFile;
import beast.core.Description;
import beast.core.Distribution;
import beast.core.Input;
import beast.core.Runnable;
import beast.core.util.CompoundDistribution;
import beast.core.util.Log;
import beast.evolution.likelihood.GenericTreeLikelihood;
import beast.util.XMLParser;
import beast.core.Input.Validate;
import beast.core.MCMC;

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
        PrintStream out = new PrintStream(outputInput.get());
		
		
        CompoundDistribution posterior = (CompoundDistribution) mcmc.posteriorInput.get();

        for (Distribution distr : posterior.pDistributions.get()) {
            if (distr.getID().equals("likelihood")) {
            	String partitionDescription = getPartitionDescription((CompoundDistribution) distr);
            	out.println(partitionDescription);
            }
        }
		
        for (Distribution distr : posterior.pDistributions.get()) {
            if (distr.getID().equals("likelihood")) {
                for (Distribution likelihood : ((CompoundDistribution) distr).pDistributions.get()) {
                    if (likelihood instanceof GenericTreeLikelihood) {
                        GenericTreeLikelihood treeLikelihood = (GenericTreeLikelihood) likelihood;
                    	String modelDescription = getModelDescription(treeLikelihood);
                    	out.println(modelDescription);
                    	Log.info.println(modelDescription);
                    }
                }
            }
        }

		out.close();
		Log.warning("Done!");
	}
	
	private String getPartitionDescription(CompoundDistribution distr) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getModelDescription(GenericTreeLikelihood treeLikelihood) {
		return MethodsTextFactory.getModelDescription(treeLikelihood);
	}

	public static void main(String[] args) throws Exception {
		new Application(new XML2Text(), "XML 2 methods section", args);
	}
}
