package beasy.beauti.inputeditor;



import beast.app.beauti.BeautiDoc;
import beast.core.BEASTInterface;
import beast.core.Description;
import beast.core.Input;

import java.lang.reflect.InvocationTargetException;


/**
 * HTML version of beast.app.draw.InputEditor.
 * 
 * Base class for editors that provide a GUI for manipulating an Input for a BEASTObject.
 * The idea is that for every type of Input there will be a dedicated editor, e.g.
 * for a String Input, there will be an edit field, for a Boolean Input, there will
 * be a checkbox in the editor.
 * <p/>
 * The default just provides an edit field and uses toString() on Input to get its value.
 * To change the behaviour, override
 * public void init(Input<?> input, BEASTObject beastObject, int itemNr, ExpandOption isExpandOption, boolean addButtons)
 */
public interface BeasyHTMLInputEditor {
	

	/** type of object that can be edited with this editor **/
	Class<?> getType();

    /** list of types of BEASTObjects to which this editor can be used **/ 
	default public Class<?>[] getTypes() {
        Class<?>[] types = new Class<?>[1];
        types[0] = getType();
        return types;
    }

	/** generate HTML for editing an object **/
	public String toHTML(Object o, Input<?> input) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException ;
	
	
	
	public abstract class Base implements BeasyHTMLInputEditor {
	    /**
	     * document that we are editing *
	     */
	    protected BeautiDoc doc;
	    
		public Base(BeautiDoc doc) {
			this.doc = doc;
		}

	}

}
