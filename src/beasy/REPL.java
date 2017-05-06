package beasy;

import java.io.*;

import beast.app.beauti.BeautiConfig;
import beast.app.beauti.BeautiDoc;
import beast.app.beauti.CAParsingException;
import beast.app.beauti.CompactAnalysisByAntlr;
import beast.core.MCMC;
import beast.core.util.Log;
import beast.util.XMLProducer;

/** A simple Read-Eval-Print-Loop for the Compact Analysis language for BEAST **/ 
public class REPL {
	BeautiDoc doc;

	public REPL() {
		doc = new BeautiDoc();
		doc.beautiConfig = new BeautiConfig();
		doc.beautiConfig.initAndValidate();
	}
	
	public REPL(BeautiDoc doc) {
		this.doc = doc;
	}

	public void doREPL() {
		while (true) {
			System.out.print(">");
			try {
				String cmd = (new BufferedReader(new InputStreamReader(System.in))).readLine();
				processCmd(cmd);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void processCmd(String cmd) {
		if (cmd.startsWith("?") || cmd.startsWith("help")) {
			showHelp(cmd);
		} else if (cmd.startsWith("quit") || cmd.startsWith("end") || cmd == null) {
			System.exit(0);
		} else if (cmd.startsWith("save")) {
			save(cmd);
		} else {
			try {
				CompactAnalysisByAntlr parser = new CompactAnalysisByAntlr(doc);
				parser.parseCA(cmd + ";");
			} catch (CAParsingException e) {
				Log.info(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace(Log.err);
				Log.info("Error: " + e.getMessage());
			}
		}
	}

	private void save(String cmd) {
		String [] strs = cmd.trim().split("\\s+");
		if (strs.length != 2) {
			Log.warning("Expected 'save <filename>' but got " + cmd);
			Log.warning("File is not saved");
			return;
		}
		try {
			XMLProducer xmlProducer = new XMLProducer();
			MCMC mcmc = (MCMC) doc.mcmc.get();

			FileWriter outfile = new FileWriter(new File(strs[1]));
	        outfile.write(xmlProducer.toXML(mcmc));
	        outfile.close();
	        System.err.println("Results in " + strs[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showHelp(String cmd) {
		Log.info("Commands are one per line, and must be one of the following");
		Log.info("template <template name>     instantiate a template (e.g. StarBeast)");
		Log.info("import <subtemplate>? file   import data using template <subtemplate> if non-standard template is required");
		Log.info("(idpattern =)? sub <subtemplate>(key1=value1,key2=value2,...)");
		Log.info("partition partitionpattern");
		Log.info("link (clock|site|tree)       link selected partitions");
		Log.info("unlink (clock|site|tree)     unlink selected partitions");
		Log.info("? or help                    show help message");
		Log.info("save <filename>              save file");
		Log.info("quit or end                  exit program");
	}

	public static void main(String[] args) {
		System.out.println("A simple Read-Eval-Print-Loop for the Compact Analysis language for BEAST");
		REPL repl = new REPL();
		repl.doREPL();
	}

}
