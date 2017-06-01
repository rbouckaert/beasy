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
import beast.core.util.Log;
import beast.evolution.alignment.Alignment;
import beast.evolution.alignment.FilteredAlignment;
import beast.evolution.likelihood.ThreadedTreeLikelihood;
import beast.evolution.likelihood.TreeLikelihood;
import beast.util.Randomizer;

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
			if (o.getID() == null) {  
				// hack to ensure any object has an ID
				o.setID(o.getClass().getName() + Randomizer.nextInt(1000));
				Log.info("Setting ID: " + o.getID());
				doc.registerPlugin(o);
				doc.pluginmap.remove(null);
			}
			
			for (Input<?> input : o.listInputs()) {
				mapInputToObject.put(input, o);
			}				
		}
		return mapInputToObject;
	}

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
		return newInputSet;
	}

	static public Set<Input<?>> setupInputSet(String inputPattern, BeautiDoc doc, boolean useChildren) {
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
		collectPredecessors(o, predecessors);
		return predecessors;
	}

    static private void collectPredecessors(BEASTInterface bo, List<BEASTInterface> predecessors) {
        predecessors.add(bo);
        if (bo instanceof Alignment || bo instanceof FilteredAlignment) {
            return;
        }
        try {
            for (BEASTInterface bo2 : bo.listActiveBEASTObjects()) {
            	// hack to deal with ThreadedTreeLikelihood instantiating itself with TreeLikelihoods
            	// in a private input
            	if (!(bo instanceof ThreadedTreeLikelihood) ||  !(bo2 instanceof TreeLikelihood)) {
            		if (!predecessors.contains(bo2)) {
            			collectPredecessors(bo2, predecessors);
            		}
            	}
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


}