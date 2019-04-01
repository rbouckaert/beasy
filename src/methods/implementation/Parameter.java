package methods.implementation;

import java.util.*;
import methods.*;

public class Parameter implements MethodsText {

	@Override
	public Class type() {
		return beast.core.parameter.Parameter.Base.class;
	}
	
	@Override
	public List<Phrase> getModelDescription(Object o) {
		beast.core.parameter.Parameter.Base p = (beast.core.parameter.Parameter.Base) o;
		List<Phrase> b = new ArrayList<>();
		if (p.isEstimatedInput.get()) {
			b.addAll(describePriors(p));
		} else {
			b.add(new Phrase(p, p.getValue().toString()));
		}
		return b;
	}
}
