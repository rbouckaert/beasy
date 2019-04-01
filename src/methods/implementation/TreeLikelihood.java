package methods.implementation;

import java.util.*;
import methods.*;

public class TreeLikelihood implements MethodsText {
		
	@Override
	public Class type() {
		return beast.evolution.likelihood.GenericTreeLikelihood.class;
	}
	
	@Override
	public List<Phrase> getModelDescription(Object o) {
		beast.evolution.likelihood.GenericTreeLikelihood likelihood = (beast.evolution.likelihood.GenericTreeLikelihood) o;
		List<Phrase> b = new ArrayList<>();
		b.addAll(MethodsTextFactory.getModelDescription(likelihood.siteModelInput.get()));
		b.addAll(MethodsTextFactory.getModelDescription(likelihood.branchRateModelInput.get()));

		b.add(new Phrase("\n"));
//		b.add(MethodsTextFactory.getModelDescription(likelihood.treeInput.get()));
		return b;
	}

}
