package methods.implementation;

import beast.core.BEASTInterface;
import beast.core.Input;
import beast.core.StateNode;
import beast.evolution.tree.TreeInterface;
import methods.MethodsText;

public class BranchRateModel implements MethodsText {

	@Override
	public Class type() {		
		return beast.evolution.branchratemodel.BranchRateModel.Base.class;
	}

	@Override
	public String getModelDescription(Object o) {
		StringBuilder b = new StringBuilder();		
		beast.evolution.branchratemodel.BranchRateModel.Base brm = (beast.evolution.branchratemodel.BranchRateModel.Base) o;

		b.append("and " + o.getClass().getSimpleName() + " ");
		boolean hasWith = false;

		done.add(brm);
		for (Input<?> input : ((BEASTInterface)brm).listInputs()) {
			if (input.get() != null && 
					input.get() instanceof StateNode && 
					(!(input.get() instanceof TreeInterface)) && 
					((StateNode)input.get()).isEstimatedInput.get()) {
				if (!hasWith) {
					b.append(" with ");
					hasWith = true;
				}
				b.append(input.getName() + " ");
				done.add(input.get());
				String m = describePriors((StateNode) input.get());
				b.append(m);
			}
		}
		return b.toString();
	}

}
