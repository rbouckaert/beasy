package methods.implementation;


import java.util.Map;

import java.util.*;
import methods.*;

public class Prior implements MethodsText {

	@Override
	public Class type() {
		return beast.math.distributions.Prior.class;
	}

	@Override
	public List<Phrase> getModelDescription(Object o) {
		beast.math.distributions.Prior p = (beast.math.distributions.Prior) o;
		List<Phrase> b = new ArrayList<>();
		b.addAll(MethodsTextFactory.getModelDescription(p.distInput.get()));
		return b;
	}

}
