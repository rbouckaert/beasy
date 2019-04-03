package methods.implementation;

import java.util.*;

import beast.core.Input;
import methods.MethodsText;
import methods.MethodsTextFactory;
import methods.Phrase;

public class ConstantPopulation implements MethodsText {
	
	@Override
	public Class type() {
		return beast.evolution.tree.coalescent.ConstantPopulation.class;
	}
	
	@Override
	public List<Phrase> getModelDescription(Object o2) {
		if (done.contains(o2)) {
			return new ArrayList<>();
		}
		beast.evolution.tree.coalescent.ConstantPopulation o = (beast.evolution.tree.coalescent.ConstantPopulation) o2;
		done.add(o);
		List<Phrase> b = new ArrayList<>();
		b.add(new Phrase(o, getName(o) + " where "));
		for (Input<?> input : o.listInputs()) {
			if (input.get() != null && input.get() instanceof beast.core.BEASTObject) {
				List<Phrase> m = MethodsTextFactory.getModelDescription(input.get());
				if (m.size() > 0) {
					b.add(new Phrase(input.get(), o, input, " " + getInputName(input) + " is "));
					b.addAll(m);
				}
			}
		}
 				
		b.addAll(describePriors(o));
		
		return b;
	}

}
