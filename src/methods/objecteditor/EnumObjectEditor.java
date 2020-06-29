package methods.objecteditor;

import beast.app.beauti.BeautiDoc;
import beast.core.BEASTObjectStore;
import beast.core.Input;

public class EnumObjectEditor extends ObjectEditor.Base {

    public EnumObjectEditor(BeautiDoc doc) {
		super(doc);
	}

	@Override
	public Class<?> getType() {
        return Enum.class;
	}

	@Override
	public String toHTML(Object o, Input<?> input) {
		String source = BEASTObjectStore.getId(o) + " " + input.getName();
		String selected = input.get() + "";

		String entry = "<select id='" + BEASTObjectStore.getId(o) + "' selected='" + selected + "' "
				+ "onchange='doIt(value,\\\"\"" + source + "\"\\\")'>";
		for (Object value : input.possibleValues) {
			entry += "<option value='" + value + "'>" + value + "</option>";
		}
		entry += "</select>";
		
		return "<td>" + input.getName() + "</td><td> " + entry+ "</td>\n";
	}

}
