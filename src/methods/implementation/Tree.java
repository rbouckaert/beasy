package methods.implementation;


import beast.app.beauti.BeautiDoc;
import beast.core.BEASTInterface;
import beast.core.BEASTObject;
import beast.core.Distribution;
import beast.core.Input;
import beast.core.util.CompoundDistribution;
import beast.evolution.tree.coalescent.TreeIntervals;
import java.util.*;
import methods.*;

public class Tree implements MethodsText {

	@Override
	public Class type() {
		return beast.evolution.tree.Tree.class;
	}

	@Override
	public List<Phrase> getModelDescription(Object o, BEASTInterface parent, Input<?> input2, BeautiDoc doc) {
		List<Phrase> m = describePriors((BEASTObject) o, parent, input2, doc);
		if (m != null && m.size() > 0) {
			return m;
		}
		
		// collect outputs and outputs of outputs
		Set<BEASTInterface> outputs = new LinkedHashSet<>();
		outputs.addAll(((BEASTInterface)o).getOutputs());
		for (BEASTInterface output : ((BEASTInterface)o).getOutputs()) {
			outputs.addAll(output.getOutputs());			
		}
		// check if any of them is in the prior
		List<Phrase> b = new ArrayList<>();
		for (Object output : outputs) {
			if (output instanceof Distribution) {
				Distribution distr = (Distribution) output;
				// is it in the prior?
				for (Object output2 : distr.getOutputs()) {
					if (output2 instanceof CompoundDistribution && ((CompoundDistribution) output2).getID().equals("prior")) {
						b.add(new Phrase(" using "));
						m = MethodsTextFactory.getModelDescription(distr, (CompoundDistribution) output2, ((CompoundDistribution) output2).pDistributions, doc);
						b.addAll(m);
						done.remove(distr);
					}
				}
			}
		}
		return b;

		
		
//		for (Object ooutput : ((BEASTInterface)o).getOutputs()) {
//			if (ooutput instanceof TreeIntervals) {
//				done.add(ooutput);
//				List<Phrase> b = new ArrayList<>();
//				for (Object output : ((TreeIntervals) ooutput).getOutputs()) {
//					if (output instanceof Distribution) {
//						Distribution distr = (Distribution) output;
//						// is it in the prior?
//						for (Object output2 : distr.getOutputs()) {
//							if (output2 instanceof CompoundDistribution && ((CompoundDistribution) output2).getID().equals("prior")) {
//								b.add(new Phrase(" using "));
//								m = MethodsTextFactory.getModelDescription(distr, (CompoundDistribution) output2, ((CompoundDistribution) output2).pDistributions, doc);
//								b.addAll(m);
//								done.remove(distr);
//							}
//						}
//					}
//				}
//				return b;
//			}
//		}
//		return m;
	}

}
