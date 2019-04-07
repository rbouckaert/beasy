package methods.implementation;

import beast.core.BEASTInterface;
import beast.core.Input;
import beast.core.parameter.RealParameter;
import beast.evolution.substitutionmodel.SubstitutionModel;

import java.util.*;
import methods.*;

public class SiteModel implements MethodsText {

	@Override
	public Class type() {
		return beast.evolution.sitemodel.SiteModel.class;
	}

	
	@Override
	public List<Phrase> getModelDescription(Object o, BEASTInterface parent, Input<?> input2) {
		beast.evolution.sitemodel.SiteModel sm = (beast.evolution.sitemodel.SiteModel) o; 
		List<Phrase> b = new ArrayList<>();
		SubstitutionModel subst = sm.substModelInput.get();
		b.add(new Phrase(sm, parent, input2, " gamma site model "));
		b.add(new Phrase("and "));
		b.add(new Phrase(subst, sm, sm.substModelInput, getName(sm.substModelInput.get())));
		b.add(new Phrase(" substitution model "));
		List<Phrase> substModel = MethodsTextFactory.getModelDescription(sm.substModelInput.get(), sm, sm.substModelInput);
		b.addAll(substModel);
		if (sm.gammaCategoryCount.get() > 1) {
			b.add(new Phrase(" with gamma rate heterogeneity using " + sm.gammaCategoryCount.get() + " categories "));
			RealParameter shape = sm.shapeParameterInput.get();
			if (shape.isEstimatedInput.get()) {
				b.add(new Phrase(" (shape "));
				b.addAll(describePriors(shape, sm, sm.shapeParameterInput));
				b.add(new Phrase(")"));
			} else {
				b.add(new Phrase("(shape = " + shape.getValue() + ")"));
			}
		}
		if (sm.invarParameterInput.get().getValue() > 0) {
			b.add(new Phrase(" and a category proportion invarible "));
		}
		// b.delete(b.length()-1, b.length());
		// b.add(new Phrase("\n"));
		b.add(new Phrase("."));
		return b;
	}
}
