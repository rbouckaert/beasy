package methods;

import java.util.*;

import beast.app.beauti.BeautiDoc;
import beast.core.BEASTInterface;
import beast.core.BEASTObject;
import beast.core.Description;
import beast.core.Distribution;
import beast.core.Input;
import beast.core.StateNode;
import beast.core.util.CompoundDistribution;
import beast.evolution.tree.TreeDistribution;
import methods.implementation.BeautiSubTemplateMethodsText;

@Description("Describe model details in text format for a methods section")
public interface MethodsText {
	static Set<Object> done = new HashSet<>();
	static Map<Object, Set<Phrase>> partitionGroupMap = new LinkedHashMap<>();

	static void clear() {
		MethodsText.done.clear();
		MethodsText.partitionGroupMap.clear();
	}

	static Map<String, String> nameMap = new HashMap<>();
	static Map<String, String> inputNameMap = new HashMap<>();
	static void initNameMap() {
		nameMap.put("LogNormalDistributionModel", "log-normal");
		nameMap.put("OneOnX", "1/X distribution");
		nameMap.put("ConstantPopulation", "a constant population");
		nameMap.put("Coalescent", "coalescent tree prior");
		nameMap.put("StrictClockModel", "strict clock model");
		nameMap.put("HKY", "HKY");
//		nameMap.put("Coalescent", "coalescent tree prior");
//		nameMap.put("Coalescent", "coalescent tree prior");
		inputNameMap.put("clock.rate", "clock rate");
		inputNameMap.put("populationModel", "population model");
		inputNameMap.put("popSize", "population size");
		inputNameMap.put("M", "mean log");
		inputNameMap.put("S", "sd log");		
	}

	Class type();
		
	default String getDataDescription(Object o) {
		return "";
	}
	
	List<Phrase> getModelDescription(Object o, BEASTInterface parent, Input<?> input, BeautiDoc doc);

	
	default List<Phrase> describePriors(BEASTObject o, BEASTInterface parent, Input<?> input2, BeautiDoc doc) {
		List<Phrase> b = new ArrayList<>();
		// need to describe priors?
		if (o instanceof StateNode && ((StateNode) o).isEstimatedInput.get()) {			
			for (Object output : o.getOutputs()) {
				if (output instanceof Distribution && !BeautiSubTemplateMethodsText.auxTreePriors.contains(output.getClass().getName())) {
					Distribution distr = (Distribution) output;
					// is it in the prior?
					for (Object output2 : distr.getOutputs()) {
						if (output2 instanceof CompoundDistribution && !((CompoundDistribution) output2).getID().equals("likelihood")) {
							if (!(distr instanceof TreeDistribution && done.contains(distr))) {
								//b.add(new Phrase(distr, " "));
								b.add(new Phrase(" "));
								List<Phrase> m = MethodsTextFactory.getModelDescription(distr, (CompoundDistribution) output2, ((CompoundDistribution) output2).pDistributions, doc);
								b.addAll(m);
							}
						}
					}
				}
			}
		}
		return b;
	}
	
	default String getName(Object o) {
		String name = o.getClass().getSimpleName();
		if (nameMap.containsKey(name)) {
			name = nameMap.get(name);
		} else {
	        name = name.replaceAll("([a-z])([A-Z])", "$1 $2");
	        name = name.toLowerCase();			
		}
		return name;
	}

	default String getInputName(Input<?> input) {
		String name = input.getName();
		if (inputNameMap.containsKey(name)) {
			name = inputNameMap.get(name);
		} else {
	        name = name.replaceAll("([a-z])([A-Z])", "$1 $2");
	        name = name.toLowerCase();			
		}
		return name;
	}

}
