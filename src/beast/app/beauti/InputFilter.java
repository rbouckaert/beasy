package beast.app.beauti;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import beast.core.BEASTInterface;
import beast.core.Input;

public class InputFilter {
	BeautiDoc doc;
	
	 public Set<Input<?>> getInputSet(BeautiDoc doc, String idPattern, String elementPattern, String inputPattern) {
		this.doc = doc;		
		Set<Input<?>> inputSet = null;
		// first handle idPattern if any
		if (idPattern != null) {
			inputSet = handleIdPattern(idPattern);
		}
		// then the elementPattern
		if (elementPattern != null) {
			inputSet = handleElemntName(elementPattern, inputSet);
		}
		// and finally the input pattern
		if (inputPattern != null) {
			inputSet = handleInputname(inputPattern, inputSet);
		}
		return inputSet;
	}

	 public static Map<Input<?>, BEASTInterface>  initInputMap(BeautiDoc doc) {
		Map<Input<?>, BEASTInterface> mapInputToObject= new LinkedHashMap<>();
		for (BEASTInterface o : getDocumentObjects(doc.mcmc.get())) {
			for (Input<?> input : o.listInputs()) {
				mapInputToObject.put(input, o);
			}				
		}
		return mapInputToObject;
	}

/*
	@Override
	public BEASTInterface visitIdPattern(IdPatternContext ctx) {
		String idPattern = ctx.getText();
		Set<Input<?>> newInputSet = new LinkedHashSet<>();
		for (BEASTInterface o : getDocumentObjects(doc)) {
			for (Input<?> input : o.listInputs()) {
				if (input.getName().matches(idPattern)) {
					newInputSet.add(input);
				}
			}
		}
		inputSet = newInputSet;
		return super.visitIdPattern(ctx);
	}
	
	@Override
	public BEASTInterface visitInputname(InputnameContext ctx) {
		String inputPattern = ctx.getText();
		if (inputSet == null) {
			setupInputSet(inputPattern);
		} else {
			filterInputSet(inputPattern);
		}
		return super.visitInputname(ctx);
	}
	
	@Override
	public BEASTInterface visitElemntName(ElemntNameContext ctx) {
		String elementPattern = ctx.getText();
		if (elementPattern.endsWith("@")) {
			elementPattern = elementPattern.substring(0, elementPattern.length() - 1);
		}
		elementPattern = normaliseString(elementPattern);
		if (inputSet == null) {
			setupInputSet(elementPattern);
		} else {
			filterInputSet(elementPattern);
		}
		return super.visitElemntName(ctx);
	}
*/		

	 public Set<Input<?>> handleIdPattern(String idPattern) {
		Set<Input<?>> newInputSet = new LinkedHashSet<>();
		for (BEASTInterface o : getDocumentObjects(doc.mcmc.get())) {
			if (o.getID().matches(idPattern)) {
				for (Input<?> input : o.listInputs()) {
					newInputSet.add(input);
				}
			}
		}
		return newInputSet;
	}
	
	 public Set<Input<?>> handleInputname(String inputPattern, Set<Input<?>> inputSet) {
		if (inputSet == null) {
			inputSet = setupInputSet(inputPattern, doc, false);
		} else {
			inputSet = filterInputSet(inputPattern, inputSet);
		}
		return inputSet;
	}
	
	 public Set<Input<?>> handleElemntName(String elementPattern, Set<Input<?>> inputSet) {
		if (elementPattern.endsWith("@")) {
			elementPattern = elementPattern.substring(0, elementPattern.length() - 1);
		}
		elementPattern = normaliseString(elementPattern);
		if (inputSet == null) {
			inputSet = setupInputSet(elementPattern, doc, true);
		} else {
			inputSet = filterInputSet(elementPattern, inputSet);
		}
		return inputSet;
	}

	 private String normaliseString(String str) {
		if (str.startsWith("'") && str.endsWith("'")) {
			return str.substring(1, str.length()-1);
		}
		if (str.startsWith("\"") && str.endsWith("\"")) {
			return str.substring(1, str.length()-1);
		}
		return str;
	}

	 private Set<Input<?>> filterInputSet(String inputPattern, Set<Input<?>> inputSet) {
		Set<Input<?>> newInputSet = new LinkedHashSet<>();
		for (Input<?> input : inputSet) {
			if (input.getName().matches(inputPattern)) {
				newInputSet.add(input);
			}
		}
//		inputSet = newInputSet;
		return newInputSet;
	}

	 private void filterInput(BEASTInterface o, String pattern, Set<Input<?>> newInputSet) {
		for (Input<?> input : o.listInputs()) {
			if (input.getName().matches(pattern)) {
				newInputSet.add(input);
			}
		}
	}

	 static public Set<Input<?>>  setupInputSet(String inputPattern, BeautiDoc doc, boolean useChildren) {
		Set<Input<?>> inputSet = new LinkedHashSet<>();
		for (BEASTInterface o : getDocumentObjects(doc.mcmc.get())) {
			for (Input<?> input : o.listInputs()) {
				if (input.getName().matches(inputPattern)) {
					if (!useChildren) {
						inputSet.add(input);
						if (input.getType() == null) {
							input.determineClass(o);
						}
					} else {
						Object o2 = input.get();
						if (o2 instanceof BEASTInterface) {
							BEASTInterface bo = (BEASTInterface) o2;
							for (Input<?> input2: bo.listInputs()) {
								inputSet.add(input2);
								if (input2.getType() == null) {
									input2.determineClass(o);
								}
							}
						}
					}
				}
			}
		}
		return inputSet;
	}

	 static public Collection<BEASTInterface> getDocumentObjects(BEASTInterface o) {
		List<BEASTInterface> predecessors = new ArrayList<>();
		BeautiDoc.collectPredecessors(o, predecessors);
		return predecessors;
	}


}
