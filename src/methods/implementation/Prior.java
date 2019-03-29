package methods.implementation;

import methods.MethodsText;
import methods.MethodsTextFactory;

public class Prior implements MethodsText {

	@Override
	public Class type() {
		return beast.math.distributions.Prior.class;
	}

	@Override
	public String getModelDescription(Object o) {
		beast.math.distributions.Prior p = (beast.math.distributions.Prior) o;
		StringBuilder b = new StringBuilder();
		b.append(MethodsTextFactory.getModelDescription(p.distInput.get()));
		return b.toString();
	}

}
