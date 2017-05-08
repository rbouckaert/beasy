package beasy;




import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import beast.app.beauti.BeautiConfig;
import beast.app.beauti.BeautiDoc;
import beast.app.beauti.BeautiSubTemplate;
import beast.core.BEASTInterface;
import beast.core.Input;
import beast.core.util.Log;
import beast.util.AddOnManager;
import beasy.shell.BeasyStudio;

public class Help {
	/**
	 *  used for displaying help, if provided 
	 */
	static public BeasyStudio studio = null;
	
	static public void help() {
		help("doc/html/index.html");
	}

	static public void help(String fileName) {
		try {
			showHelp(new URL("file:" + fileName));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	static private void showHelp(String string) {
		if (studio == null) {
			try {
				string = string.replaceAll("<[^>]*>", "");
				string = string.replaceAll("&lt;", "<");
				string = string.replaceAll("&gt;", ">");
				string = string.replaceAll("&quot;", "\"");
				string = string.replaceAll("&apos;", "'");
				string = string.replaceAll("&amp;", "&");
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
				Scanner scanner = new Scanner(url.openStream(), "UTF-8"); 
				String html = scanner.useDelimiter("\\A").next();
				scanner.close();
				showHelp(html);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			studio.helpPane.setURL(url);
			studio.rightLowerPaneTab.setSelectedIndex(0);
		}
	}

	

	public static void doHelp(BeautiDoc doc, String cmd) {
		cmd = cmd.trim();
		while (cmd.startsWith("?")) {
			cmd = cmd.substring(1);
		}
		cmd = cmd.trim();
		if (cmd.startsWith("help")) {
			cmd = cmd.substring(4);
		}
		cmd = cmd.trim();
		switch (cmd.toLowerCase()) {
		case "template":
			helpTemplate(doc, cmd);
			return;
		case "import":
			help("doc/html/import.html");
			return;
		case "sub":
			helpSub(doc, cmd);
			return;
		case "set":
			help("doc/html/set.html");
			return;
		case "link":
			help("doc/html/link.html");
			return;
		case "unlink":
			help("doc/html/unlink.html");
			return;
		case "save":
			help("doc/html/save.html");
			return;
		case "partition":
			help("doc/html/partition.html");
			return;
		}
		
		
		// generic help
		help();	
	}

	private static void helpSub(BeautiDoc doc, String cmd) {
		StringBuilder html = new StringBuilder();
		html.append("<html><h1>sub &lt;templateName></h1>\n\n");
		html.append("Instantiates a new template. Available templates are:</p>\n");
		html.append("<table>\n");
		if (doc.pluginmap != null && doc.pluginmap.containsKey(cmd)) {
			String pattern = ".*" + cmd + ".*";
			Map<Input, BEASTInterface> inputMap = new LinkedHashMap<>();
  			for (String id : doc.pluginmap.keySet()) {
				BEASTInterface o = doc.pluginmap.get(id);
				for (String name : o.getInputs().keySet()) {
					if ((id + "." + name).matches(pattern)) { // <=== match id + name
						Input<?> in = o.getInputs().get(name);
						if (in.getType() != null) { // && in.getType().isAssignableFrom(bo.getClass()) && in.canSetValue(bo, o)) {
							inputMap.put(in, o);
						}
					}
				}
			}
			if (inputMap.size() == 0) {
				// no match found -- try matching id only
				for (String id : doc.pluginmap.keySet()) {
					BEASTInterface o = doc.pluginmap.get(id);
					for (String name : o.getInputs().keySet()) {
						if ((id).matches(pattern)) { // <=== match id only
							Input<?> in = o.getInputs().get(name);
							if (in.getType() != null) { // && in.getType().isAssignableFrom(bo.getClass()) && in.canSetValue(bo, o)) {
								inputMap.put(in, o);
							}
						}
					}
				}
			}
			
	        for (BeautiSubTemplate subTemplate : doc.beautiConfig.subTemplates) {
	        	try {
					Class c = Class.forName(subTemplate.classInput.get());
					boolean found = false;
					for (Input input : inputMap.keySet()) {
						if (input.getType().isAssignableFrom(c)) {
							found = true;
							break;
						}
					}
					if (found) {
		        		html.append("<tr><td>" + subTemplate.getID() + "</td>" + "                     ".substring(Math.min(subTemplate.getID().length(), 20))+ "<td>" + subTemplate.getDescription() + "</td></tr>\n");
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }			
			
		} else {
			cmd = ".*" + cmd + ".*";
	        for (BeautiSubTemplate subTemplate : doc.beautiConfig.subTemplates) {
	        	if (subTemplate.getID().matches(cmd)) {
	        		html.append("<tr><td>" + subTemplate.getID() + "</td>" + "                     ".substring(Math.min(subTemplate.getID().length(), 20))+ "<td>" + subTemplate.getDescription() + "</td></tr>\n");
	        	}
	        }
			
		}		
        html.append("</table>\n");
        html.append("</html>\n");
        showHelp(html.toString());
	}

	private static void helpTemplate(BeautiDoc doc, String cmd) {
		List<String> templates = new ArrayList<>();
		StringBuilder html = new StringBuilder();
		html.append("<html><h1>template &lt;templateName></h1>\n\n");
		html.append("Switches to a new template. Available templates are:</p>\n");
		html.append("<table>\n");
		
        List<String> beastDirectories = AddOnManager.getBeastDirectories();
        for (String dirName : beastDirectories) {
            File dir = new File(dirName + "/templates");
            if (dir.exists() && dir.isDirectory()) {
                File[] files = dir.listFiles();
                if (files != null) {
                    for (File template : files) {
                        if (template.getName().toLowerCase().endsWith(".xml")) {
                            try {
                                String xml2 = BeautiDoc.load(template.getAbsolutePath());
                                if (xml2.contains("templateinfo=")) {
                                	String fileName = template.getName();
                                    fileName = fileName.substring(0, fileName.length() - 4);
                                    boolean duplicate = false;
                                	for (String name : templates) {
                                		if (name.equals(fileName)) {
                                			duplicate = true;
                                		}
                                	}
                                	if (!duplicate) {
                                		html.append(getTemplateInfo(template));
                                		templates.add(template.getAbsolutePath());
                                	}
                                }
                            } catch (Exception e) {
                            	Log.warning.println(e.getMessage());
                            }
                        }
                    }
                }
            }
        }
        
        html.append("</table>\n");
        html.append("</html>\n");
        showHelp(html.toString());
	}
	
	
	static String getTemplateInfo(File file) {
		String m_sFileName = file.getAbsolutePath();
	    String fileSep = System.getProperty("file.separator");
	    if (fileSep.equals("\\")) {
	        fileSep = "\\";
	    }
	    int i = m_sFileName.lastIndexOf(fileSep) + 1;
	    String name = m_sFileName.substring(
	            i, m_sFileName.length() - 4);
	    try {
	        DocumentBuilderFactory factory = DocumentBuilderFactory
	                .newInstance();
	        Document doc = factory.newDocumentBuilder().parse(file);
	        doc.normalize();
	        // get name and version of add-on
	        Element template = doc.getDocumentElement();
	        String templateInfo = template.getAttribute("templateinfo");
	        if (templateInfo == null || templateInfo.length() == 0) {
	            templateInfo = "switch to " + name + " template";
	        }
	        return "<tr><td>" + name + "</td>" + "                     ".substring(Math.min(name.length(), 20))+ "<td>" + templateInfo + "</tr>\n";
	    } catch (Exception e) {
	        // ignore
	    }
	    return  "<tr><td>" + name + "</td><td></tr>";
	}

	public static void main(String[] args) throws MalformedURLException {
		BeautiDoc doc = new BeautiDoc();
		doc.beautiConfig = new BeautiConfig();
		doc.beautiConfig.initAndValidate();
		Help.doHelp(doc, "sub");
	}

}
