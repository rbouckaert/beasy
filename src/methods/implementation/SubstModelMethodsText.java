package methods.implementation;


import beast.app.beauti.BeautiDoc;
import beast.core.BEASTInterface;
import beast.core.Input;
import beast.core.StateNode;
import java.util.*;
import methods.*;

public class SubstModelMethodsText implements MethodsText {

	@Override
	public Class<?> type() {
		return beast.evolution.substitutionmodel.SubstitutionModel.Base.class;
	}

	@Override
	public List<Phrase> getModelDescription(Object o2, BEASTInterface parent, Input<?> input2, BeautiDoc doc) {
		List<Phrase> b = new ArrayList<>();		
		beast.evolution.substitutionmodel.SubstitutionModel.Base o = (beast.evolution.substitutionmodel.SubstitutionModel.Base) o2;
		boolean hasWith = false;

		done.add(o2);
		for (Input<?> input : o.listInputs()) {
			if (!BEASTObjectMethodsText.cfg.suppressBEASTObjects.contains(o.getClass().getName() + "." + input.getName())) {
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
						List<Phrase> m = describePriors((StateNode) input.get(), o, input, doc);
						b.addAll(m);
					} else {
						List<Phrase> m =  MethodsTextFactory.getModelDescription(input.get(), o, input, doc);
						b.addAll(m);
					}
				}
			}
		}
		if (hasWith) {
			b.add(new Phrase("\nand "));
		}
		
		if (o.frequenciesInput.get() != null) {
			List<Phrase> m = MethodsTextFactory.getModelDescription(o.frequenciesInput.get(), o, o.frequenciesInput, doc);
			String text = o.frequenciesInput.get().getID();
			if (text.toLowerCase().indexOf("freqs") > 0) {
				text = text.substring(0, text.toLowerCase().indexOf("freqs"));
			}
			if (m.size() > 0) {
				m.get(0).setText(text);
			} else {
				m.add(new Phrase(text));
			}
			b.addAll(m);
			b.add(new Phrase(" frequencies"));
		}
		return b;
	}

}
