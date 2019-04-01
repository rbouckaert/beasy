package methods.implementation;


import beast.core.BEASTInterface;
import beast.core.BEASTObject;
import beast.core.Distribution;
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
	public List<Phrase> getModelDescription(Object o) {
		List<Phrase> m = describePriors((BEASTObject) o);
		if (m.size() == 0) {
			for (Object ooutput : ((BEASTInterface)o).getOutputs()) {
				if (ooutput instanceof TreeIntervals) {
					done.add(ooutput);
					List<Phrase> b = new ArrayList<>();
					for (Object output : ((TreeIntervals) ooutput).getOutputs()) {
						if (output instanceof Distribution) {
							Distribution distr = (Distribution) output;
							// is it in the prior?
							for (Object output2 : distr.getOutputs()) {
								if (output2 instanceof CompoundDistribution && ((CompoundDistribution) output2).getID().equals("prior")) {
									b.add(new Phrase(" using "));
									m = MethodsTextFactory.getModelDescription(distr);
									b.addAll(m);
									done.remove(distr);
								}
							}
						}
					}
					return b;
				}
			}
		}
		return m;
	}

}
