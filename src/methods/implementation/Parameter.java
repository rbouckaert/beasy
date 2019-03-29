package methods.implementation;

import methods.MethodsText;

public class Parameter implements MethodsText {

	@Override
	public Class type() {
		return beast.core.parameter.Parameter.Base.class;
	}
	
	@Override
	public String getModelDescription(Object o) {
		beast.core.parameter.Parameter.Base p = (beast.core.parameter.Parameter.Base) o;
		if (p.isEstimatedInput.get()) {
			StringBuilder b = new StringBuilder();
			b.append(describePriors(p));
			return b.toString();
		} else {
			return p.getValue().toString();
		}
	}
}
