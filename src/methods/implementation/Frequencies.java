package methods.implementation;

import java.util.*;

import beast.core.BEASTInterface;
import beast.core.Input;
import methods.*;

public class Frequencies implements MethodsText {

	@Override
	public Class type() {
		return Boolean.class;
		//return beast.evolution.substitutionmodel.Frequencies.class;
	}

	@Override
	public List<Phrase> getModelDescription(Object o2, BEASTInterface parent, Input<?> input2) {
//		beast.evolution.substitutionmodel.Frequencies f = (beast.evolution.substitutionmodel.Frequencies) o;
//		List<Phrase> b = new ArrayList<>();
//		if (f.dataInput.get() != null) {
//			b.add(new Phrase("empirical frequencies"));
//		} else if (f.frequenciesInput.get().isEstimatedInput.get()) {
//			b.add(new Phrase("estimated frequencies"));
//		} else {
//			b.add(new Phrase("fixed equal frequencies"));
//		}
//		return b;
		
		
		beast.core.BEASTObject o = (beast.core.BEASTObject) o2;
		List<Phrase> b = new ArrayList<>();
		b.add(new Phrase(o, getName(o) + " with "));
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
