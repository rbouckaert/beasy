package methods.implementation;

import methods.MethodsText;
import methods.MethodsTextFactory;

public class TreeLikelihood implements MethodsText {
		
	@Override
	public Class type() {
		return beast.evolution.likelihood.GenericTreeLikelihood.class;
	}
	
	@Override
	public String getModelDescription(Object o) {
		beast.evolution.likelihood.GenericTreeLikelihood likelihood = (beast.evolution.likelihood.GenericTreeLikelihood) o;
		StringBuilder b = new StringBuilder();
		b.append(MethodsTextFactory.getModelDescription(likelihood.siteModelInput.get()));
		b.append(MethodsTextFactory.getModelDescription(likelihood.branchRateModelInput.get()));
		b.append(MethodsTextFactory.getModelDescription(likelihood.treeInput.get()));
		return b.toString();
	}

}
