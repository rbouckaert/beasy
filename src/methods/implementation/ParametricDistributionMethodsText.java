package methods.implementation;


import beast.app.beauti.BeautiDoc;
import beast.core.BEASTInterface;
import beast.core.Input;
import java.util.*;
import methods.*;

public class ParametricDistributionMethodsText implements MethodsText {

	@Override
	public Class<?> type() {
		return beast.math.distributions.ParametricDistribution.class;
	}
	
	@Override
	public List<Phrase> getModelDescription(Object o2, BEASTInterface parent, Input<?> input2, BeautiDoc doc) {
		beast.math.distributions.ParametricDistribution o = (beast.math.distributions.ParametricDistribution) o2;
		done.add(o);
		List<Phrase> b = new ArrayList<>();
		b.add(new Phrase(o, parent, input2, getName(o)));
		b.add(new Phrase(" distributed "));
		boolean isFirst = true;
		for (Input<?> input : o.listInputs()) {
			if (!BEASTObjectMethodsText.cfg.suppressBEASTObjects.contains(o.getClass().getName() + "." + input.getName())) {
				if (input.get() != null && input.get() instanceof beast.core.BEASTObject) {
					if (isFirst) {
						isFirst = false;
						b.add(new Phrase("with "));
					} else {
						b.add(new Phrase(","));
					}
					b.add(new Phrase(input.get(), o, input," " + getInputName(input) + " "));
					List<Phrase> m = MethodsTextFactory.getModelDescription(input.get(), o, input, doc);
					b.addAll(m);
				}
			}
		}
 				
		b.addAll(describePriors(o, parent, input2, doc));
		
		return b;
	}
}
