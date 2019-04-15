package methods.implementation;

import java.util.*;

import beast.app.beauti.BeautiDoc;
import beast.core.BEASTInterface;
import beast.core.Input;
import methods.*;

public class ParameterMethodsText implements MethodsText {

	@Override
	public Class<?> type() {
		return beast.core.parameter.Parameter.Base.class;
	}
	
	@Override
	public List<Phrase> getModelDescription(Object o, BEASTInterface parent, Input<?> input2, BeautiDoc doc) {
		beast.core.parameter.Parameter.Base<?> p = (beast.core.parameter.Parameter.Base<?>) o;
		List<Phrase> b = new ArrayList<>();
		if (p.isEstimatedInput.get()) {
			List<Phrase> m = describePriors(p, parent, input2, doc);
			if (m.size() > 0) {
				b.addAll(m);
			} else {
				// it was not estimated after all, just a constant
				b.add(new Phrase(p.valuesInput.get(), p, p.valuesInput, p.getValue().toString()));
			}
		} else {
			b.add(new Phrase(p.valuesInput.get(), p, p.valuesInput, p.getValue().toString()));
		}
		return b;
	}
}
