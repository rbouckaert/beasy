package beasy.shell;




import jam.framework.DocumentFrame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import beast.app.beauti.BeautiSubTemplate;
import beast.app.beauti.PartitionContext;
import beast.app.draw.InputEditor;
import beast.app.draw.MyAction;
import beast.app.util.Utils;
import beast.app.util.Utils6;
import beast.core.parameter.RealParameter;
import beast.math.distributions.Prior;
import beast.math.distributions.Uniform;
import beast.util.BEASTClassLoader;
import beast.util.PackageManager;
import beasy.BeasyInterpreter;
import beasy.Help;
import beasy.JConsole;

public class BeasyStudio extends JSplitPane {
	public final static String ICONPATH = "beasy/shell/icons/";
	public final static String PACKAGENAME = "/beasy/";
	
	private static final long serialVersionUID = 1L;

	JFrame frame;
	public JFrame getFrame() {
		return frame;
	}

	JSplitPane splitpaneleft;
	JSplitPane splitpaneright;

	//JTabbedPane helpPaneTab;
	JTabbedPane rightUpperPaneTab;
	public JTabbedPane rightLowerPaneTab;
	//VariablesPanel variablesPane;
	HistoryPanel historyPane;
	public EditorPanel editorPanel;
	
//	public JPanel plotPane;
	JConsole console;
	public HelpBrowser helpPane;
	public JTextPane hintsPane;
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
		
		JPanel panel = new JPanel(new BorderLayout());
		JScrollPane scroller = new JScrollPane(panel);
		hintsPane = new JTextPane();
		panel.add(hintsPane);
		rightLowerPaneTab.addTab("Hints", scroller);

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
		JOptionPane.showMessageDialog(frame, "Beasy Studio version " + BeasyInterpreter.beasyVersion());
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

        @Override
		public void actionPerformed(ActionEvent ae) {
        	editorPanel.doNew();
        }
    };

	Action a_open =  new MyAction("Load", "Open file for editing", "open", KeyEvent.VK_O) {
        private static final long serialVersionUID = 1;

        @Override
		public void actionPerformed(ActionEvent ae) {
        	editorPanel.doOpen();
        }
    };

    Action a_save =  new MyAction("Save", "Save Editor", "save", KeyEvent.VK_S) {
        private static final long serialVersionUID = 1;

        @Override
		public void actionPerformed(ActionEvent ae) {
        	editorPanel.doSave();
        } // actionPerformed
        
        @Override
		public boolean isEnabled() {
        	return editorPanel.hasEditors();
        };
    }; // class ActionSave
	
    Action a_saveall =  new MyAction("Save All", "Save All Editors", "saveall", -1) {
        private static final long serialVersionUID = 1;

        @Override
		public void actionPerformed(ActionEvent ae) {
        	editorPanel.doSaveAll();
        } // actionPerformed
        
        @Override
		public boolean isEnabled() {
        	return editorPanel.hasEditors();
        };
    }; // class ActionSave

    Action a_saveas =  new MyAction("Save As", "Save Model As", "saveas", -1) {
        private static final long serialVersionUID = 1;

        @Override
		public void actionPerformed(ActionEvent ae) {
        	editorPanel.saveAs();
        } // actionPerformed
        
        @Override
		public boolean isEnabled() {
        	return editorPanel.hasEditors();
        };
    }; // class ActionSaveAs
	
    MyAction a_quit = new MyAction("Exit", "Exit Program", "exit", KeyEvent.VK_F4) {
		private static final long serialVersionUID = 1L;

		@Override
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

        @Override
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

        @Override
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

        @Override
		public void actionPerformed(ActionEvent ae) {
        	editorPanel.runCurrent();
        }
    };

    MyAction a_runselection = new MyAction("Run selection", "Run selection in current editor", "runselection", -1) {
        private static final long serialVersionUID = -1;

        @Override
		public void actionPerformed(ActionEvent ae) {
        	editorPanel.runCurrentSelection();
        }
    };

    MyAction a_about = new MyAction("About", "Help about", "about", -1) {
        private static final long serialVersionUID = -1;

        @Override
		public void actionPerformed(ActionEvent ae) {
        	Help.help("doc/html/about.html");
        }
    }; // class ActionAbout

    MyAction a_help = new MyAction("Help", "Help on current panel", "help", -1) {
        private static final long serialVersionUID = -1;

        @Override
		public void actionPerformed(ActionEvent ae) {
        	Help.help("doc/html/index.html");
        }
    }; // class ActionHelp
	
    
    MyAction a_distr = new MyAction("View distribution", "View parametric distribution and its graph", "distr", -1) {

        @Override
		public void actionPerformed(ActionEvent ae) {
            JFrame frame = new JFrame("Parametric distribution");
            // start with prior with uniform(0,1) distribution
            final Prior prior = new Prior();
            Uniform uniform = new Uniform();
            uniform.setID("Uniform.0");
            uniform.initByName("lower","0.0","upper","1.0");
            prior.initByName("x", new RealParameter("0.0"), "distr", uniform);
            prior.setID("Parametric.Distribution");
            
            // create panel with parametric distribution viewer
            refreshDistributionPanel(frame, prior);

            frame.setSize(800, 400);
            frame.setVisible(true);
        }

		private void refreshDistributionPanel(JFrame frame, Prior prior) {
			// clear frame content in case it is refreshed after change of distribution  
			JRootPane p = (JRootPane) frame.getComponent(0);
			JLayeredPane p1 = (JLayeredPane) p.getComponent(1);
			JPanel p2 = (JPanel) p1.getComponent(0);
			p2.removeAll();
			
            final JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			
            // add combobox with parametric distributions
            List<BeautiSubTemplate> availableBEASTObjects = interpreter.doc.getInputEditorFactory().getAvailableTemplates(prior.distInput, prior, null, interpreter.doc);
            if (availableBEASTObjects.size() == 0) {
            	JOptionPane.showMessageDialog(null, "No Parametric distirbutions available\nDid you select a template with the template command?");
            }
            JComboBox<BeautiSubTemplate> comboBox = new JComboBox<BeautiSubTemplate>(availableBEASTObjects.toArray(new BeautiSubTemplate[]{}));
            panel.add(comboBox);
            
            String id = prior.distInput.get().getID();
            id = id.substring(0, id.indexOf('.'));
            for (BeautiSubTemplate template : availableBEASTObjects) {
                if (template.classInput.get() != null && template.getShortClassName().equals(id)) {
                    comboBox.setSelectedItem(template);
                }
            }
            
            try {
            	// add parametric input editor
                final InputEditor editor = interpreter.doc.getInputEditorFactory().createInputEditor(prior.distInput, prior, interpreter.doc);
                panel.add((Component) editor);
                comboBox.addActionListener(e -> {
                    @SuppressWarnings("unchecked")
        			JComboBox<BeautiSubTemplate> comboBox1 = (JComboBox<BeautiSubTemplate>) e.getSource();

                    BeautiSubTemplate template = (BeautiSubTemplate) comboBox1.getSelectedItem();
                    try {
                        template.createSubNet(new PartitionContext(), prior, prior.distInput, true);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    refreshDistributionPanel(frame, prior);
                });
			} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
            
            // add close button
            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> {
            	frame.setVisible(false);
            	frame.dispose();
            });
            panel.add(closeButton);
			
            frame.add(panel);
            frame.revalidate();
		}
    }; // class ActionViewDistribution

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
        helpMenu.add(a_distr);

        return menuBar;
	}

	private static void loadJavaFX() {
		try {
			// JavaFX already loaded?
			Class x = BEASTClassLoader.forName("javafx.embed.swing.JFXPanel");
			// if we got here, all is fine.
			return;
		} catch (ClassNotFoundException e) {
			// JavaFX is not loaded yet
			try {
				String home = System.getenv("JAVA_HOME");
				if (home != null) {
					PackageManager.addURL(new URL("file:" + home + "/jre/lib/jfxrt.jar"));
					BEASTClassLoader.forName("javafx.embed.swing.JFXPanel");
					return;
				}
				String jarfile = System.getenv("JAVAFX_JAR");
				if (jarfile != null) {
					PackageManager.addURL(new URL("file:" + jarfile));
					BEASTClassLoader.forName("javafx.embed.swing.JFXPanel");
					return;
				}
				File f = new File("/opt/java/jre/lib/jfxrt.jar"); 
				if (f.exists()) {
					PackageManager.addURL(new URL("file:" + f.getPath()));
					BEASTClassLoader.forName("javafx.embed.swing.JFXPanel");
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
		JOptionPane.showMessageDialog(null, "Could not load JavaFX. Run with Java 8 (preferred) or higher, or set the JAVAFX_JAR environment variable to the location of jfxrt.jar or install jfxrt.jar at /opt/java/jre/lib/jfxrt.jar. -- exiting now");
		System.exit(0);
	}

	public static void main(String[] args) {
		Utils6.startSplashScreen();

		// try to load JavaFX
		loadJavaFX();

		Utils.loadUIManager();
	
		JFrame frame = new JFrame();
		final BeasyStudio studio = new BeasyStudio(args);
		studio.setup();
		studio.frame = frame;
		
		Help.studio = studio;
		URL url = studio.getClass().getClassLoader().getResource("beasy/shell/beasySmall.png");
		Image image = Toolkit.getDefaultToolkit().createImage(url);
		frame.setIconImage(image);
		
		
		
		
		frame.setLayout(new BorderLayout());
		frame.add(studio, BorderLayout.CENTER);
		studio.setSize(1024, 768);
		frame.setSize(new Dimension(2024, 1024));
		frame.setVisible(true);
		studio.setDividerLocation(0.5);
		frame.addWindowListener(new WindowAdapter() {
            @Override
			public void windowClosing(WindowEvent e) {
            	studio.doQuit();
            }
        });

		frame.setJMenuBar(studio.createMenuBar());
		
        if (Utils.isMac()) {
            // set up application about-menu for Mac
            // Mac-only stuff
            try {
                url = BEASTClassLoader.classLoader.getResource("/beast/app/draw/icons/beauti.png");
                Icon icon = null;
                if (url != null) {
                    icon = new ImageIcon(url);
                } else {
                    System.err.println("Unable to find image: /beast/app/draw/icons/beauti.png");
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
                Class<?> class_ = BEASTClassLoader.forName("jam.maconly.OSXAdapter");
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
