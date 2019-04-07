package methods.implementation;


import beast.core.BEASTInterface;
import beast.core.Input;
import beast.core.StateNode;
import java.util.*;
import methods.*;

public class SubstModel implements MethodsText {

	@Override
	public Class type() {
		return beast.evolution.substitutionmodel.SubstitutionModel.Base.class;
	}

	@Override
	public List<Phrase> getModelDescription(Object o2, BEASTInterface parent, Input<?> input2) {
		List<Phrase> b = new ArrayList<>();		
		beast.evolution.substitutionmodel.SubstitutionModel.Base o = (beast.evolution.substitutionmodel.SubstitutionModel.Base) o2;
		boolean hasWith = false;

		done.add(o2);
		for (Input<?> input : o.listInputs()) {
			if (input.get() != null && input.get() instanceof StateNode) { // && ((StateNode)input.get()).isEstimatedInput.get()) {
				if (!hasWith) {
					b.add(new Phrase(" with "));
					hasWith = true;
				} else {
					b.add(new Phrase(" and "));
				}
				b.add(new Phrase(input.get(), o, input, getInputName(input) + " "));
				done.add(input.get());
				if (((StateNode)input.get()).isEstimatedInput.get()) {
					List<Phrase> m = describePriors((StateNode) input.get(), o, input);
					b.addAll(m);
				} else {
					List<Phrase> m =  MethodsTextFactory.getModelDescription(input.get(), o, input);
					b.addAll(m);
				}
			}
		}
		if (hasWith) {
			b.add(new Phrase("\nand "));
		}
		
		if (o.frequenciesInput.get() != null) {
			List<Phrase> m = MethodsTextFactory.getModelDescription(o.frequenciesInput.get(), o, o.frequenciesInput);
			String text = o.frequenciesInput.get().getID();
			if (text.indexOf("Freqs") > 0) {
				text = text.substring(0, text.indexOf("Freqs"));
			}
			m.get(0).setText(text);
			b.addAll(m);
			b.add(new Phrase(" frequencies"));
		}
		return b;
	}

}
