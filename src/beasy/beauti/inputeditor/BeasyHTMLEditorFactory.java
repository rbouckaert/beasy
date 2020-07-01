package beasy.beauti.inputeditor;



import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import beast.app.beauti.BeautiDoc;
import beast.core.BEASTInterface;
import beast.core.Input;
import beast.core.util.Log;
import beast.util.BEASTClassLoader;
import beast.util.PackageManager;

public class BeasyHTMLEditorFactory {
    /**
     * map that identifies the InputEditor to use for a particular type of Input *
     */
    HashMap<Class<?>, String> BeasyHTMLInputEditorMap;
    HashMap<Class<?>, String> listBeasyHTMLInputEditorMap;

	public void init(BeautiDoc doc) {
		if (BeasyHTMLInputEditorMap != null) {
			return;
		}
		BeasyHTMLInputEditorMap = new LinkedHashMap<>();
		listBeasyHTMLInputEditorMap = new LinkedHashMap<>();
		String[] PACKAGE_DIRS = { "beasy.beauti.inputeditor", };
		for (String packageName : PACKAGE_DIRS) {
			List<String> inputEditors = PackageManager.find("beasy.beauti.inputeditor.BeasyHTMLInputEditor", packageName);
			registerInputEditors(inputEditors.toArray(new String[0]), doc);
		}
	}

	private void registerInputEditors(String[] inputEditors, BeautiDoc doc) {
		// BeautiDoc doc = new BeautiDoc();
		for (String inputEditor : inputEditors) {
			// ignore inner classes, which are marked with $
			if (!inputEditor.contains("$")) {
				try {
					Class<?> _class = BEASTClassLoader.forName(inputEditor);

	                Constructor<?> con = _class.getConstructor(BeautiDoc.class);
	                BeasyHTMLInputEditor editor = (BeasyHTMLInputEditor) con.newInstance(doc);

					// InputEditor editor = (InputEditor) _class.newInstance();
					Class<?>[] types = editor.getTypes();
					for (Class<?> type : types) {
						BeasyHTMLInputEditorMap.put(type, inputEditor);
						if (editor instanceof ListBeasyHTMLInputEditor) {
							Class<?> baseType = ((ListBeasyHTMLInputEditor) editor).getBaseType();
							listBeasyHTMLInputEditorMap.put(baseType, inputEditor);
						}
					}
				} catch (java.lang.InstantiationException e) {
					// ignore input editors that are inner classes
				} catch (Throwable e) {
					// print message
					Log.err.println(e.getClass().getName() + ": " + e.getMessage());
				}
			}
		}
	}

	public BeasyHTMLInputEditor getBeasyHTMLInputEditor(BEASTInterface o, Input<?> input, BeautiDoc doc) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (input.getType() == null) {
            input.determineClass(o);
        }
		
        Class<?> inputClass = input.getType();
        if (inputClass == null) {
        	return null;
        }
    	if (input.get() != null && !input.get().getClass().equals(inputClass)
    			&& !(input.get() instanceof ArrayList)) {
    		Log.trace.println(input.get().getClass() + " != " + inputClass);
    		inputClass = input.get().getClass();
    	}

        //Log.trace.print(inputClass.getName() + " => ");
        BeasyHTMLInputEditor BeasyHTMLInputEditor = null;


        if (List.class.isAssignableFrom(inputClass) ||
                (input.get() != null && input.get() instanceof List<?>)) {
            // handle list inputs
            if (listBeasyHTMLInputEditorMap.containsKey(inputClass)) {
                // use custom list input editor
                String inputEditorName = listBeasyHTMLInputEditorMap.get(inputClass);
                Constructor<?> con = BEASTClassLoader.forName(inputEditorName).getConstructor(BeautiDoc.class);
                BeasyHTMLInputEditor = (BeasyHTMLInputEditor) con.newInstance(doc);

                //inputEditor = (InputEditor) BEASTClassLoader.forName(inputEditor).newInstance();
            } else {
                // otherwise, use generic list editor
                BeasyHTMLInputEditor = new ListBeasyHTMLInputEditor(doc);
            }
        } else if (input.possibleValues != null) {
            // handle enumeration inputs
            BeasyHTMLInputEditor = new EnumBeasyHTMLInputEditor(doc);
        } else {
        	Class<?> inputClass2 = inputClass;
        	while (inputClass2 != null && !BeasyHTMLInputEditorMap.containsKey(inputClass2)) {
        		inputClass2 = inputClass2.getSuperclass(); 
        	}
        	if (inputClass2 == null) {
        		BeasyHTMLInputEditor = new BEASTObjectBeasyHTMLInputEditor(doc, this);
        	} else {
	            // handle BEASTObject-input with custom input editors
	            String inputEditorName = BeasyHTMLInputEditorMap.get(inputClass2);
	            
	            Constructor<?> con = BEASTClassLoader.forName(inputEditorName).getConstructor(BeautiDoc.class);
	            BeasyHTMLInputEditor = (BeasyHTMLInputEditor) con.newInstance(doc);
        	}
        }        	
        return BeasyHTMLInputEditor;
	}
	
	
	
}
