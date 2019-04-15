package methods.implementation;

import java.util.*;

import beast.app.beauti.BeautiDoc;
import beast.core.BEASTInterface;
import beast.core.Input;
import beast.math.distributions.MRCAPrior;
import methods.*;

public class MRCAPriorMethodsText implements MethodsText {

	@Override
	public Class type() {
		return MRCAPrior.class;
	}

	@Override
	public List<Phrase> getModelDescription(Object o2, BEASTInterface parent, Input<?> input2, BeautiDoc doc) {		
		MRCAPrior o = (MRCAPrior) o2;
		List<Phrase> b = new ArrayList<>();
		b.add(new Phrase(o, " MRCA prior " + o.taxonsetInput.get().getID() + " (" + o.taxonsetInput.get().getTaxonCount() + " taxa)"));
		boolean isUsed = false;
		if (o.isMonophyleticInput.get()) {
			b.add(new Phrase(" is monophyletic "));
			isUsed = true;
		}
		if (o.onlyUseTipsInput.get()) {
			b.add(new Phrase(" on tips only "));
		}
		if (o.distInput.get() != null) {
				List<Phrase> m = MethodsTextFactory.getModelDescription(o.distInput.get(), o, o.distInput, doc);
				if (!isUsed) {
					b.add(new Phrase(" being "));
				} else {
					b.add(new Phrase(" and "));
				}
				b.addAll(m);
		} else {
			if (!o.isMonophyleticInput.get() &&! o.onlyUseTipsInput.get()) {
				b.add(new Phrase(" is added for logging only"));
			}
		}
		return b;
	}

}
