package beasy.shell;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import beast.util.AddOnManager;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HistoryPanel extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;

	JTextField searchField;
	JTextArea textPane;
	Image image;
	BeasyStudio studio = null;
	List<String> history;
	
	public HistoryPanel(BeasyStudio _studio) {
		studio = _studio;
		history = new ArrayList<String>();
		setLayout(new BorderLayout());
		
		textPane = new JTextArea();
//		textPane.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
//		textPane.setCodeFoldingEnabled(true);
//		textPane.setAntiAliasingEnabled(true);
		add(new JScrollPane(textPane), BorderLayout.CENTER);
		
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		JButton toConsoleButton = new JButton("");
		toConsoleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String text = textPane.getSelectedText();
				StringSelection stringSelection = new StringSelection (text);
				Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
				clpbrd.setContents (stringSelection, null);
				studio.console.text.paste();
			}
		});
		toConsoleButton.setToolTipText("Send selected commands to the console and clipboard");
		toConsoleButton.setIcon(new ImageIcon(HistoryPanel.class.getResource("/beast/app/shell/icons/toconsole.png")));
		toolBar.add(toConsoleButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setText("");
				history.clear();
			}
		});
		btnNewButton_1.setToolTipText("remove everything from history");
		btnNewButton_1.setIcon(new ImageIcon(HistoryPanel.class.getResource("/beast/app/shell/icons/removeall.png")));
		toolBar.add(btnNewButton_1);
		
		image = new ImageIcon(HistoryPanel.class.getResource("/beast/app/shell/icons/search.png")).getImage();
		searchField = new JTextField() {
	            protected void paintComponent(Graphics g) {  
	                super.paintComponent(g);  
	                int y = (getHeight() - image.getHeight(null))/2;
	                int x = getWidth() - 17;
	                g.drawImage(image, x, y, this);
	            }  
	        };  
		searchField.setToolTipText("filter history by matching expression");
		toolBar.add(searchField);
		searchField.setColumns(8);
		searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
            	filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	filter();
            }
        });
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchField.setText("");
			}
		});
		btnNewButton_2.setToolTipText("clear filter history");
		btnNewButton_2.setIcon(new ImageIcon(HistoryPanel.class.getResource("/beast/app/shell/icons/clear.png")));
		toolBar.add(btnNewButton_2);
		
		new Thread() {
			@Override
			public void run() {
				try {
					new Thread().wait(20000);
				} catch (Exception e) {}	
				loadBackup();
			}
		}.run();
		
		//TODO: let the splitter pane determine size
		setMaximumSize(new Dimension(768,2048));
	}

	final static String BACKUP_FILE = "beastshell.history";
	
	void loadBackup() {
		File backup = new File(AddOnManager.getPackageUserDir() + BeasyStudio.PACKAGENAME + BACKUP_FILE);
		if (backup.exists()) {
			try {
		        BufferedReader fin = new BufferedReader(new FileReader(backup));
		        StringBuffer buf = new StringBuffer();
		        String sStr = null;
		        while (fin.ready()) {
		            sStr = fin.readLine() + "\n";
		            history.add(sStr);
		            buf.append(sStr);
		        }
		        fin.close();
				textPane.setText(buf.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void saveBackup() {
		File backupdir = new File(AddOnManager.getPackageUserDir() + BeasyStudio.PACKAGENAME);
		if (!backupdir.exists()) {
			backupdir.mkdirs();
		}
		File backup = new File(AddOnManager.getPackageUserDir() + BeasyStudio.PACKAGENAME + BACKUP_FILE);
		try {
	        FileWriter outfile = new FileWriter(backup);
	        for (String s : history) {
	        	outfile.write(s);
	        }
	        outfile.close();				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add(String s) {
		if (s.matches("^\\s*$")) {
			return;
		}
		history.add(s + "\n");
		filter();
	}
	
	void filter() {
		String filter = searchField.getText();
		if (filter.equals("")) {
	        StringBuffer buf = new StringBuffer();
	        for (String s : history) {
	        	buf.append(s);
	        }
	        textPane.setText(buf.toString());
		} else {
	        StringBuffer buf = new StringBuffer();
	        for (String s : history) {
	        	if (s.contains(filter)) {
	        		buf.append(s);
	        	}
	        }
	        textPane.setText(buf.toString());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.isControlDown() && e.getKeyCode() == 70) {
			searchField.requestFocus();
		}
	}
}
