package beasy.shell;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;

import beast.app.DocMaker;
import beast.core.BEASTObject;
import beast.util.AddOnManager;

public class Help {
	/**
	 *  used for displaying help, if provided 
	 */
	static BeasyStudio studio = null;

    /** 
help(beast.util.TreeParser);

     * generates HTML pages *
     */
    static DocMaker m_docMaker = HelpBrowser.docMaker;
	
	
	static public void help() {
		showHelp("<html>" +
"<h1>Documentation</h1>\n" +
"\n" +
"<h2>Description:</h2>\n" +
"\n" +
"     ‘help’ is the primary interface to the help systems.\n" +
"\n" +
"<h2>Usage:</h2>\n" +
"\n" +
"     help(command)<br>\n" +
"     help(class)\n" +
"     \n" +
"<h2>Arguments:</h2>\n" +
"\n" +
"    command: show help for a command\n" +
"\n" +
"    class: show help for class if it is a BEASTObject<br>\n" +
"    otherwise show the class browser.\n" +
"\n" +
"</html>");
	}

	static public void help(Object o) {
		if (o instanceof BEASTObject) {
			try {
				if (m_docMaker == null) {
					m_docMaker = HelpBrowser.docMaker;
				}
				
				String help = m_docMaker.getHTML(o.getClass().getName(), false);
				showHelp(help);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String name = o.getClass().getCanonicalName();
			name = name.replaceAll("\\.", "/");
			name = "http://docs.oracle.com/javase/7/docs/api/" + name + ".html";
			try {
				URL url = new URL(name);
				showHelp(url);
				return;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			// show the class browser
			studio.rightLowerPaneTab.setSelectedIndex(1);
//			studio.classBrowser.setClist(o.getClass().getPackage().getName());
//			studio.classBrowser.setMlist(o.getClass().getSimpleName());
			showHelp("No help available for " + o);
		}

	}

	static private void showHelp(String string) {
		if (studio == null) {
			try {
				string = string.replaceAll("<[^>]*>", "");
				System.out.println(string);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			studio.helpPane.setText(string);
			studio.rightLowerPaneTab.setSelectedIndex(0);
		}
	}

	static private void showHelp(URL url) {
		if (studio == null) {
			try {
				System.out.println(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			studio.helpPane.setURL(url);
			studio.rightLowerPaneTab.setSelectedIndex(0);
		}
	}


    static boolean canLoadPage(String docPage) {
    	String page = docPage;
    	if (page.contains("#")) {
    		page = page.substring(0, page.indexOf('#'));
    	}
    	if (new File(page).exists()) {
        	String path = new File(".").getAbsolutePath();
           	try {
           		studio.helpPane.setURL(new URL("file://" + path  + "/" + docPage));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
           	return true;
    	} else if(new File(AddOnManager.getPackageUserDir() + BeasyStudio.PACKAGENAME + page).exists()) {
        	try {
        		studio.helpPane.setURL(new URL("file://" + AddOnManager.getPackageUserDir() + BeasyStudio.PACKAGENAME + docPage));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
           	return true;
    	}
    	return false;
	}


	
	public static void main(String[] args) {
		Help.help();
	}
}
