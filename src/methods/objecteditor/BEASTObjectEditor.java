package methods.objecteditor;

import java.lang.reflect.InvocationTargetException;

import beast.app.beauti.BeautiDoc;
import beast.core.BEASTInterface;
import beast.core.Input;
import methods.objecteditor.ObjectEditor.Base;

public class BEASTObjectEditor extends Base {
	ObjectEditorFactory objectEditorFactory;
	
	public BEASTObjectEditor(BeautiDoc doc, ObjectEditorFactory objectEditorFactory) {
		super(doc);
		this.objectEditorFactory = objectEditorFactory;
	}

	@Override
	public Class<?> getType() {		
		return BEASTInterface.class;
	}

	@Override
	public String toHTML(Object o, Input<?> input) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringBuilder html = new StringBuilder();
		html.append("<table>\n");
		BEASTInterface o2 = (BEASTInterface) input.get();
		for (Input<?> input2 : o2.listInputs()) {
			html.append("<tr>\n");
			ObjectEditor editor = objectEditorFactory.getObjectEditor(o2, input2, doc);
			html.append("<p>" + input.getName() + ": ");
			html.append(editor.toHTML(o, input));			
			html.append("</tr>\n");
		}		
		html.append("</table>\n");
		return html.toString();
	}

}
