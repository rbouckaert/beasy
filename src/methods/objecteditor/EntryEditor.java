package methods.objecteditor;

import beast.app.beauti.BeautiDoc;
import beast.core.BEASTObjectStore;
import beast.core.Input;

public class EntryEditor extends ObjectEditor.Base {

	public EntryEditor(BeautiDoc doc) {
		super(doc);
	}

	@Override
	public Class<?> getType() {
		return Double.class;
	}
	
	@Override
	public Class<?>[] getTypes() {
		return new Class[]{Double.class, Float.class, String.class, Integer.class, Long.class};
	}
	
	@Override
	public String toHTML(Object o, Input<?> input) {
		String source = BEASTObjectStore.getId(o) + " " + input.getName();
		String text = input.get() + "";
		return "<p>" + input.getName() + ": <input size='5' onkeyup='doIt(value,\"" + source + "\")' value='" + text +"'/>\n";
	}

}
