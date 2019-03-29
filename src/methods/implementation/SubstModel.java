package methods.implementation;

import beast.core.Input;
import beast.core.StateNode;
import methods.MethodsText;
import methods.MethodsTextFactory;

public class SubstModel implements MethodsText {

	@Override
	public Class type() {
		return beast.evolution.substitutionmodel.SubstitutionModel.Base.class;
	}

	@Override
	public String getModelDescription(Object o2) {
		StringBuilder b = new StringBuilder();		
		beast.evolution.substitutionmodel.SubstitutionModel.Base o = (beast.evolution.substitutionmodel.SubstitutionModel.Base) o2;
		b.append("uses " + o.getClass().getSimpleName() + " ");
		boolean hasWith = false;

		done.add(o2);
		for (Input<?> input : o.listInputs()) {
			if (input.get() != null && input.get() instanceof StateNode && ((StateNode)input.get()).isEstimatedInput.get()) {
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
		if (hasWith) {
			b.append("\nand ");
		}
		
		b.append(MethodsTextFactory.getModelDescription(o.frequenciesInput.get()));
		return b.toString();
	}

}
