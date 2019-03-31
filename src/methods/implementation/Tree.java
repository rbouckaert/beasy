package methods.implementation;

import beast.core.BEASTInterface;
import beast.core.BEASTObject;
import beast.core.Distribution;
import beast.core.util.CompoundDistribution;
import beast.evolution.tree.coalescent.TreeIntervals;
import methods.MethodsText;
import methods.MethodsTextFactory;

public class Tree implements MethodsText {

	@Override
	public Class type() {
		return beast.evolution.tree.Tree.class;
	}

	@Override
	public String getModelDescription(Object o) {
		String m = describePriors((BEASTObject) o);
		if (m.length() == 0) {
			for (Object ooutput : ((BEASTInterface)o).getOutputs()) {
				if (ooutput instanceof TreeIntervals) {
					done.add(ooutput);
					StringBuilder b = new StringBuilder();
					for (Object output : ((TreeIntervals) ooutput).getOutputs()) {
						if (output instanceof Distribution) {
							Distribution distr = (Distribution) output;
							// is it in the prior?
							for (Object output2 : distr.getOutputs()) {
								if (output2 instanceof CompoundDistribution && ((CompoundDistribution) output2).getID().equals("prior")) {
									b.append(" using ");
									m = MethodsTextFactory.getModelDescription(distr);
									b.append(m);
									done.remove(distr);
								}
							}
						}
					}
					return b.toString();
				}
			}
		}
		return m;
	}

}
