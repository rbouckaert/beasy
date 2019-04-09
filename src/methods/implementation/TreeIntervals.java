package methods.implementation;

import java.util.*;

import beast.app.beauti.BeautiDoc;
import beast.core.BEASTInterface;
import beast.core.Input;
import methods.*;

public class TreeIntervals implements MethodsText {

	@Override
	public Class type() {
		return beast.evolution.tree.coalescent.TreeIntervals.class;
	}

	@Override
	public List<Phrase> getModelDescription(Object o, BEASTInterface parent, Input<?> input2, BeautiDoc doc) {
		return new ArrayList<>();
	}

}
