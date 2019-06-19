package methods.objecteditor;

import beast.app.beauti.BeautiDoc;
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
		// TODO Auto-generated method stub
		return null;
	}

}
