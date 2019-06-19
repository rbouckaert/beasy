package methods.objecteditor;

import java.lang.reflect.InvocationTargetException;

import beast.app.beauti.BeautiDoc;
import beast.core.Input;

public interface ObjectEditor {
	

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
	
	
	
	public abstract class Base implements ObjectEditor {
	    /**
	     * document that we are editing *
	     */
	    protected BeautiDoc doc;
	    
		public Base(BeautiDoc doc) {
			this.doc = doc;
		}

	}

}
