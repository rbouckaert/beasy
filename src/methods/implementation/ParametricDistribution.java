package methods.implementation;


import java.util.HashMap;
import java.util.Map;

import beast.core.Input;
import java.util.*;
import methods.*;

public class ParametricDistribution implements MethodsText {

	@Override
	public Class type() {
		return beast.math.distributions.ParametricDistribution.class;
	}
	
	@Override
	public List<Phrase> getModelDescription(Object o2) {
		beast.math.distributions.ParametricDistribution o = (beast.math.distributions.ParametricDistribution) o2;
		done.add(o);
		List<Phrase> b = new ArrayList<>();
		b.add(new Phrase(o, getName(o) + " "));
		boolean isFirst = true;
		for (Input<?> input : o.listInputs()) {
			if (input.get() != null && input.get() instanceof beast.core.BEASTObject) {
				if (isFirst) {
					isFirst = false;
					b.add(new Phrase("with "));
				} else {
					b.add(new Phrase(","));
				}
				b.add(new Phrase(" " + getInputName(input.getName()) + " is "));
				List<Phrase> m = MethodsTextFactory.getModelDescription(input.get());
				b.addAll(m);
			}
		}
 				
		b.addAll(describePriors(o));
		
		return b;
	}
}
