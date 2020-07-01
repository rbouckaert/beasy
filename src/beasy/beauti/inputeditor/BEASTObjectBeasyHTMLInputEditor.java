package beasy.beauti.inputeditor;

import java.lang.reflect.InvocationTargetException;

import beast.app.beauti.BeautiDoc;
import beast.core.BEASTInterface;
import beast.core.Input;
import beasy.beauti.inputeditor.BeasyHTMLInputEditor.Base;

public class BEASTObjectBeasyHTMLInputEditor extends Base {

	BeasyHTMLEditorFactory factory;
	public BEASTObjectBeasyHTMLInputEditor(BeautiDoc doc, BeasyHTMLEditorFactory factory) {
		super(doc);
		this.factory = factory;
	}

	@Override
	public Class<?> getType() {
		return BEASTInterface.class;
	}

	@Override
	public String toHTML(Object o, Input<?> input)
			throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

}
