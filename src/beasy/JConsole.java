/*****************************************************************************
 *                                                                           *
 *  Documentation and updates may be found at http://www.beanshell.org/      *
 *                                                                           *
 *  Sun Public License Notice:                                               *
 *                                                                           *
 *  The contents of this file are subject to the Sun Public License Version  *
 *  1.0 (the "License"); you may not use this file except in compliance with *
 *  the License. A copy of the License is available at http://www.sun.com    * 
 *                                                                           *
 *  The Original Code is BeanShell. The Initial Developer of the Original    *
 *  Code is Pat Niemeyer. Portions created by Pat Niemeyer are Copyright     *
 *  (C) 2000.  All Rights Reserved.                                          *
 *                                                                           *
 *  GNU Public License Notice:                                               *
 *                                                                           *
 *  Alternatively, the contents of this file may be used under the terms of  *
 *  the GNU Lesser General Public License (the "LGPL"), in which case the    *
 *  provisions of LGPL are applicable instead of those above. If you wish to *
 *  allow use of your version of this file only under the  terms of the LGPL *
 *  and not to allow others to use your version of this file under the SPL,  *
 *  indicate your decision by deleting the provisions above and replace      *
 *  them with the notice and other provisions required by the LGPL.  If you  *
 *  do not delete the provisions above, a recipient may use your version of  *
 *  this file under either the SPL or the LGPL.                              *
 *                                                                           *
 *  Patrick Niemeyer (pat@pat.net)                                           *
 *  Author of Learning Java, O'Reilly & Associates                           *
 *  http://www.pat.net/~pat/                                                 *
 *                                                                           *
 *****************************************************************************/

package	beasy;


import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.awt.Cursor;

import javax.swing.text.*;

import beast.app.beauti.BeautiDoc;
import beast.app.beauti.BeautiSubTemplate;
import beast.app.beauti.CompactAnalysisByAntlr;
import beast.app.beauti.InputFilter;
import beast.app.beauti.PriorProvider;
import beast.app.util.Utils;
import beast.core.BEASTInterface;
import beast.core.Input;
import beast.core.parameter.Parameter;
import beast.core.util.Log;
import beast.evolution.alignment.Alignment;
import beast.evolution.likelihood.GenericTreeLikelihood;
import beast.util.PackageManager;
import beasy.shell.BeasyStudio;
import beasy.shell.HistoryPanel;

import javax.swing.*;


/**
	A JFC/Swing based console for the BeanShell desktop.
	This is a descendant of the old AWTConsole.

	Improvements by: Mark Donszelmann <Mark.Donszelmann@cern.ch>
		including Cut & Paste

  	Improvements by: Daniel Leuck
		including Color and Image support, key press bug workaround
*/
public class JConsole extends JScrollPane
	implements Runnable, KeyListener,
	MouseListener, ActionListener, PropertyChangeListener 
{
	private static final long serialVersionUID = 1L;
	private final static String	CUT = "Cut";
    private final static String	COPY = "Copy";
    private final static String	PASTE =	"Paste";

	private	OutputStream outPipe;
	private static boolean suppressOutput = false;
	public static void setSuppressOutput(boolean suppressOutput) {JConsole.suppressOutput = suppressOutput;}
	private	InputStream inPipe;
	private	InputStream in;
	private	PrintStream out;

	public InputStream getInputStream() { return in; }
	public Reader getIn() { return new InputStreamReader(in); }
	public PrintStream getOut() { return out;	}
	public PrintStream getErr() { return out;	}

    private int	cmdStart = 0;
	private	List<String> history = new ArrayList<>();
	public HistoryPanel historyPanel = null;
	private	String startedLine;
	private	int histLine = 0;

    private JPopupMenu menu;
    public JTextPane text;
    private DefaultStyledDocument docStyle;
    
    static Semaphore mutex = new Semaphore(1);

//	NameCompletion nameCompletion;
	final int SHOW_AMBIG_MAX = 10;

	// hack to prevent key repeat for some reason?
    private boolean gotUp = true;

    public BeasyStudio studio = null;
    
    
	// list of available templates
    List<String> templates;

    public JConsole() {
		this(null, null);
	}
	
	public JConsole(BeasyStudio studio) {
		this(null, null);
		this.studio = studio;
	}

	public JConsole( InputStream cin, OutputStream cout )  
	{
		super();

		// Special TextPane which catches for cut and paste, both L&F keys and
		// programmatic	behaviour
		text = new JTextPane( docStyle=new DefaultStyledDocument() ) 
			{
				@Override
				public void	cut() {
					if (text.getCaretPosition() < cmdStart)	{
						super.copy();
					} else {
						super.cut();
					}
				}

				@Override
				public void	paste()	{
					forceCaretMoveToEnd();
					super.paste();
				}
			};

		Font font = new	Font("Monospaced",Font.PLAIN,14);
		text.setText("");
		text.setFont( font );
		text.setMargin(	new Insets(7,5,7,5) );
		text.addKeyListener(this);
		setViewportView(text);

		// create popup	menu
		menu = new JPopupMenu("JConsole	Menu");
		menu.add(new JMenuItem(CUT)).addActionListener(this);
		menu.add(new JMenuItem(COPY)).addActionListener(this);
		menu.add(new JMenuItem(PASTE)).addActionListener(this);

		text.addMouseListener(this);

		// make	sure popup menu	follows	Look & Feel
		UIManager.addPropertyChangeListener(this);

		outPipe	= cout;
		if ( outPipe ==	null ) {
			outPipe	= new PipedOutputStream();
			try {
				in = new PipedInputStream((PipedOutputStream)outPipe);
			} catch	( IOException e	) {
				print("Console internal	error (1)...", Color.red);
			}
		}

		inPipe = cin;
		if ( inPipe == null ) {
			PipedOutputStream pout = new PipedOutputStream();
			out = new PrintStream( pout );
			try {
				inPipe = new BlockingPipedInputStream(pout);
			} catch ( IOException e ) { print("Console internal error: "+e); }
		}
		// Start the inpipe watcher
		new Thread( this ).start();
		
		requestFocus();
	}

	@Override
	public void requestFocus() 
	{
		super.requestFocus();
		text.requestFocus();
	}

	@Override
	public void keyPressed(	KeyEvent e ) {
	    type( e );
	    gotUp=false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	    type( e );
	}

    @Override
	public void	keyReleased(KeyEvent e)	{
		gotUp=true;
		type( e	);
    }

    private synchronized void type( KeyEvent e ) {
		switch ( e.getKeyCode()	) 
		{
			case ( KeyEvent.VK_ENTER ):
			    if (e.getID() == KeyEvent.KEY_PRESSED) {
					if (gotUp) {
						enter();
						resetCommandStart();
						text.setCaretPosition(cmdStart);
					}
				}
				e.consume();
				text.repaint();
				break;

			case ( KeyEvent.VK_UP ):
			    if (e.getID() == KeyEvent.KEY_PRESSED) {
				    historyUp();
				}
				e.consume();
				break;

			case ( KeyEvent.VK_DOWN	):
			    if (e.getID() == KeyEvent.KEY_PRESSED) {
					historyDown();
				}
				e.consume();
				break;

			case ( KeyEvent.VK_LEFT	):
			case ( KeyEvent.VK_BACK_SPACE ):
			case ( KeyEvent.VK_DELETE ):
				if (text.getCaretPosition() <= cmdStart) {
					// This doesn't work for backspace.
					// See default case for workaround
					e.consume();
				}
				break;

			case ( KeyEvent.VK_RIGHT ):
				forceCaretMoveToStart();
				break;

			case ( KeyEvent.VK_HOME ):
				text.setCaretPosition(cmdStart);
				e.consume();
				break;

			case ( KeyEvent.VK_U ):	// clear line
				if ( (e.getModifiers() & InputEvent.CTRL_MASK) > 0 ) {
					replaceRange( "", cmdStart, textLength());
					histLine = 0;
					e.consume();
				}
				break;

			case ( KeyEvent.VK_ALT ):
			case ( KeyEvent.VK_CAPS_LOCK ):
			case ( KeyEvent.VK_CONTROL ):
			case ( KeyEvent.VK_META ):
			case ( KeyEvent.VK_SHIFT ):
			case ( KeyEvent.VK_PRINTSCREEN ):
			case ( KeyEvent.VK_SCROLL_LOCK ):
			case ( KeyEvent.VK_PAUSE ):
			case ( KeyEvent.VK_INSERT ):
			case ( KeyEvent.VK_F1):
			case ( KeyEvent.VK_F2):
			case ( KeyEvent.VK_F3):
			case ( KeyEvent.VK_F4):
			case ( KeyEvent.VK_F5):
			case ( KeyEvent.VK_F6):
			case ( KeyEvent.VK_F7):
			case ( KeyEvent.VK_F8):
			case ( KeyEvent.VK_F9):
			case ( KeyEvent.VK_F10):
			case ( KeyEvent.VK_F11):
			case ( KeyEvent.VK_F12):
			case ( KeyEvent.VK_ESCAPE ):

			// only	modifier pressed
			break;

			// Control-C
			case ( KeyEvent.VK_C ):
				if (text.getSelectedText() == null) {
				    if (( (e.getModifiers() & InputEvent.CTRL_MASK) > 0	)
					&& (e.getID() == KeyEvent.KEY_PRESSED))	{
						append("^C");
					}
					e.consume();
				}
				break;

			case ( KeyEvent.VK_TAB ):
				e.consume();
			    if (e.getID() == KeyEvent.KEY_RELEASED) {
					String part = text.getText().substring( cmdStart );
					completeCommand( part );
					//append(part);
				}
				break;

			default:
				if ( 
					(e.getModifiers() & 
					(InputEvent.CTRL_MASK 
					| InputEvent.ALT_MASK | InputEvent.META_MASK)) == 0 ) 
				{
					// plain character
					forceCaretMoveToEnd();
				}

				/*
					The getKeyCode function always returns VK_UNDEFINED for
					keyTyped events, so backspace is not fully consumed.
				*/
				if (e.paramString().indexOf("Backspace") != -1)
				{ 
				  if (text.getCaretPosition() <= cmdStart) {
						e.consume();
						break;
					}
				}

				break;
		}
	}


	private void completeCommand(final String part0 ) {
//		Log.info(part0);
		String [] cmds = new String[]{"template", "import", "link", "unlink", "rm", "add", "set", "use", "taxonset", "rename", "mode"};
		String part = part0.trim();
		
		if (part.length() == 0) {
			if (studio.interpreter.doc.beautiConfig.alignmentProvider.size() == 0) {
				// no template loaded yet
				replaceRange("template ", cmdStart, textLength());
				return;
			}
			printHint("\nChoose one of " + Arrays.toString(cmds), Color.blue);
			return;
		}
		
		// complete command
		for (String cmd : cmds) {
			if (cmd.startsWith(part) && !cmd.equals(part)) {
				replaceRange(cmd + " ", cmdStart, textLength());
				return;
			}
		}
		
		// complete template
		if (part.startsWith("template")) {
			completeTemplate(part);
			return;
		}

		if (part.startsWith("import")) {
			completeImport(part);
			return;
		}

		if (part.startsWith("taxonset")) {
			completeTaxonset(part);
			return;
		}

		if (part.startsWith("rename")) {
			completeRename(part);
			return;
		}

		if (part.startsWith("add")) {
			completeAdd(part);
			return;
		}

		if (part.startsWith("link") || part.startsWith("unlink")) {
			completeLinkOrUnLink(part);
			return;
		}

		
		if (part.startsWith("mode")) {
			completeMode(part);
			return;
		}
		
		if (part.startsWith("set") || part.startsWith("use") || part.startsWith("rm")) {
			if (part.indexOf('=') > 0) {
				if (part.startsWith("use")) {
					completeSubtemplate(part);
				}
				return;
			}
			if (part.startsWith("rm")) {
				part = part.substring(2).trim();
			} else {
				part = part.substring(3).trim();
			}
			
			
			// complete a partitionPattern 
			if (part.indexOf("{") >= 0) {
				if (part.indexOf('}') > 0) {
					replaceRange(part0 + " = ", cmdStart, textLength());
					return;
				}
				String partitionPattern = part.substring(part.indexOf("{") + 1).trim();
				String partition = partitionPattern;
				if (partitionPattern.indexOf(',') > 0) {
					String [] strs = partitionPattern.split(",");
					partition = strs[strs.length - 1].trim();
				}
				partitionPattern = part0.substring(0, part0.length() - partition.length());
				
				Set<String> matcheSet = new HashSet<>();
				BeautiDoc doc = studio.interpreter.doc;
				List<BEASTInterface> bos = doc.getPartitions("Partitions");
				bos.addAll(doc.getPartitions("SiteModel"));
				bos.addAll(doc.getPartitions("ClockModel"));
				bos.addAll(doc.getPartitions("TreeModel"));
				for (BEASTInterface o : bos) {
					if (o.getID().startsWith(partition)) {
						matcheSet.add(o.getID());
					}
				}
				List<String> matches = new ArrayList<>();
				matches.addAll(matcheSet);
				completeMatch(matches, partition, partitionPattern, ", ", "No partition matches ");
//				if (matches.size() == 1) {				
//					replaceRange(partitionPattern + matches.get(0) +", ", cmdStart, textLength());
//					return;
//				}
//				if (matches.size() == 0) {
//					printHint("\nNo partition matches " + partition, Color.blue);
//					return;
//				}
//				String prefix = getLargestCommonPrefix(matches);
//				if (prefix.length() > partition.length()) {
//					replaceRange(partitionPattern + prefix, cmdStart, textLength());
//				}
//				printHint("\nChoose one of: " + Arrays.toString(matches.toArray()), Color.blue);		
				return;
			}
			
			// complete an idPattern 
			if (part.indexOf("[") >= 0 && part.indexOf(']') < 0) {
				int k = part.indexOf("[");
				String id = part.substring(k + 1).trim();
				part = part0.substring(0, part0.indexOf('['));

				BeautiDoc doc = studio.interpreter.doc;
				List<String> matches = new ArrayList<>();
				for (String id_ : doc.pluginmap.keySet()) {
					if (id_.startsWith(id)) {
						matches.add(id_);
					}
				}
				completeMatch(matches, id, part +'[', "]", "No object id matches ");
//				if (matches.size() == 1) {				
//					replaceRange(part +'[' + matches.get(0) +"] ", cmdStart, textLength());
//					return;
//				}
//				if (matches.size() == 0) {
//					printHint("\nNo object id matches " + id, Color.blue);
//					return;
//				}
//				String prefix = getLargestCommonPrefix(matches);
//				if (prefix.length() > id.length()) {
//					replaceRange(part + '[' + prefix, cmdStart, textLength());
//				}
//				printHint("\nChoose one of: " + Arrays.toString(matches.toArray()), Color.blue);		
				return;			
			}

			// complete an inputname
			if (part.indexOf("@") >= 0) {
				int k = part.indexOf("@");
				String elementPart = part.substring(0, k).trim();
				String inputPart = part.substring(k+1).trim();
				part = part0.substring(0, part0.indexOf('@'));

				BeautiDoc doc = studio.interpreter.doc;
				Set<String> matchSet = new HashSet<>();
				for (BEASTInterface o : doc.pluginmap.values()) {
					for (Input<?> input : o.listInputs()) {
						if (input.getName().equals(elementPart)) {
							if (input.get() != null && input.get() instanceof BEASTInterface) {
								BEASTInterface o2 = (BEASTInterface) input.get();
								for (Input<?> input2 : o2.listInputs()) {
									if (input2.getName().startsWith(inputPart)) {
										matchSet.add(input2.getName());
									}
								}
							}
						}
					}
				}
				List<String> matches = new ArrayList<>();
				matches.addAll(matchSet);
				completeMatch(matches, inputPart, part +'@', "No input name matches ");
//				if (matches.size() == 1) {				
//					replaceRange(part +'@' + matches.get(0), cmdStart, textLength());
//					return;
//				}
//				if (matches.size() == 0) {
//					printHint("\nNo input name matches " + inputPart, Color.blue);
//					return;
//				}
//				String prefix = getLargestCommonPrefix(matches);
//				if (prefix.length() > inputPart.length()) {
//					replaceRange(part + '@' + prefix, cmdStart, textLength());
//				}
//				printHint("\nChoose one of: " + Arrays.toString(matches.toArray()), Color.blue);		
				return;			
			}

			
			int len = part.length();
			String elementPattern = null;
			String idPattern = null;
			String inputPattern = null;
			if (part.trim().length() > 0) {
				inputPattern = part + ".*";
			}
			if (elementPattern == null && idPattern == null && inputPattern == null) {
				inputPattern = ".*";
			}
			InputFilter filter = new InputFilter();
			BeautiDoc doc = studio.interpreter.doc;
			Map<Input<?>, BEASTInterface> map = InputFilter.initInputMap(doc);
			Set<Input<?>> inputs = filter.getInputSet(doc, idPattern, elementPattern, inputPattern);
			
			Log.info("");
			for (Input<?> input : inputs) {
				BEASTInterface o = map.get(input);
				if (input.get() instanceof Parameter.Base) {
					Log.info(input.getName() + "[" + o.getID() + "] = " + ((Parameter.Base<?>) input.get()).valuesInput.get());
				} else {
					Log.info(input.getName() + "[" + o.getID() + "] = " + input.get());
				}
			}
			studio.rightLowerPaneTab.setSelectedIndex(1);
			

			// calc greatest common denominator for all matching inputs
			String gcd = null;
			int gcdlen = 0;
			for (Input<?> input : inputs) {
			    BEASTInterface o = map.get(input);
				if (gcd == null) {
					gcd = (input.getName() + "[" + o.getID() + "]");
					gcdlen = gcd.length();
				} else {
					gcd = gcd(gcd, input.getName() + "[" + o.getID() + "]");
				}
			}
			if (gcd != null && gcd.length() > len) {
			    // unique identifier, so we can complete the pattern

				// delete "tab" in text
				int slen = text.getDocument().getLength();
				try {
					text.getDocument().remove(slen-1, 0);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				append(gcd.substring(len) + (gcdlen == gcd.length() ? " = " : ""));
			}
		}
	}

	
	private void completeMode(String part) {
		String [] strs = part.trim().split("\\s+");
		if (strs.length == 1) {
			replaceRange("mode auto", cmdStart, textLength());
			return;
		}
		
		if (strs.length == 2) {
			String modeName = strs[1];
			if (modeName.indexOf('=') > 0) {
				return;
			}
			if (modeName.length() > 4 && "autoUpdateFixMeanSubstRate".startsWith(modeName)) {
				replaceRange("mode autoUpdateFixMeanSubstRate = ", cmdStart, textLength());
				return;
			} else if (modeName.length() > 4 && "autoSetClockRate".startsWith(modeName)) {
				replaceRange("mode autoSetClockRate = ", cmdStart, textLength());
				return;
			} else {
				printHint("Choose one of autoUpdateFixMeanSubstRate or autoUpdateFixMeanSubstRate\n", Color.blue);
				return;
			}
		}
 	}
	
	private void completeAdd(String part) {		
    	if (part.indexOf('(') > 0) {
    		return;
    	}

    	if (CompactAnalysisByAntlr.priorProviders == null) {
    		BeautiDoc doc = studio.interpreter.doc;
    		CompactAnalysisByAntlr.initProviders(doc);
    	}
    	
		String part0 = part.substring(3).trim();
		if (part0.length() == 0) {
			if (CompactAnalysisByAntlr.priorProviders.size() == 1) {
				replaceRange("add " + CompactAnalysisByAntlr.priorProviders.get(0).getClass().getSimpleName(), cmdStart, textLength());
				return;				
			}
			String [] descriptions = new String[CompactAnalysisByAntlr.priorProviders.size()];
			for (int i = 0; i < descriptions.length; i++) {
				descriptions[i] = CompactAnalysisByAntlr.priorProviders.get(i).getClass().getSimpleName();
			}
			printHint("\nChoose one of: " + Arrays.toString(descriptions), Color.blue);
			return;
		}
		
		List<String> matches = new ArrayList<>();
		for (PriorProvider p : CompactAnalysisByAntlr.priorProviders) {
			if (p.getClass().getSimpleName().startsWith(part0)) {
				matches.add(p.getClass().getSimpleName());
			}
		}		
		
		completeMatch(matches, part0, "add ", "(", "No prior provider matches ");
//		if (matches.size() == 1) {				
//			replaceRange("add " + matches.get(0) +"(", cmdStart, textLength());
//			return;
//		}
//		if (matches.size() == 0) {
//			printHint("\nNo prior provider matches " + part0, Color.blue);
//			return;
//		}
//		String prefix = getLargestCommonPrefix(matches);
//		if (prefix.length() > part0.length()) {
//			replaceRange("add "  + prefix, cmdStart, textLength());
//		}
//		printHint("\nChoose one of: " + Arrays.toString(matches.toArray()), Color.blue);		
//		return;					
	}
	
	private void completeSubtemplate(String part) {
		String part0 = part.substring(0, part.indexOf('='));
		String part1 = part.substring(part.indexOf('=') + 1);
		if (part1.indexOf('(') > 0) {
			// deal with arguments
			return;
		}
		
		// try to complete subtemplate name
		String str = part1.trim();
		boolean hasQuote = false;
		if (str.charAt(0) == '"') {
			hasQuote = true;
			str = str.substring(1);
		}
		BeautiDoc doc = studio.interpreter.doc;
		List<String> matches = new ArrayList<>();
		for (BeautiSubTemplate sub : doc.beautiConfig.subTemplates) {
			if (sub.getID().startsWith(str)) {
				matches.add(sub.getID());
			}
		}
		completeMatch(matches, part1, part0 + "= " + (hasQuote ? '"' : ""), (hasQuote ? "\"" : ""), "No subtemplate matches ");

//		if (matches.size() == 1) {				
//			replaceRange(part0 + "= " + (hasQuote ? '"' : "") + matches.get(0) + (hasQuote ? '"' : ""), cmdStart, textLength());
//			return;
//		}
//		if (matches.size() == 0) {
//			printHint("No subtemplate matches " + part1, Color.blue);
//			return;
//		}
//		String prefix = getLargestCommonPrefix(matches);
//		if (prefix.length() > str.length()) {
//			replaceRange(part0 + "= " + (hasQuote ? '"' : "") + prefix, cmdStart, textLength());
//		}
//		printHint("\nChoose one of: " + Arrays.toString(matches.toArray()), Color.blue);		
	}
	
	private void completeRename(String part) {
		if (part.indexOf('=') > 0) {
			printHint("\nProvide new name of partition", Color.blue);
			return;
		}

		String [] strs = part.split("\\s+");
		if (strs.length == 1) {
			printHint("\nChoose one of 'clock', 'tree', 'sitemodel'", Color.blue);
			return;
		}
		if (strs.length == 2) {
			if ("clock".startsWith(strs[1])) {
				strs[1] = "clock";
			} else if ("tree".startsWith(strs[1])) {
				strs[1] = "tree";
			} else if ("sitemodel".startsWith(strs[1])) {
				strs[1] = "sitemodel";
			} else {
				printHint("\nChoose one of 'clock', 'tree', 'sitemodel'", Color.blue);
				return;
			}
			replaceRange(strs[0] + " " + strs[1] + " ", cmdStart, textLength());
		}
		
		Map<String, String> map = new HashMap<>();
		map.put("clock", "ClockModel");
		map.put("tree", "TreeModel");
		map.put("sitemodel", "SiteModel");
		BeautiDoc doc = studio.interpreter.doc;
		
		List<String> matches = new ArrayList<>();
		String current = strs[strs.length - 1];

		List<BEASTInterface> partitions = doc.getPartitions(map.get(strs[1]));
		List<String> partNames = new ArrayList<>();
		for (BEASTInterface bo : partitions) {
			String id = bo.getID();
			switch (strs[1]) {
			case "clock":
				id = ((BEASTInterface) bo.getInput("branchRateModel").get()).getID();
				break;
			case "tree":
				id = ((BEASTInterface) bo.getInput("tree").get()).getID();
				break;
			case "sitemodel":
				id = ((BEASTInterface) bo.getInput("siteModel").get()).getID();
				break;
			}
			id = BeautiDoc.parsePartition(id);
			partNames.add(id);
		}
		
		if (strs.length == 2) {
			printHint("\nChoose one of " + partNames.toString(), Color.blue);
			return;
		}
		for (String partition : partNames) {
			if (current.length() == 0 || partition.startsWith(current)) {
				matches.add(partition);
			}
		}

		String cmd = strs[0] + " " + strs[1] + " ";
				
		completeMatch(matches, current, cmd, " = ", "No available partition matches " + current);

//		if (matches.size() == 1) {
//			replaceRange(cmd + matches.get(0), cmdStart, textLength());
//			return;
//		}
//		if (matches.size() == 0) {
//			replaceRange(cmd + strs[strs.length - 1], cmdStart, textLength());
//			printHint("\nNo available partition matches " + current, Color.blue);
//			return;
//		}
//		String prefix = getLargestCommonPrefix(matches);
//		if (prefix.length() > current.length()) {
//			replaceRange(cmd + prefix, cmdStart, textLength());
//		}
//		printHint("\nChoose one of:\n" + Arrays.toString(matches.toArray()).replaceAll(",", "\n"), Color.blue);		
	}

	private void completeTaxonset(String part) {
		if (part.indexOf('=') > 0) {
			BeautiDoc doc = studio.interpreter.doc;
			String [] taxa = doc.taxaset.keySet().toArray(new String [] {});
			String [] strs = part.split("=");
			if (strs.length == 1) {
				Arrays.sort(taxa);
				printHint("\nChoose one of: " + Arrays.toString(taxa).replaceAll("[\\[,\\]]", "\n").replaceFirst("\n", "\n "), Color.blue);
				return;
			}
			String [] strs2 = strs[1].split("\\s+");
			String current = strs2[strs2.length - 1];			
			List<String> matches = new ArrayList<>();
			for (String taxon : taxa) {
				if (taxon.startsWith(current)) {
					matches.add(taxon);
				}
			}
			part = part.substring(0, part.length() - current.length());
			completeMatch(matches, current, part, " ", "No available taxon matches " + part);
			
//			if (matches.size() == 1) {
//				part = part.substring(0, part.length() - current.length());
//				replaceRange(part + matches.get(0) + " ", cmdStart, textLength());
//				return;
//			}
//			if (matches.size() == 0) {
//				printHint("\nNo available taxon matches " + current, Color.blue);
//				return;
//			}
//			String prefix = getLargestCommonPrefix(matches);
//			if (prefix.length() > current.length()) {
//				part = part.substring(0, part.length() - current.length());
//				replaceRange(part + prefix, cmdStart, textLength());
//			}
//			printHint("\nChoose one of:\n" + Arrays.toString(matches.toArray()).replaceAll(",", "\n"), Color.blue);		
			return;
		}

		String [] strs = part.split("\\s+");
		if (strs.length == 1) {
			printHint("\nProvide the name of a taxonset", Color.blue);
		} else {
			replaceRange(part +" = ", cmdStart, textLength());			
		}
	}
	
	private void completeLinkOrUnLink(String part) {
		String [] strs = part.split("\\s+");
		if (part.indexOf('{') > 0) {
			if (part.endsWith(",")) {
				part += "*";
			}
			String [] strs2 = part.substring(part.indexOf('{')+1).split(",");
			strs2[strs2.length - 1] = strs2[strs2.length - 1].replaceAll("}", "");

			Map<String, String> map = new HashMap<>();
			map.put("clock", "ClockModel");
			map.put("tree", "TreeModel");
			map.put("sitemodel", "SiteModel");
			BeautiDoc doc = studio.interpreter.doc;
			
			List<BEASTInterface> partitions = doc.getPartitions(map.get(strs[1]));
			List<String> matches = new ArrayList<>();
			String current = strs2[strs2.length - 1];
			if (current.equals("*")) {
				current = "";
			}
			for (BEASTInterface p : partitions) {
				String partition = doc.parsePartition(p.getID());
				if (current.length() == 0 || partition.startsWith(current)) {
					matches.add(partition);
				}
			}
			for (String s : strs2) {
				if (!s.equals(current)) {
					matches.remove(s);
				}
			}			
			String cmd = strs[0] + " " + strs[1] + " {";
			for (int i = 0; i < strs2.length - 1; i++) {
				cmd += strs2[i] + ",";
			}
			if (matches.size() == 1) {
				if (matches.get(0).equals(current)) {
					matches.clear();
				} else {
					replaceRange(cmd + matches.get(0), cmdStart, textLength());
					return;
				}
			}
			if (matches.size() == 0) {
				replaceRange(cmd + strs2[strs2.length - 1] + "}", cmdStart, textLength());
				printHint("\nNo available partition matches " + current, Color.blue);
				return;
			}
			String prefix = getLargestCommonPrefix(matches);
			if (prefix.length() > current.length()) {
				replaceRange(cmd + prefix, cmdStart, textLength());
			}
			printHint("\nChoose one of:\n" + Arrays.toString(matches.toArray()).replaceAll(",", "\n"), Color.blue);		
		}
				
		if (strs.length == 1) {
				printHint("\nChoose one of 'clock', 'tree', 'sitemodel'", Color.blue);
			return;
		}
		if (strs.length == 2) {
			if ("clock".startsWith(strs[1])) {
				replaceRange(strs[0] + " clock {", cmdStart, textLength());
			} else if ("tree".startsWith(strs[1])) {
				replaceRange(strs[0] + " tree {", cmdStart, textLength());
			} else if ("sitemodel".startsWith(strs[1])) {
				replaceRange(strs[0] + " sitemodel {", cmdStart, textLength());
			} else {
				printHint("\nChoose one of 'clock', 'tree', 'sitemodel'", Color.blue);
			}
			return;
		}
		
		
	}
	
	
	private void completeImport(String part) {
		part = part.substring(6).trim();
		String cwd;
		String fileSep = Utils.isWindows() ? "\\\\" : "/";
		if (part.startsWith(fileSep)) {
			cwd = part;
		} else {
			cwd = System.getProperty("user.dir") + fileSep + part;
		}
		String dir = cwd.substring(0, cwd.lastIndexOf(fileSep));
		String rest = cwd.substring(cwd.lastIndexOf(fileSep) + 1);
		File d = new File(dir);
		if (!d.exists()) {
			printHint("\nFile or folder " + d.getAbsolutePath() + " does not exist", Color.red);
		}
		List<String> matches = new ArrayList<>();
		for (File f : d.listFiles()) {
			if (f.getName().startsWith(rest)) {
				try {
					matches.add(f.getCanonicalPath());
				} catch (IOException e) {					
				}
			}
		}
		
		completeMatch(matches, part, "import ", "No file matches " + part);
//		if (matches.size() == 1) {
//			replaceRange("import " + matches.get(0), cmdStart, textLength());
//			return;
//		}
//		if (matches.size() == 0) {
//			printHint("No file matches " + part, Color.blue);
//			return;
//		}
//		
//		String largestPrefix = getLargestCommonPrefix(matches);
//		if (largestPrefix.length() > part.length()) {
//			replaceRange("import " + largestPrefix, cmdStart, textLength());
//		}
//		printHint("\nChoose one of:\n" + Arrays.toString(matches.toArray()).replaceAll(",", "\n"), Color.blue);		
	}
	
	private void completeMatch(List<String> matches, String toMatch, String match0, String warning) {
		completeMatch(matches, toMatch, match0, "", warning);
	}
	
	private void completeMatch(List<String> matches, String toMatch, String match0, String postFix, String warning) {
		if (matches.size() == 1) {
			replaceRange(match0 + matches.get(0) + postFix, cmdStart, textLength());
			return;
		}
		if (matches.size() == 0) {
			printHint(warning + toMatch, Color.blue);
			return;
		}
		String prefix = getLargestCommonPrefix(matches);
		if (prefix.length() > toMatch.length()) {			
			replaceRange(match0 + prefix, cmdStart, textLength());
		}
		printHint("\nChoose one of: " + Arrays.toString(matches.toArray()).replaceAll("[\\[,\\]]", "\n").replaceFirst("\n", "\n "), Color.blue);		
	}
	
	
	private String getLargestCommonPrefix(List<String> strs) {
		if (strs.size() == 0) {
			return "";
		}
		
		String m0 = strs.get(0);
		for (int i = 0; i < m0.length(); i++) {
			char c = m0.charAt(i);
			for (String s : strs) {
				if (s.length() == i || s.charAt(i) != c) {
					return m0.substring(0, i);
				}
			}
		}
		
		// we only get here if m0 is prefix of all strs
		return m0;
	}
	
	private void completeTemplate(String part) {
		part = part.substring(8).trim();
		
		initTemplates();
		List<String> matches = new ArrayList<>();
		for (String template : templates) {
			if (template.startsWith(part)) {
				matches.add(template);
			}
		}
		completeMatch(matches, part, "template ", "No template matches " + part);
	}
	
	private void initTemplates() {
		if (templates != null) {
			return;
		}
		templates = new ArrayList<>();
        List<String> beastDirectories = PackageManager.getBeastDirectories();
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
                                    String fileSep = System.getProperty("file.separator");
                                    if (fileSep.equals("\\")) {
                                        fileSep = "\\";
                                    }
                                    int i = fileName.lastIndexOf(fileSep) + 1;
                                    String name = fileName.substring(i, fileName.length());
                                    templates.add(name);
                                }
                            } catch (Exception e) {
                            	Log.warning.println(e.getMessage());
                            }
                        }
                    }
                }
            }
        }
		
	}
	private String gcd(String gcd, String str) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < gcd.length() && i < gcd.length(); i++) {
			char c1 = gcd.charAt(i);
			char c2 = str.charAt(i);
			if (c1 == c2) {
				b.append(c1);
			} else {
				return b.toString();
			}
		}
		return b.toString();
	}

	private void resetCommandStart() {
		cmdStart = textLength();
	}

	private	void append(String string) {
		int slen = textLength();
		text.select(slen, slen);
	    text.replaceSelection(string);
    }

    private String replaceRange(Object s, int start, int	end) {
		String st = s.toString();
		text.select(start, end);
	    text.replaceSelection(st);
	    //text.repaint();
	    return st;
    }

	private	void forceCaretMoveToEnd() {
		if (text.getCaretPosition() < cmdStart)	{
			// move caret first!
			text.setCaretPosition(textLength());
		}
		text.repaint();
    }

	private	void forceCaretMoveToStart() {
		if (text.getCaretPosition() < cmdStart)	{
			// move caret first!
		}
		text.repaint();
    }


	private	void enter() {
		String s = getCmd();

		if ( s.length()	== 0 )	// special hack	for empty return!
			s = ";\n";
		else {
			history.add( s );
			if (historyPanel != null) {
				historyPanel.add(s);
			}
			s = s +"\n";
		}

		append("\n");
		histLine = 0;
		studio.getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		text.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		acceptLine( s );
		studio.getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		text.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		text.repaint();
	}

    private String getCmd() {
		String s = "";
		try {
			s =	text.getText(cmdStart, textLength() - cmdStart);
		} catch	(BadLocationException e) {
			// should not happen
			System.out.println("Internal JConsole Error: "+e);
		}
		return s;
    }

	private	void historyUp() {
		if ( history.size() == 0 )
			return;
		if ( histLine == 0 )  // save current line
			startedLine = getCmd();
		if ( histLine <	history.size() ) {
			histLine++;
			showHistoryLine();
		}
	}
	
	private	void historyDown() {
		if ( histLine == 0 )
			return;

		histLine--;
		showHistoryLine();
	}

	private	void showHistoryLine() {
		String showline;
		if ( histLine == 0 )
			showline = startedLine;
		else
			showline = history.get(history.size() - histLine	);

		replaceRange( showline,	cmdStart, textLength() );
		text.setCaretPosition(textLength());
		text.repaint();
	}

	String ZEROS = "000";

	
	ByteArrayOutputStream boas = null;
	
	private	void acceptLine( String	line ) 
	{
		if (boas == null) {
        	boas = new ByteArrayOutputStream() {
        		@Override
        		public synchronized void write(byte[] b, int off, int len) {
        			if (suppressOutput) return;
        			super.write(b, off, len);
    				for (int i = off; i < off + len; i++) {
    					printHint("" + (char) b[i], Color.blue);
    				}
        		};

				@Override
        		public synchronized void write(int b) {
        			if (suppressOutput) return;
        			super.write(b);
					printHint("" + (char) b, Color.blue);
        		};

        		@Override
        		public void write(byte[] b) throws java.io.IOException {
        			if (suppressOutput) return;
        			super.write(b);
    				for (int i = 0; i < b.length; i++) {
    					printHint("" + (char) b[i], Color.blue);
    				}
        		};

        		@Override
        		public void flush() throws java.io.IOException {
        			if (suppressOutput) return;
        			super.flush();
        		};

        		@Override
        		public void close() throws IOException {
        			if (suppressOutput) return;
        			super.close();
        		}
        	};

        	Log.info = new PrintStream(boas);
        	
        	boas = new ByteArrayOutputStream() {
        		@Override
        		public synchronized void write(byte[] b, int off, int len) {
        			if (suppressOutput) return;
        			super.write(b, off, len);
    				for (int i = off; i < off + len; i++) {
    					printHint("" + (char) b[i], Color.red);
    				}
        		};

        		@Override
        		public synchronized void write(int b) {
        			if (suppressOutput) return;
        			super.write(b);
					printHint("" + (char) b, Color.red);
        		};

        		@Override
        		public void write(byte[] b) throws java.io.IOException {
        			if (suppressOutput) return;
        			super.write(b);
    				for (int i = 0; i < b.length; i++) {
    					printHint("" + (char) b[i], Color.red);
    				}
        		};

        		@Override
        		public void flush() throws java.io.IOException {
        			super.flush();
        		};

        		@Override
        		public void close() throws IOException {
        			super.close();
        		}
        	};

        	Log.err = new PrintStream(boas);
        	Log.warning = Log.err;
		}
		// Patch to handle Unicode characters
		// Submitted by Daniel Leuck
//		StringBuilder buf = new StringBuilder(); 
//		int lineLength = line.length(); 
//		for(int i=0; i<lineLength; i++) {  
//				String val = Integer.toString(line.charAt(i), 16); 
//				val=ZEROS.substring(0,4-val.length()) + val;
//				buf.append("\\u" + val);
//		} 
//		line = buf.toString();
		// End unicode patch

		if (outPipe == null )
			print("Console internal	error: cannot output ...", Color.red);
		else
			try {
				byte [] bytes = line.getBytes();
				// RRB: following line can block the EventThread
				//outPipe.write( bytes );
				outPipe.flush();
			} catch	( IOException e	) {
				outPipe	= null;
				throw new RuntimeException("Console pipe broken...");
			}
		new Thread() {
			@Override
			public void run() {
				if (line.trim().startsWith("import")) {
        			setSuppressOutput(true);
				}
				try {
					mutex.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					mutex.release();
				}
				studio.interpreter.run(line);
				mutex.release();
				setSuppressOutput(false);
			}
		}.start();
		//text.repaint();
	}

	public void println(Object o) {
	    print( String.valueOf(o) + "\n" );
		text.repaint();
	}

	public void print(final Object o) {
		invokeAndWait(new Runnable() {
			@Override
			public void run() {
				append(String.valueOf(o));
				resetCommandStart();
				text.setCaretPosition(cmdStart);
			}
		});
	}

	/**
	  * Prints "\\n" (i.e. newline)
	  */
	public void println() {
	    print("\n");
		text.repaint();
	}

	public void error( Object o ) {
	    print( o, Color.red );
	}

	public void println(Icon icon) {
		print(icon);
		println();
		text.repaint();
	}

	public void print(final Icon icon) {
	    if (icon==null) 
			return;

		invokeAndWait(new Runnable() {
			@Override
			public void run() {
				text.insertIcon(icon);
				resetCommandStart();
				text.setCaretPosition(cmdStart);
			}
		});			
	}

	public void print(Object s, Font font) {
		print(s, font, null);
    }

	public void print(Object s, Color color) {
		print(s, null, color);
	}

	public void print(final Object o, final Font font, final Color color) {
		invokeAndWait(new Runnable() {
			@Override
			public void run() {
				AttributeSet old = getStyle();
				setStyle(font, color);
				append(String.valueOf(o));
				resetCommandStart();
				text.setCaretPosition(cmdStart);
				setStyle(old, true);
			}
		});	
    }

	private void printHint(String string, Color color) {
		invokeAndWait(new Runnable() {
			@Override
			public void run() {
				JTextPane text = studio.hintsPane;
				AttributeSet oldAttr = studio.hintsPane.getCharacterAttributes(); 
				MutableAttributeSet attr = new SimpleAttributeSet();
				if (color!=null)
					StyleConstants.setForeground(attr, color);
				text.setCharacterAttributes(attr, false);

				int slen = text.getDocument().getLength();
				text.select(slen, slen);
			    text.replaceSelection(string);
				
				text.setCharacterAttributes(oldAttr, false);
				studio.rightLowerPaneTab.setSelectedIndex(1);
			}
		});	
	}

	public void print(
	    Object s,
	    String fontFamilyName,
	    int	size,
	    Color color
	    ) {
			
	    print(s,fontFamilyName,size,color,false,false,false);
    }

	public void print(
	    final Object o,
	    final String fontFamilyName,
	    final int	size,
	    final Color color,
	    final boolean bold,
	    final  boolean italic,
	    final boolean underline
	    ) 
	{
		invokeAndWait(new Runnable() {
			@Override
			public void run() {
				AttributeSet old = getStyle();
				setStyle(fontFamilyName, size, color, bold,	italic,	underline);
				append(String.valueOf(o));
				resetCommandStart();
				text.setCaretPosition(cmdStart);
				setStyle(old, true);
			}
		});			
    }

//    private AttributeSet setStyle(Font font) {
//	    return setStyle(font, null);
//    }
//
//    private AttributeSet setStyle(Color color) {
//	    return setStyle(null, color);
//    }

    private AttributeSet setStyle( Font font, Color color) 
	{
	    if (font!=null)
			return setStyle( font.getFamily(), font.getSize(), color, 
				font.isBold(), font.isItalic(), 
				StyleConstants.isUnderline(getStyle()) );
		else
			return setStyle(null,-1,color);
    }

    private AttributeSet setStyle (
	    String fontFamilyName, int	size, Color color) 
	{
		MutableAttributeSet attr = new SimpleAttributeSet();
		if (color!=null)
			StyleConstants.setForeground(attr, color);
		if (fontFamilyName!=null)
			StyleConstants.setFontFamily(attr, fontFamilyName);
		if (size!=-1)
			StyleConstants.setFontSize(attr, size);

		setStyle(attr);

		return getStyle();
    }

    private AttributeSet setStyle(
	    String fontFamilyName,
	    int	size,
	    Color color,
	    boolean bold,
	    boolean italic,
	    boolean underline
	    ) 
	{
		MutableAttributeSet attr = new SimpleAttributeSet();
		if (color!=null)
			StyleConstants.setForeground(attr, color);
		if (fontFamilyName!=null)
			StyleConstants.setFontFamily(attr, fontFamilyName);
		if (size!=-1)
			StyleConstants.setFontSize(attr, size);
		StyleConstants.setBold(attr, bold);
		StyleConstants.setItalic(attr, italic);
		StyleConstants.setUnderline(attr, underline);

		setStyle(attr);

		return getStyle();
    }

    private void setStyle(AttributeSet attributes) {
		setStyle(attributes, false);
    }

    private void setStyle(AttributeSet attributes, boolean overWrite) {
		text.setCharacterAttributes(attributes,	overWrite);
    }

    private AttributeSet getStyle() {
		return text.getCharacterAttributes();
    }

	@Override
	public void setFont( Font font ) {
		super.setFont( font );

		if ( text != null )
			text.setFont( font );
	}

	private	void inPipeWatcher() throws IOException	{
		byte []	ba = new byte [256]; //	arbitrary blocking factor
		int read;
		while (	(read =	inPipe.read(ba)) != -1 ) {
			if (!suppressOutput) {
				print( new String(ba, 0, read) );
			}
			//text.repaint();
		}

		println("Console: Input	closed...");
	}

	@Override
	public void run() {
		try {
			inPipeWatcher();
		} catch	( IOException e	) {
			print("Console: I/O Error: "+e+"\n", Color.red);
		}
	}

	@Override
	public String toString() {
		return "BeanShell console";
	}

    // MouseListener Interface
    @Override
	public void	mouseClicked(MouseEvent	event) {
    }

    @Override
	public void mousePressed(MouseEvent event) {
        if (event.isPopupTrigger()) {
            menu.show(
				(Component)event.getSource(), event.getX(), event.getY());
        }
    }

    @Override
	public void	mouseReleased(MouseEvent event)	{
		if (event.isPopupTrigger()) {
			menu.show((Component)event.getSource(), event.getX(),
			event.getY());
		}
		text.repaint();
    }

    @Override
	public void	mouseEntered(MouseEvent	event) { }

    @Override
	public void	mouseExited(MouseEvent event) { }

    // property	change
    @Override
	public void	propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals("lookAndFeel")) {
			SwingUtilities.updateComponentTreeUI(menu);
		}
    }

    // handle cut, copy	and paste
    @Override
	public void	actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();
		if (cmd.equals(CUT)) {
			text.cut();
		} else if (cmd.equals(COPY)) {
			text.copy();
		} else if (cmd.equals(PASTE)) {
			text.paste();
		}
    }

	/**
	 * If not in the event thread run via SwingUtilities.invokeAndWait()
	 */
	private void invokeAndWait(Runnable run) {
//		if(!SwingUtilities.isEventDispatchThread()) {
//			try {
//				SwingUtilities.invokeAndWait(run);
//			} catch(Exception e) {
//				// shouldn't happen
//				e.printStackTrace();
//			}
//		} else {
			run.run();
//		}
	}

	/**
		The overridden read method in this class will not throw "Broken pipe"
		IOExceptions;  It will simply wait for new writers and data.
		This is used by the JConsole internal read thread to allow writers
		in different (and in particular ephemeral) threads to write to the pipe.

		It also checks a little more frequently than the original read().

		Warning: read() will not even error on a read to an explicitly closed 
		pipe (override closed to for that).
	*/
	public static class BlockingPipedInputStream extends PipedInputStream
	{
		boolean closed;
		public BlockingPipedInputStream( PipedOutputStream pout ) 
			throws IOException 
		{
			super(pout);
		}
		@Override
		public synchronized int read() throws IOException {
			if ( closed )
				throw new IOException("stream closed");

			while (super.in < 0) {	// While no data */
				notifyAll();	// Notify any writers to wake up
				try {
					wait(750);
				} catch ( InterruptedException e ) {
					throw new InterruptedIOException();
				}
			}
			// This is what the superclass does.
			int ret = buffer[super.out++] & 0xFF;
			if (super.out >= buffer.length)
				super.out = 0;
			if (super.in == super.out)
				super.in = -1;  /* now empty */
			return ret;
		}
		@Override
		public void close() throws IOException {
			closed = true;
			super.close();
		}
	}

//	public void setNameCompletion( NameCompletion nc ) {
//		this.nameCompletion = nc;
//	}

	public void setWaitFeedback( boolean on ) {
		if ( on )
			setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) );
		else
			setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR) );
	}

	private int textLength() { return text.getDocument().getLength(); }

}


