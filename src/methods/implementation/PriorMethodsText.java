package methods.implementation;


import beast.app.beauti.BeautiDoc;
import beast.core.BEASTInterface;
import beast.core.Input;

import java.util.*;
import methods.*;

public class PriorMethodsText implements MethodsText {

	@Override
	public Class<?> type() {
		return beast.math.distributions.Prior.class;
	}

	@Override
	public List<Phrase> getModelDescription(Object o, BEASTInterface parent, Input<?> input2, BeautiDoc doc) {
		beast.math.distributions.Prior p = (beast.math.distributions.Prior) o;
		List<Phrase> b = new ArrayList<>();
		List<Phrase> m = MethodsTextFactory.getModelDescription(p.distInput.get(), p, p.distInput, doc);
		b.addAll(m);
		return b;
	}

}
