package methods.implementation;

import java.util.*;

import beast.app.beauti.BeautiDoc;
import beast.core.BEASTInterface;
import beast.core.Input;
import methods.MethodsText;
import methods.MethodsTextFactory;
import methods.Phrase;

public class CoalescentMethodsText implements MethodsText {
	
	@Override
	public Class<?> type() {
		return beast.evolution.tree.coalescent.Coalescent.class;
	}
	
	@Override
	public List<Phrase> getModelDescription(Object o2, BEASTInterface parent, Input<?> input2, BeautiDoc doc) {
		if (done.contains(o2)) {
			return new ArrayList<>();
		}
		beast.evolution.tree.coalescent.Coalescent o = (beast.evolution.tree.coalescent.Coalescent) o2;
		done.add(o);
		List<Phrase> b = new ArrayList<>();
		b.add(new Phrase(o, parent, input2, getName(o) + " with "));
		for (Input<?> input : o.listInputs()) {
			if (input.get() != null && input.get() instanceof beast.core.BEASTObject) {
				List<Phrase> m = MethodsTextFactory.getModelDescription(input.get(), o, input, doc);
				if (m.size() > 0) {
					if (!input.getName().equals("populationModel")) {
						b.add(new Phrase(input.get(), o, input, " " + getInputName(input) + " is "));
					}
					b.addAll(m);
				}
			}
		}
 				
		b.addAll(describePriors(o, parent, input2, doc));
		
		return b;
	}

}
