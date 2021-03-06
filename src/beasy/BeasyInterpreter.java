package beasy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import beast.app.beauti.Beauti;
import beast.app.beauti.BeautiConfig;
import beast.app.beauti.BeautiDoc;
import beast.app.beauti.CAParsingException;
import beast.app.beauti.CompactAnalysisByAntlr;
import beast.app.util.Application;
import beast.app.util.OutFile;
import beast.core.Description;
import beast.core.Input;
import beast.core.Runnable;
import beast.core.util.Log;
import beast.util.ClassToPackageMap;

@Description("Runs a Beasy file, and save output to XML")
public class BeasyInterpreter extends Runnable {
	final public Input<InFile> inputFileInput = new Input<>("in", "Input file with BEASY specification.");
	final public Input<OutFile> outFileInput = new Input<>("out", "Output XML file. If not specified, input file will be used with .xml extension");
	final public Input<List<String>> definitionsInput = new Input<>("D", "attribute-value pairs to be replaced in the Beasy Script, e.g., -D \"arg1=10,arg2=20\"", new ArrayList<>());
    public enum MODE {
        overwrite, ask, dontoverwrite
    }
    final public Input<MODE> modeInput = new Input<>("mode", "logging mode, one of " + Arrays.toString(MODE.values()), MODE.dontoverwrite, MODE.values());

		
	@Override
	public void initAndValidate() {
	}

	
	@Override
	public void run() throws Exception {
		
		
		validateInputs();
		
		File out = outFileInput.get();
		if (out == null || out.getName().equals("[[none]]")) {
			String fileName = inputFileInput.get().getName();
			fileName = fileName.substring(0, fileName.lastIndexOf('.'));
			out = new File(fileName + ".xml");
		}
		
		if (modeInput.get() == MODE.dontoverwrite) {
			if (out.exists()) {
				Log.err("File " + out.getPath() + " already exists -- try another output file name, or change mode to overwrite");
				System.exit(0);
			}
		}
		
		BeautiDoc doc = new BeautiDoc();
		doc.beautiConfig = new BeautiConfig();
		doc.beautiConfig.initAndValidate();
		// this sets a flag so classes think we are in BEAUti
		// which causes some classes to behave differently (in particular ThreadedTreeLikelihood)
		Beauti beauti = new Beauti(doc);  

		String script = BeautiDoc.load(inputFileInput.get());
		
		script = processDefinitions(script);
		
		
		
		try {
			CompactAnalysisByAntlr parser = new CompactAnalysisByAntlr(doc);
			parser.parseCA(script + ";");

			String XML = doc.toXML();
			save(XML, script, out);
		} catch (CAParsingException e) {
			Log.info(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(Log.err);
			Log.info("Error: " + e.getMessage());
		}
		
	}
	
	private String processDefinitions(String script) {
		for (String arg : definitionsInput.get()) {
	        String [] strs = arg.split("=",-1);
	        for (int eqIdx = 0; eqIdx<strs.length-1; eqIdx++) {
	            int lastCommaIdx = strs[eqIdx].lastIndexOf(",");
	
	            if (lastCommaIdx != -1 && eqIdx == 0)
	                throw new IllegalArgumentException("Argument to -D is not well-formed: expecting comma-separated name=value pairs");
	
	            String name = strs[eqIdx].substring(lastCommaIdx+1);
	
	            lastCommaIdx = strs[eqIdx+1].lastIndexOf(",");
	            String value;
	            if (eqIdx+1 == strs.length-1) {
	                value = strs[eqIdx+1];
	            } else {
	                if (lastCommaIdx == -1)
	                    throw new IllegalArgumentException("Argument to -D is not well-formed: expecting comma-separated name=value pairs");
	
	                value = strs[eqIdx+1].substring(0, lastCommaIdx);
	            }
	            script = script.replaceAll("\\$\\(" + name + "\\)", value);
			}
		}
		return script;
	}


	public static String beasyVersion() {
		Map<String, String > classToPackageMap = ClassToPackageMap.getClassToPackageMap();
		for (String s : classToPackageMap.keySet()) {
			if (s.startsWith("beasy")) {
				System.out.println(s + " " + classToPackageMap.get(s));
			}
		}
		String packageVersion = classToPackageMap.get(BeasyInterpreter.class.getName());
		return packageVersion;
	}
	
	static void save(String XML, String script, File out) throws IOException {
    	String packageVersion = beasyVersion();

        StringBuilder buf = new StringBuilder();
        buf.append("\n\n<!-- Generated with Beasy " + packageVersion + " -->\n\n");
        buf.append("<!--\n");
        buf.append(script);
        buf.append("-->\n\n");
        XML = XML.replaceFirst("\n", buf.toString());
        FileWriter outfile = new FileWriter(out);
        outfile.write(XML);
        outfile.close();

	}


	public static void main(String[] args) throws Exception {
		new Application(new BeasyInterpreter(), "BEASY Interpreter", args);
	}
}
