package beasy.shell;



import jam.framework.DocumentFrame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import beast.app.draw.MyAction;
import beast.app.util.Utils;
import beast.app.util.Utils6;
import beast.util.AddOnManager;
import beasy.JConsole;

public class BeasyStudio extends JSplitPane {
	public final static String ICONPATH = "beasy/shell/icons/";
	public final static String PACKAGENAME = "/compactanalysis/";
	public final static String VERSION = "0.0.1";
	
	private static final long serialVersionUID = 1L;

	JFrame frame;
	JSplitPane splitpaneleft;
	JSplitPane splitpaneright;

	//JTabbedPane helpPaneTab;
	JTabbedPane rightUpperPaneTab;
	JTabbedPane rightLowerPaneTab;
	//VariablesPanel variablesPane;
	HistoryPanel historyPane;
	public EditorPanel editorPanel;
	
//	public JPanel plotPane;
	JConsole console;
	public HelpBrowser helpPane;
	//ClassBrowser classBrowser;
	public Interpreter interpreter;

	public BeasyStudio(String[] args) {
		super(JSplitPane.HORIZONTAL_SPLIT);
		// TODO process arguments
	}

	private void setup() {
		splitpaneleft = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		editorPanel = new EditorPanel(this);
		splitpaneleft.add(editorPanel);
		
		console = new JConsole(this);
		splitpaneleft.add(console);
	

		splitpaneright = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		//variablesPane = new VariablesPanel();
		
		rightUpperPaneTab = new JTabbedPane();
		//rightUpperPaneTab.addTab("Variables", variablesPane);
		
		historyPane = new HistoryPanel(this);
		rightUpperPaneTab.addTab("History", historyPane);
		
		splitpaneright.add(rightUpperPaneTab);
		
		rightLowerPaneTab = new JTabbedPane();
		
		helpPane = new HelpBrowser();
		rightLowerPaneTab.addTab("Help", helpPane);

//		classBrowser = new ClassBrowser();
//		//TODO: let the splitter pane determine size
//		classBrowser.setMaximumSize(new Dimension(512,512));
//		classBrowser.setSize(new Dimension(512,512));
//
//		try {
//			classBrowser.init();
//		} catch (ClassPathException e) {
//			e.printStackTrace();
//		}
//		//rightLowerPaneTab.addTab("Class Browser", classBrowser);

	
		
//		plotPane = new JPanel();
//		plotPane.add(new JLabel("dummy plot panel"));
//		JScrollPane plotScrollPane = new JScrollPane(plotPane);
//		rightLowerPaneTab.addTab("Plots", plotScrollPane);
		
		//PackagesPanel packagePanel = new PackagesPanel();
		//rightLowerPaneTab.addTab("Pacakges", packagePanel);
		
		
		splitpaneright.add(rightLowerPaneTab);

		
		add(splitpaneleft);
		add(splitpaneright);
		console.historyPanel = historyPane;

		//JSplitPane hsplitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
//		hsplitpane.add(splitpane);
		//hsplitpane.add(splitpane2);
		
		//add(hsplitpane);
		//		//setRowHeaderView(view);
//		JScrollPane pane = new JScrollPane(cli);
//		add(pane);
	}

	protected boolean quit() {
		// TODO: do we want to save anything?
		return true;
	}

	public void update() {
//		Variable [] vars = interpreter.getNameSpace().getDeclaredVariables();
//		variablesPane.update(vars);
	}
	
	void doAbout() {
		JOptionPane.showMessageDialog(frame, "BEAST shell version " + VERSION);
	}
	
	void doQuit() {
        if (!quit()) {
            return;
        }
        historyPane.saveBackup();
        editorPanel.saveStatus();
        frame.dispose();
        //intances--;
        //if (intances == 0) {
            System.exit(0);
        //}
	}
	
	private void setNameCompletion() {
//		if (true) {
//			// TODO: enable auto completion
//			return;
//		}
        // Access to read classpath is protected 
//        try {
//	        NameCompletionTable nct = new NameCompletionTable();
//	        nct.add( interpreter.getNameSpace() );
//	        try {
//	        	BshClassManager bcm = interpreter.getNameSpace().getClassManager();
//	            if (bcm != null ) {
//	            	 NameSource classNamesSource = ((ClassManagerImpl) bcm).getClassPath();
//	                 nct.add( classNamesSource );
//	            }
//	        } catch ( ClassPathException e ) {
//	                throw new RuntimeException("classpath exception in name compl:"+e);
//	        }
//	        console.setNameCompletion( nct );
//        // end setup name completion
//        } catch ( SecurityException e ) { }
	}
	
	Action a_new =  new MyAction("New", "Start new editor", "new", KeyEvent.VK_N) {
        private static final long serialVersionUID = 1;

        public void actionPerformed(ActionEvent ae) {
        	editorPanel.doNew();
        }
    };

	Action a_open =  new MyAction("Load", "Open file for editing", "open", KeyEvent.VK_O) {
        private static final long serialVersionUID = 1;

        public void actionPerformed(ActionEvent ae) {
        	editorPanel.doOpen();
        }
    };

    Action a_save =  new MyAction("Save", "Save Editor", "save", KeyEvent.VK_S) {
        private static final long serialVersionUID = 1;

        public void actionPerformed(ActionEvent ae) {
        	editorPanel.doSave();
        } // actionPerformed
        
        public boolean isEnabled() {
        	return editorPanel.hasEditors();
        };
    }; // class ActionSave
	
    Action a_saveall =  new MyAction("Save All", "Save All Editors", "saveall", -1) {
        private static final long serialVersionUID = 1;

        public void actionPerformed(ActionEvent ae) {
        	editorPanel.doSaveAll();
        } // actionPerformed
        
        public boolean isEnabled() {
        	return editorPanel.hasEditors();
        };
    }; // class ActionSave

    Action a_saveas =  new MyAction("Save As", "Save Model As", "saveas", -1) {
        private static final long serialVersionUID = 1;

        public void actionPerformed(ActionEvent ae) {
        	editorPanel.saveAs();
        } // actionPerformed
        
        public boolean isEnabled() {
        	return editorPanel.hasEditors();
        };
    }; // class ActionSaveAs
	
    MyAction a_quit = new MyAction("Exit", "Exit Program", "exit", KeyEvent.VK_F4) {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent ae) {
            // if (!m_doc.m_bIsSaved) {
            if (!quit()) {
                return;
            }
            System.exit(0);
        }
    }; // class ActionQuit

	Action a_find =  new MyAction("Find", "Find in text", "find", KeyEvent.VK_F) {
        private static final long serialVersionUID = 1;

        public void actionPerformed(ActionEvent ae) {
        	Component focusOwner = frame.getFocusOwner();
        	while (focusOwner != null) {
	        	if (editorPanel == focusOwner) {
	        		editorPanel.searchField.requestFocus();
	        	} else if (helpPane == focusOwner) {
	        		helpPane.searchField.requestFocus();
	        	} else if (historyPane == focusOwner) {
	        		historyPane.searchField.requestFocus();	
	        	}
        		focusOwner = focusOwner.getParent();
        	}
        }
        
// why does this not work?
//        public boolean isEnabled() {
//        	Component focusOwner = frame.getFocusOwner();
//        	while (focusOwner != null) {
//	        	if (editorPanel == focusOwner || helpPane == focusOwner || historyPane == focusOwner) {
//	        		return true;	
//	        	}
//        		focusOwner = focusOwner.getParent();
//        	}
//        	return false;
//        };
    };

	Action a_findReplace=  new MyAction("Find/Replace", "Find and replace text", "findreplace", KeyEvent.VK_H) {
        private static final long serialVersionUID = 1;

        public void actionPerformed(ActionEvent ae) {
       		editorPanel.doSearchReplace();
        }

//        public boolean isEnabled() {
//        	Component focusOwner = frame.getFocusOwner();
//        	while (focusOwner != null) {
//	        	if (editorPanel == focusOwner) {
//	        		return true;	
//	        	}
//        		focusOwner = focusOwner.getParent();
//        	}
//        	return false;
//        };
    };

    MyAction a_run = new MyAction("Run", "Run script in current editor", "run", KeyEvent.VK_R) {
        private static final long serialVersionUID = -1;

        public void actionPerformed(ActionEvent ae) {
        	editorPanel.runCurrent();
        }
    };

    MyAction a_runselection = new MyAction("Run selection", "Run selection in current editor", "runselection", -1) {
        private static final long serialVersionUID = -1;

        public void actionPerformed(ActionEvent ae) {
        	editorPanel.runCurrentSelection();
        }
    };

    MyAction a_about = new MyAction("About", "Help about", "about", -1) {
        private static final long serialVersionUID = -1;

        public void actionPerformed(ActionEvent ae) {
        	Help.help("doc/html/about.html");
        }
    }; // class ActionAbout

    MyAction a_help = new MyAction("Help", "Help on current panel", "help", -1) {
        private static final long serialVersionUID = -1;

        public void actionPerformed(ActionEvent ae) {
        	Help.help("doc/html/index.html");
        }
    }; // class ActionHelp
	
	private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        menuBar.add(fileMenu);
        fileMenu.add(a_new);
        fileMenu.add(a_open);
        fileMenu.add(a_save);
        fileMenu.add(a_saveall);
        fileMenu.add(a_saveas);
        fileMenu.addSeparator();
        if (!Utils.isMac()) {
            fileMenu.addSeparator();
            fileMenu.add(a_quit);
        }

        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);
        editMenu.setMnemonic('E');
        editMenu.add(a_find);
        editMenu.add(a_findReplace);

        JMenu runMenu = new JMenu("Run");
        menuBar.add(runMenu);
        runMenu.setMnemonic('R');
        runMenu.add(a_run);
        runMenu.add(a_runselection);
        
//        JMenu viewMenu = new JMenu("Run");
//        menuBar.add(viewMenu);
//        viewMenu.setMnemonic('V');

        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        helpMenu.setMnemonic('H');
        helpMenu.add(a_help);
        //helpMenu.add(a_citation);
        if (!Utils.isMac()) {
            helpMenu.add(a_about);
        }

        return menuBar;
	}

	private static void loadJavaFX() {
		try {
			// JavaFX already loaded?
			Class x = Class.forName("javafx.embed.swing.JFXPanel");
			// if we got here, all is fine.
			return;
		} catch (ClassNotFoundException e) {
			// JavaFX is not loaded yet
			try {
				String home = System.getenv("JAVA_HOME");
				if (home != null) {
					AddOnManager.addURL(new URL("file:" + home + "/jre/lib/jfxrt.jar"));
					Class.forName("javafx.embed.swing.JFXPanel");
					return;
				}
				String jarfile = System.getenv("JAVAFX_JAR");
				if (jarfile != null) {
					AddOnManager.addURL(new URL("file:" + jarfile));
					Class.forName("javafx.embed.swing.JFXPanel");
					return;
				}
				File f = new File("/opt/java/jre/lib/jfxrt.jar"); 
				if (f.exists()) {
					AddOnManager.addURL(new URL("file:" + f.getPath()));
					Class.forName("javafx.embed.swing.JFXPanel");
					return;
				}
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(null, "Could not load JavaFX. Run with java 7 or higher, or set the JAVAFX_JAR environment variable to the location of jfxrt.jar or install jfxrt.jar at /opt/java/jre/lib/jfxrt.jar. -- exiting now");
		System.exit(0);
	}

	public static void main(String[] args) {
		Utils6.startSplashScreen();

		// try to load JavaFX
		loadJavaFX();
	
		JFrame frame = new JFrame();
		final BeasyStudio studio = new BeasyStudio(args);
		studio.setup();
		studio.frame = frame;
		
		Help.studio = studio;
		
		frame.setLayout(new BorderLayout());
		frame.add(studio, BorderLayout.CENTER);
		studio.setSize(1024, 768);
		frame.setSize(new Dimension(2024, 1024));
		frame.setVisible(true);
		studio.setDividerLocation(0.5);
		frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	studio.doQuit();
            }
        });

		frame.setJMenuBar(studio.createMenuBar());
		
        if (Utils.isMac()) {
            // set up application about-menu for Mac
            // Mac-only stuff
            try {
                URL url = ClassLoader.getSystemResource(BeasyStudio.ICONPATH + "beauti.png");
                Icon icon = null;
                if (url != null) {
                    icon = new ImageIcon(url);
                } else {
                    System.err.println("Unable to find image: " + BeasyStudio.ICONPATH + "beauti.png");
                }
                jam.framework.Application application = new jam.framework.MultiDocApplication(null, "BEAUti", "about", icon) {

                    @Override
                    protected JFrame getDefaultFrame() {
                        return null;
                    }

                    @Override
                    public void doQuit() {
                        studio.doQuit();
                    }

                    @Override
                    public void doAbout() {
                       studio.doAbout();
                    }

                    @Override
                    public DocumentFrame doOpenFile(File file) {
                        return null;
                    }

                    @Override
                    public DocumentFrame doNew() {
                        return null;
                    }
                };
                jam.mac.Utils.macOSXRegistration(application);
            } catch (Exception e) {
                // ignore
            } 
            try {
                Class<?> class_ = Class.forName("jam.maconly.OSXAdapter");
                Method method = class_.getMethod("enablePrefs", boolean.class);
                method.invoke(null, false);
            } catch (java.lang.Exception e) {
                // ignore
            } 
        } else {
        	try {
        	    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        	        if ("Nimbus".equals(info.getName())) {
        	            UIManager.setLookAndFeel(info.getClassName());
        	            break;
        	        }
        	    }
        	} catch (Exception e) {
        	    // If Nimbus is not available, you can set the GUI to another look and feel.
        	}
        }
		
		
		studio.interpreter = new Interpreter(studio.console, studio);
		studio.interpreter.studio = studio;
		studio.setNameCompletion();
		studio.splitpaneleft.setDividerLocation(0.3);
		studio.splitpaneright.setDividerLocation(0.5);
		studio.setDividerLocation(0.7);
		Utils6.endSplashScreen();
		studio.console.requestFocusInWindow();
//		new Thread() {
//			public void run() {
//				try {sleep(15000);}catch (Exception e){}
//				studio.setDividerLocation(0.7);
//			};
//		}.run();
		//studio.interpreter.run();
	}

} // RBEASTStudio
