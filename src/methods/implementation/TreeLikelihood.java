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
		List<Phrase> m = MethodsTextFactory.getModelDescription(likelihood.siteModelInput.get());
		b.addAll(m);
		m.get(0).setInput(likelihood, likelihood.siteModelInput);
		
		m = MethodsTextFactory.getModelDescription(likelihood.branchRateModelInput.get());
		m.get(0).setInput(likelihood, likelihood.branchRateModelInput);
		b.addAll(m);

		b.add(new Phrase("\n"));
//		b.add(MethodsTextFactory.getModelDescription(likelihood.treeInput.get()));
		return b;
	}

}
