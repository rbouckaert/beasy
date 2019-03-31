package methods.implementation;

import java.util.HashMap;
import java.util.Map;

import beast.core.Input;
import methods.MethodsText;
import methods.MethodsTextFactory;

public class ParametricDistribution implements MethodsText {

	@Override
	public Class type() {
		return beast.math.distributions.ParametricDistribution.class;
	}
	
	@Override
	public String getModelDescription(Object o2) {
		beast.math.distributions.ParametricDistribution o = (beast.math.distributions.ParametricDistribution) o2;
		done.add(o);
		StringBuilder b = new StringBuilder();
		b.append(getName(o) + " ");
		boolean isFirst = true;
		for (Input<?> input : o.listInputs()) {
			if (input.get() != null && input.get() instanceof beast.core.BEASTObject) {
				if (isFirst) {
					isFirst = false;
					b.append("with ");
				} else {
					b.append(",");
				}
				b.append(" " + getInputName(input.getName()) + " is ");
				String m = MethodsTextFactory.getModelDescription(input.get());
				b.append(m);
			}
		}
 				
		b.append(describePriors(o));
		
		return b.toString();
	}
}
