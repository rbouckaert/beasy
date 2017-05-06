package beasy.shell;

import java.io.File;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Utils {
    public static File getLoadFile(String message, File defaultFileOrDir, String description, final String... extensions) {
        File[] files = getFile(message, true, defaultFileOrDir, false, description, extensions);
        if (files == null) {
            return null;
        } else {
            return files[0];
        }
    }
    public static File getSaveFile(String message, File defaultFileOrDir, String description, final String... extensions) {
        File[] files = getFile(message, false, defaultFileOrDir, false, description, extensions);
        if (files == null) {
            return null;
        } else {
            return files[0];
        }
    }

    public static File[] getLoadFiles(String message, File defaultFileOrDir, String description, final String... extensions) {
        return getFile(message, true, defaultFileOrDir, true, description, extensions);
    }

    public static File[] getFile(String message, boolean bLoadNotSave, File defaultFileOrDir, boolean bAllowMultipleSelection, String description, final String... extensions) {
    	
    	FileChooser chooser = new FileChooser();
    	chooser.setInitialDirectory(defaultFileOrDir);
    	chooser.setTitle(message);
    	
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(description, extensions));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All files", "*.*"));
        if (bLoadNotSave) {
        	if (bAllowMultipleSelection) {
	        	List<File> f = chooser.showOpenMultipleDialog(new Stage());
        		return f.toArray(new File[0]);
        	} else {
	        	File f = chooser.showOpenDialog(new Stage());
	    		return new File[]{f};
        	}
        } else {
        	File f = chooser.showSaveDialog(new Stage());
    		return new File[]{f};
        }
    }

}
