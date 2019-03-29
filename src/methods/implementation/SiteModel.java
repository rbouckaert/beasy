package methods.implementation;

import beast.core.parameter.RealParameter;
import methods.MethodsText;
import methods.MethodsTextFactory;

public class SiteModel implements MethodsText {

	@Override
	public Class type() {
		return beast.evolution.sitemodel.SiteModel.class;
	}

	
	@Override
	public String getModelDescription(Object o) {
		beast.evolution.sitemodel.SiteModel sm = (beast.evolution.sitemodel.SiteModel) o; 
		StringBuilder b = new StringBuilder();
		String substModel = MethodsTextFactory.getModelDescription(sm.substModelInput.get());
		b.append(substModel);
		if (sm.gammaCategoryCount.get() > 1) {
			b.append(" with gamma rate heterogeneity using " + sm.gammaCategoryCount.get() + " categories ");
			RealParameter shape = sm.shapeParameterInput.get();
			if (shape.isEstimatedInput.get()) {
				b.append(" (shape ");
				b.append(describePriors(shape));
				b.append(")");
			} else {
				b.append("(shape = " + shape.getValue() + ")");
			}
			b.append("\n");
		}
		if (sm.invarParameterInput.get().getValue() > 0) {
			b.append(" and a category proportion invarible\n");
		}
		b.delete(b.length()-1, b.length());
		b.append(".\n");
		return b.toString();
	}
}
