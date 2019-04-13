package methods.implementation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import beast.app.beauti.BeautiDoc;
import beast.core.BEASTInterface;
import beast.core.Input;
import beast.core.util.Log;
import methods.MethodsText;
import methods.MethodsTextFactory;
import methods.Phrase;

public class BeautiSubTemplate {
	static private List<String[]> templates = null;
	static public Set<String> analysisIdentifiers = null;
	
	static public void initialise() {
		if (templates != null) {
			return;
		}
		templates = new ArrayList<>();
		analysisIdentifiers = new LinkedHashSet<>();
		
        // first gather the set of potential directories with templates
        Set<String> dirs = new HashSet<>();
        String pathSep = System.getProperty("path.separator");
        String classpath = System.getProperty("java.class.path");
        String fileSep = System.getProperty("file.separator");
        if (fileSep.equals("\\")) {
            fileSep = "\\\\";
        }
        dirs.add(".");
        for (String path : classpath.split(pathSep)) {
            path = path.replaceAll(fileSep, "/");
            if (path.endsWith(".jar")) {
                path = path.substring(0, path.lastIndexOf("/"));
            }
            if (path.indexOf("/") >= 0) {
                path = path.substring(0, path.lastIndexOf("/"));
            }
            if (!dirs.contains(path)) {
                dirs.add(path);
            }
        }
        
        String METHODS_CFG = "methods.cfg";

        // read methods.cfg, try all template directories
        Set<String> alreadySpecified = new LinkedHashSet<>();
        for (String dirName : dirs) {
            File cfgFile = new File(dirName + fileSep + METHODS_CFG);
            if (!cfgFile.exists()) {
                cfgFile = new File(dirName + fileSep + "templates" + fileSep + METHODS_CFG);
            }
            if (cfgFile.exists()) {
            	try {
            		processMethodsCfgFile(cfgFile, alreadySpecified);
            	} catch (IOException e) {
            		e.printStackTrace();
            	}
            }
        }
	}

	private static void processMethodsCfgFile(File cfgFile, Set<String> alreadySpecified) throws IOException {
		String cfg = BeautiDoc.load(cfgFile);
		String [] strs = cfg.split("\n");
		int i = 0;
		for (String str : strs) {
			if (str.startsWith("analysisIdentifier")) {
				String [] strs2 = str.split(",");
				analysisIdentifiers.add(strs2[1]);
			} else if (str.trim().length() > 0 && !str.trim().startsWith("#")) {
				String [] template = str.split(",");
				for (int j = 0; j < template.length; j++) {
					template[j] = template[j].replaceAll("&comma;", ",");
					if (template[j].startsWith("\"")) {
						template[j] = template[j].substring(1);
					}
					if (template[j].endsWith("\"")) {
						template[j] = template[j].substring(0, template[j].length()-1);
					}
				}
				if (!alreadySpecified.contains(template[0])) {
					templates.add(template);
					i++;
					alreadySpecified.add(template[0]);
				} 
			}
		}
		Log.warning("Added " + i + " method templates from " + cfgFile.getAbsolutePath());
	}

	static public List<Phrase> getModelDescription(Object o, BEASTInterface parent, Input<?> input, BeautiDoc doc) {
		initialise();
		
		if (o instanceof BEASTInterface) {
			BEASTInterface bi = (BEASTInterface) o;
			String [] match = match(bi);
			if (match == null) {
				return null;
			}
//			if (MethodsText.done.contains(bi)) {
//				return new ArrayList<>();
//			}
			MethodsText.done.add(bi);

			List<Phrase> m = new ArrayList<>();
			String str = match[1];
			m.add(new Phrase(o,parent,input, match[1]));
			for (int i = 2; i < match.length; i++) {
				str = match[i];
				if (str.contains("$(n)")) {
					beast.app.beauti.PartitionContext partition = doc.getContextFor(bi);
					str = BeautiDoc.translatePartitionNames(str, partition);
					BEASTInterface o2 = doc.pluginmap.get(str);
					List<Phrase> m2 = MethodsTextFactory.getModelDescription(o2, null, null, doc);
					m.addAll(m2);
				} else if (doc.pluginmap.containsKey(str)) {
					BEASTInterface o2 = doc.pluginmap.get(str);
					List<Phrase> m2 = MethodsTextFactory.getModelDescription(o2, null, null, doc);
					m.addAll(m2);
				} else {
					Input<?> input2 = hasInput(bi, str);
					if (input2 != null) {
						if (input2.get() != null) {
							List<Phrase> m2 = MethodsTextFactory.getModelDescription(input2.get(), bi, input2, doc);
							m.addAll(m2);
						}
					} else if (str.indexOf('@') > -1) {
						String [] strs = str.split("@");
						input2 = hasInput(bi, strs[1]);
						m.add(new Phrase(input2.get(), bi, input2, strs[0]));
					} else {
						m.add(new Phrase(str));
					}
				}
			}			
			return m;
		}
		return null; 
	}

	private static Input<?> hasInput(BEASTInterface bi, String str) {
		for (Input<?> input : bi.listInputs()) {
			if (input.getName().equals(str)) {
				return input;
			}
		}
		return null;
	}

	static private String[] match(BEASTInterface bi) {
		String fullID = bi.getID();
		String id;
		if (fullID.lastIndexOf('.') > 0) {
			id = fullID.substring(0, fullID.lastIndexOf('.'));
		} else {
			id = fullID;
		}
		String className = bi.getClass().getName();
		for (String [] str : templates) {
			String str0 = str[0];
			if (str0.lastIndexOf('.') > 0) {
				str0 = str0.substring(0, str0.lastIndexOf('.'));
			}
			if (str0.equals(id)) {
				return str;
			}
			if (str[0].equals(className)) {
				return str;
			}
			if (str[0].equals(fullID)) {
				return str;
			}
		}
		return null;
	}
	
	

}
