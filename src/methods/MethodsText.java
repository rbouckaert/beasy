package methods;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import beast.core.BEASTObject;
import beast.core.Description;
import beast.core.Distribution;
import beast.core.StateNode;
import beast.core.util.CompoundDistribution;

@Description("Describe model details in text format for a methods section")
public interface MethodsText {
	static Set<Object> done = new HashSet<>();

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
		
	}

	Class type();
		
	default String getDataDescription(Object o) {
		return "";
	}
	
	String getModelDescription(Object o);

	
	default String describePriors(BEASTObject o) {
		StringBuilder b = new StringBuilder();
		// need to describe priors?
		if (o instanceof StateNode && ((StateNode) o).isEstimatedInput.get()) {			
			for (Object output : o.getOutputs()) {
				if (output instanceof Distribution) {
					Distribution distr = (Distribution) output;
					// is it in the prior?
					for (Object output2 : distr.getOutputs()) {
						if (output2 instanceof CompoundDistribution && ((CompoundDistribution) output2).getID().equals("prior")) {
							b.append(" using ");
							String m = MethodsTextFactory.getModelDescription(distr);
							b.append(m);
						}
					}
				}
			}
		}
		return b.toString();
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

	default String getInputName(String name) {
		if (inputNameMap.containsKey(name)) {
			name = inputNameMap.get(name);
		} else {
	        name = name.replaceAll("([a-z])([A-Z])", "$1 $2");
	        name = name.toLowerCase();			
		}
		return name;
	}
}
