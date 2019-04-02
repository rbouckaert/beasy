package methods.implementation;

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
	public List<Phrase> getModelDescription(Object o) {
		beast.evolution.sitemodel.SiteModel sm = (beast.evolution.sitemodel.SiteModel) o; 
		List<Phrase> b = new ArrayList<>();
		SubstitutionModel subst = sm.substModelInput.get();
		b.add(new Phrase("uses "));
		b.add(new Phrase(sm, " gamma site model "));
		b.add(new Phrase("and "));
		b.add(new Phrase(subst, sm, sm.substModelInput, getName(sm.substModelInput.get()) + " "));
		List<Phrase> substModel = MethodsTextFactory.getModelDescription(sm.substModelInput.get());
		b.addAll(substModel);
		if (sm.gammaCategoryCount.get() > 1) {
			b.add(new Phrase(" with gamma rate heterogeneity using " + sm.gammaCategoryCount.get() + " categories "));
			RealParameter shape = sm.shapeParameterInput.get();
			if (shape.isEstimatedInput.get()) {
				b.add(new Phrase(" (shape "));
				b.addAll(describePriors(shape));
				b.add(new Phrase(")"));
			} else {
				b.add(new Phrase("(shape = " + shape.getValue() + ")"));
			}
			b.add(new Phrase("\n"));
		}
		if (sm.invarParameterInput.get().getValue() > 0) {
			b.add(new Phrase(" and a category proportion invarible\n"));
		}
		// b.delete(b.length()-1, b.length());
		// b.add(new Phrase("\n"));
		return b;
	}
}
