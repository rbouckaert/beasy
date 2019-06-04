package beast.app.beauti;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import beast.core.MCMC;
import methods.MethodsText;
import methods.Phrase;
import methods.XML2Text;

/** Custom menu for BEAUti, which appears as "Help => Methods Section" menu item **/
public class MethodsSectionHelpMenu extends BeautiHelpAction {
	private static final long serialVersionUID = 1L;
	
	BeautiDoc doc;
	
	public MethodsSectionHelpMenu(BeautiDoc doc) {
		super("Methods section", "Attempts to convert model into a methods section", "methods", -1);
		this.doc = doc;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		beast.core.Runnable runnable = doc.mcmc.get();
		if (runnable instanceof MCMC) {
			JFrame frame = doc.getFrame();
			if (frame != null) frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MCMC mcmc = (MCMC) runnable;
			MethodsText.initNameMap();
			XML2Text xml2textProducer = new XML2Text(doc);
			try {
				xml2textProducer.initialise(mcmc);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<Phrase> m = xml2textProducer.getPhrases();
			String text = Phrase.toText(doc, m);
			if (frame != null) frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        	JTextArea textArea = new JTextArea(text);
        	textArea.setRows(40);
        	textArea.setColumns(50);
        	textArea.setLineWrap(true);
        	textArea.setEditable(true);
        	JScrollPane scroller = new JScrollPane(textArea);
        	JOptionPane.showMessageDialog(doc.getFrame(), scroller);
		}
	}

}
