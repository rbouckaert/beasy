package methods.implementation;

import java.util.*;

import beast.core.BEASTInterface;
import beast.core.Input;
import methods.MethodsText;
import methods.MethodsTextFactory;
import methods.Phrase;

public class BEASTObject implements MethodsText {
	
	@Override
	public Class type() {
		return beast.core.BEASTObject.class;
	}
	
	@Override
	public List<Phrase> getModelDescription(Object o2, BEASTInterface parent, Input<?> input2) {
		if (done.contains(o2)) {
			return new ArrayList<>();
		}
		beast.core.BEASTObject o = (beast.core.BEASTObject) o2;
		done.add(o);
		List<Phrase> b = new ArrayList<>();
		b.add(new Phrase(o, parent, input2, getName(o) + " with "));
		for (Input<?> input : o.listInputs()) {
			if (input.get() != null && input.get() instanceof beast.core.BEASTObject) {
				List<Phrase> m = MethodsTextFactory.getModelDescription(input.get(), o, input);
				if (m.size() > 0) {
					b.add(new Phrase(input.get(), o, input, " " + getInputName(input) + " is "));
					b.addAll(m);
				}
			}
		}
 				
		b.addAll(describePriors(o, parent, input2));
		
		return b;
	}

}
