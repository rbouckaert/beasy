package methods.implementation;

import java.util.*;

import beast.core.BEASTInterface;
import beast.core.Input;
import methods.*;

public class Alignment implements MethodsText {

	@Override
	public Class type() {
		return beast.evolution.alignment.Alignment.class;
	}

	@Override
	public List<Phrase> getModelDescription(Object o, BEASTInterface parent, Input<?> input2) {
		return new ArrayList<>();
	}

}
