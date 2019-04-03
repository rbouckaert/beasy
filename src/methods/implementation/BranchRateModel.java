package methods.implementation;

import beast.core.BEASTInterface;
import beast.core.Input;
import beast.core.StateNode;
import beast.evolution.tree.TreeInterface;
import methods.MethodsText;
import java.util.*;
import methods.*;

public class BranchRateModel implements MethodsText {

	@Override
	public Class type() {		
		return beast.evolution.branchratemodel.BranchRateModel.Base.class;
	}

	@Override
	public List<Phrase> getModelDescription(Object o) {
		List<Phrase> b = new ArrayList<>();		
		beast.evolution.branchratemodel.BranchRateModel.Base brm = (beast.evolution.branchratemodel.BranchRateModel.Base) o;

		
		b.add(new Phrase(o, getName(o) + " "));
		boolean hasWith = false;

		done.add(brm);
		for (Input<?> input : ((BEASTInterface)brm).listInputs()) {
			if (input.get() != null && 
					input.get() instanceof StateNode && 
					(!(input.get() instanceof TreeInterface)) && 
					((StateNode)input.get()).isEstimatedInput.get()) {
				if (!hasWith) {
					b.add(new Phrase("with "));
					hasWith = true;
				}
				b.add(new Phrase(input.get(), (BEASTInterface) brm, input, getInputName(input) + " "));
				done.add(input.get());
				List<Phrase> m = describePriors((StateNode) input.get());
				b.addAll(m);
			}
		}
		return b;
	}

}
