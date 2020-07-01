package beasy.beauti.inputeditor;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import beast.app.beauti.BeautiDoc;
import beast.core.Input;
import beasy.beauti.inputeditor.BeasyHTMLInputEditor.Base;

public class ListBeasyHTMLInputEditor extends BeasyHTMLInputEditor.Base {
	
	public ListBeasyHTMLInputEditor(BeautiDoc doc) {
		super(doc);
	}

	@Override
	public Class<?> getType() {
		return List.class;
	}
	
	public Class<?> getBaseType() {return null;}
	

	@Override
	public String toHTML(Object o, Input<?> input)
			throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

}
