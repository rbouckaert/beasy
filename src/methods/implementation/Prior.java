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
		List<Phrase> m = MethodsTextFactory.getModelDescription(p.distInput.get());
		m.get(0).setInput(p, p.distInput);
		b.addAll(m);
		return b;
	}

}
