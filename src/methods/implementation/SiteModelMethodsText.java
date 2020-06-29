package methods.implementation;

import beast.app.beauti.BeautiDoc;
import beast.core.BEASTInterface;
import beast.core.Input;
import beast.core.parameter.RealParameter;

import java.util.*;
import methods.*;

public class SiteModelMethodsText implements MethodsText {

	@Override
	public Class<?> type() {
		return beast.evolution.sitemodel.SiteModel.class;
	}

	
	@Override
	public List<Phrase> getModelDescription(Object o, BEASTInterface parent, Input<?> input2, BeautiDoc doc) {
		beast.evolution.sitemodel.SiteModel sm = (beast.evolution.sitemodel.SiteModel) o; 
		List<Phrase> b = new ArrayList<>();
		//SubstitutionModel subst = sm.substModelInput.get();
		b.add(new Phrase(sm, parent, input2, " gamma site model "));
		b.add(new Phrase("and "));
		List<Phrase> substModel = MethodsTextFactory.getModelDescription(sm.substModelInput.get(), sm, sm.substModelInput, doc);
		b.addAll(substModel);
		if (sm.gammaCategoryCount.get() > 1) {
			b.add(new Phrase(" with gamma rate heterogeneity using " + sm.gammaCategoryCount.get() + " categories "));
			RealParameter shape = sm.shapeParameterInput.get();
			if (shape.isEstimatedInput.get()) {
				b.add(new Phrase(" and shape "));
				b.addAll(describePriors(shape, sm, sm.shapeParameterInput, doc));
			} else {
				b.add(new Phrase(" and shape = " + shape.getValue() + ""));
			}
		}
		if (sm.invarParameterInput.get() != null && sm.invarParameterInput.get().getValue() > 0) {
			b.add(new Phrase(" and a category proportion invarible "));
		}
		return b;
	}
}
