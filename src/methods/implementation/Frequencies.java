package methods.implementation;

import java.util.*;
import methods.*;

public class Frequencies implements MethodsText {

	@Override
	public Class type() {
		return beast.evolution.substitutionmodel.Frequencies.class;
	}

	@Override
	public List<Phrase> getModelDescription(Object o) {
		beast.evolution.substitutionmodel.Frequencies f = (beast.evolution.substitutionmodel.Frequencies) o;
		List<Phrase> b = new ArrayList<>();
		if (f.dataInput.get() != null) {
			b.add(new Phrase("empirical frequencies\n"));
		} else if (f.frequenciesInput.get().isEstimatedInput.get()) {
			b.add(new Phrase("estimated frequencies\n"));
		} else {
			b.add(new Phrase("fixed equal frequencies\n"));
		}
		return b;
	}

}
