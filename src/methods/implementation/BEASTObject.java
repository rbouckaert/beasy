package methods.implementation;

import beast.core.Input;
import methods.MethodsText;
import methods.MethodsTextFactory;

public class BEASTObject implements MethodsText {

	@Override
	public Class type() {
		return beast.core.BEASTObject.class;
	}
	
	@Override
	public String getModelDescription(Object o2) {
		if (done.contains(o2)) {
			return "";
		}
		beast.core.BEASTObject o = (beast.core.BEASTObject) o2;
		done.add(o);
		StringBuilder b = new StringBuilder();
		b.append(o.getClass().getSimpleName() + " with ");
		for (Input<?> input : o.listInputs()) {
			if (input.get() != null && input.get() instanceof beast.core.BEASTObject) {
				b.append(" " + input.getName() + " is ");
				String m = MethodsTextFactory.getModelDescription(input.get());
				b.append(m);
			}
		}
 				
		b.append(describePriors(o));
		
		return b.toString();
	}

}
