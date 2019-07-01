package beasy;

import java.io.*;
import java.util.List;

import beast.app.beauti.Beauti;
import beast.app.beauti.BeautiConfig;
import beast.app.beauti.BeautiDoc;
import beast.app.beauti.CAParsingException;
import beast.app.beauti.CompactAnalysisByAntlr;
import beast.app.beauti.SiteModelInputEditor;
import beast.core.MCMC;
import beast.core.Operator;
import beast.core.util.Log;
import beast.evolution.operators.DeltaExchangeOperator;
import beast.util.XMLProducer;

/** A simple Read-Eval-Print-Loop for Beasy: a Compact Analysis language for BEAST **/ 
public class BeasyREPL {
	BeautiDoc doc;

	public BeasyREPL() {
		doc = new BeautiDoc();
		doc.beautiConfig = new BeautiConfig();
		doc.beautiConfig.initAndValidate();
		
		// this sets a flag so classes think we are in BEAUti
		// which causes some classes to behave differently (in particular ThreadedTreeLikelihood)
		Beauti beauti = new Beauti(doc);
	}
	
	public BeasyREPL(BeautiDoc doc) {
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
		if (cmd == null) {
			return;
		}
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
	    	Operator operator = (DeltaExchangeOperator) doc.pluginmap.get("FixMeanMutationRatesOperator");
	    	if (operator != null) {
				List<Operator> operators = ((MCMC) doc.mcmc.get()).operatorsInput.get();
				if (SiteModelInputEditor.customConnector(doc)) {
					// connect DeltaExchangeOperator
					if (!operators.contains(operator)) {
						operators.add(operator);
					}
				} else {
					operators.remove(operator);
				}
			}
			doc.scrubAll(true, false);
			BeasyInterpreter.save(xmlProducer.toXML(mcmc), cmd, new File(strs[1]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showHelp(String cmd) {
		Help.doHelp(doc, cmd);
	}

	public static void main(String[] args) {
		System.out.println("A simple Read-Eval-Print-Loop for Beasy: a Compact Analysis language for BEAST");
		BeasyREPL repl = new BeasyREPL();
		repl.doREPL();
	}

}
