package methods;

import java.util.HashSet;
import java.util.Set;

import beast.core.BEASTObject;
import beast.core.Description;
import beast.core.Distribution;
import beast.core.StateNode;
import beast.core.util.CompoundDistribution;

@Description("Describe model details in text format for a methods section")
public interface MethodsText {
	static Set<Object> done = new HashSet<>();
	
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
	
}
