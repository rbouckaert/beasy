package beasy.shell;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Adapted from https://www.ssl.berkeley.edu/~mlampton/MTE.java
 * @author Mike Lampton
 *
 * Search+Replace nonmodal Dialog class for JTextArea. Uses host's
 * WindowListener to disappear when host hides. M.Lampton Stellar Software 2003,
 * 2011
 */
public class SearchDialog extends JDialog {
	public SearchDialog(Component givenJF, JTextArea givenJTA) {
		myJF = givenJF;
		myJTA = givenJTA;

		searchJTF = new JTextField("", 10);
		searchJTF.setMaximumSize(new Dimension(150, 20));
		replaceJTF = new JTextField("", 10);
		replaceJTF.setMaximumSize(new Dimension(150, 20));

		setVisible(false);
		setTitle("Search & Replace");

		// -----Panel 1: search panel--------------

		JPanel jp1 = new JPanel();
		jp1.setPreferredSize(new Dimension(100, 20));
		jp1.setLayout(new BoxLayout(jp1, BoxLayout.X_AXIS));
		JLabel jl1 = new JLabel("Find what?");
		jp1.add(Box.createGlue());
		jp1.add(jl1);
		jp1.add(Box.createHorizontalStrut(5));
		jp1.add(searchJTF);
		jp1.add(Box.createHorizontalStrut(50));

		// -------Panel 2: replace panel-------------

		JPanel jp2 = new JPanel();
		jp2.setPreferredSize(new Dimension(100, 20));
		jp2.setLayout(new BoxLayout(jp2, BoxLayout.X_AXIS));
		JLabel jl2 = new JLabel("Replace with?");
		jp2.add(Box.createGlue());
		jp2.add(jl2);
		jp2.add(Box.createHorizontalStrut(5));
		jp2.add(replaceJTF);
		jp2.add(Box.createHorizontalStrut(50));

		// ------Panel 3: search direction panel----------

		JPanel jp3 = new JPanel();
		jp3.setPreferredSize(new Dimension(150, 25));
		jp3.setMinimumSize(jp3.getPreferredSize());
		dirBG = new ButtonGroup();

		JRadioButton fwdJRB = new JRadioButton("forward", true);
		fwdJRB.setActionCommand("fwd");
		fwdJRB.setVerticalTextPosition(AbstractButton.CENTER);
		fwdJRB.setHorizontalTextPosition(AbstractButton.LEFT);
		dirBG.add(fwdJRB);

		JRadioButton revJRB = new JRadioButton("reverse", true);
		revJRB.setActionCommand("rev");
		revJRB.setVerticalTextPosition(AbstractButton.CENTER);
		revJRB.setHorizontalTextPosition(AbstractButton.RIGHT);
		dirBG.add(revJRB);

		jp3.add(Box.createGlue());
		jp3.add(Box.createGlue());
		jp3.add(fwdJRB);
		jp3.add(revJRB);
		jp3.add(Box.createGlue());

		// -----Panel 4: case sensitivity panel-----------

		JPanel jp4 = new JPanel();
		jp4.setPreferredSize(new Dimension(150, 25));
		jp4.setMinimumSize(jp4.getPreferredSize());
		sensBG = new ButtonGroup();

		JRadioButton ignoreJRB = new JRadioButton("ignore case", true);
		ignoreJRB.setActionCommand("ignore");
		ignoreJRB.setVerticalTextPosition(AbstractButton.CENTER);
		ignoreJRB.setHorizontalTextPosition(AbstractButton.LEFT);
		sensBG.add(ignoreJRB);

		JRadioButton matchJRB = new JRadioButton("match case", true);
		matchJRB.setActionCommand("match");
		matchJRB.setVerticalTextPosition(AbstractButton.CENTER);
		matchJRB.setHorizontalTextPosition(AbstractButton.RIGHT);
		sensBG.add(matchJRB);

		jp4.add(Box.createGlue());
		jp4.add(Box.createGlue());
		jp4.add(ignoreJRB);
		jp4.add(matchJRB);
		jp4.add(Box.createGlue());

		// -----Panel 5: button panel-----------------

		JPanel jp5 = new JPanel();
		jp5.setPreferredSize(new Dimension(150, 40));
		jp5.setMinimumSize(jp5.getPreferredSize());
		jp5.setLayout(new BoxLayout(jp5, BoxLayout.X_AXIS));

		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ix = getNextPosition(false);
				setTitle((ix >= 0) ? "Found" : "Not found");
			}
		});

		JButton replaceButton = new JButton("Replace");
		replaceButton.addActionListener(new ActionListener()
		// must replace possible current selection, and then search to next
		// selection.
				{
					public void actionPerformed(ActionEvent e) {
						int ix = getNextPosition(true);
						setTitle((ix >= 0) ? "Found" : "Not found");
					}
				});

		JButton allButton = new JButton(" All ");
		allButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ix = 0, n = 0;
				while (ix >= 0) {
					ix = getNextPosition(true);
					if (ix >= 0)
						n++;
				}
				setTitle(n + " replacements");
			}
		});

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		jp5.add(Box.createGlue());
		jp5.add(nextButton);
		jp5.add(Box.createGlue());
		jp5.add(replaceButton);
		jp5.add(Box.createGlue());
		jp5.add(allButton);
		jp5.add(Box.createGlue());
		jp5.add(closeButton);
		jp5.add(Box.createGlue());

		Container cp = getContentPane();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		cp.add(Box.createVerticalStrut(5));
		cp.add(jp1);
		cp.add(Box.createVerticalStrut(5));
		cp.add(jp2);
		cp.add(jp3);
		cp.add(jp4);
		cp.add(jp5);
		cp.setPreferredSize(new Dimension(320, 160));
		cp.setMinimumSize(getPreferredSize());

		pack();
		setLocationRelativeTo(myJF);
		setVisible(false);
	}

	int getNextPosition(boolean bReplace) {
		String sc = myJTA.getSelectedText();
		String sr = replaceJTF.getText();
		String ss = searchJTF.getText();
		String st = myJTA.getText();

		int ir = sr.length();
		int is = ss.length();
		int it = st.length();
		if ((is < 1) || (it < 1))
			return -1;

		if (bReplace && (sc != null) && (sr != null)) {
			if (bIgnoreCase()) {
				sc = sc.toUpperCase();
				ss = ss.toUpperCase();
			}
			if (sc.equals(ss)) {
				myJTA.replaceSelection(sr); // changes text
				st = myJTA.getText(); // get new text
				it = st.length(); // get new length
			}
		}

		// ----now search for next selection------

		if (bIgnoreCase()) {
			st = st.toUpperCase();
			ss = ss.toUpperCase();
		}

		int ix = myJTA.getCaretPosition();
		// On repeats, caret is always found at the end of selection

		if (bForward()) {
			if (ix == it) // special startup case
				ix = 0;
			ix = st.indexOf(ss, ix); // = startNext. or -1.
		} else {
			ix -= is + 1; // back up
			if (ix < is) // absent
				ix = -1;
			else
				ix = st.lastIndexOf(ss, ix);
		}
		if (ix >= 0) // success
		{
			myJTA.requestFocus(true);
			myJTA.setSelectionStart(ix);
			myJTA.setSelectionEnd(ix + is);
			// Caret must remain at SelectionEnd
		}
		return ix;
	}

	boolean bIgnoreCase() {
		String sens = sensBG.getSelection().getActionCommand();
		return sens.equals("ignore");
	}

	boolean bForward() {
		String dir = dirBG.getSelection().getActionCommand();
		return dir.equals("fwd");
	}

	Component myJF = null;
	JTextArea myJTA = null;
	int index = 0;
	JTextField searchJTF = null;
	JTextField replaceJTF = null;
	ButtonGroup dirBG, sensBG;
}