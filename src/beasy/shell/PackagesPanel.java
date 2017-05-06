package beasy.shell;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Graphics;

import javax.swing.JPanel;

import beast.app.beauti.JPackageDialog;

public class PackagesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JPackageDialog panel = null;

	@Override
	protected void paintComponent(Graphics arg0) {
		if (panel == null) {
			init();
		}
		super.paintComponent(arg0);
	}

	private void init() {
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		//panel = new JPackageDialog();
		add(panel, BorderLayout.CENTER);
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

}
