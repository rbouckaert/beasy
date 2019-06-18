package beasy.shell;


import static javafx.concurrent.Worker.State.FAILED;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.application.*;
import javafx.beans.value.*;
import javafx.collections.*;
import javafx.concurrent.*;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.scene.*;
import javafx.scene.web.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

//import org.fife.ui.rtextarea.SearchContext;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventListener;


import beast.app.DocMaker;
import beast.util.BEASTClassLoader;
import beast.util.PackageManager;

public class HelpBrowser extends JPanel implements ActionListener { //implements HyperlinkListener {
    private static final long serialVersionUID = 1L;
    final static String INITIAL_PAGE = "doc/html/index.html";
    final static String BEAST_DOC_DIR = PackageManager.getPackageUserDir() + BeasyStudio.PACKAGENAME + "doc/BeastObjects/";
    
    
    /**
     * generates HTML pages *
     */
    static public DocMaker docMaker;
    static boolean docsGenerated = false;
    /**
     * browser stack *
     */
    List<Object> pages = new ArrayList<Object>();
    
    int currentPage = 0;

    JEditorPane editorPane;
    JButton btnPrev;
    JButton btnNext;
    JButton btnHome;
    JButton btnExport;
	JTextField searchField;
	Image image;
    
    private final JFXPanel jfxPanel = new JFXPanel();
    private WebEngine engine;
    public WebEngine getWebEngine() {
    	return engine;
    }
    
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JLabel lblStatus = new JLabel();

    //private final JButton btnGo = new JButton("Go");
    //private final JTextField txtURL = new JTextField();
    private final JProgressBar progressBar = new JProgressBar();     
    

    List<String> importCommands;
    public HelpBrowser() {
    	importCommands = new ArrayList<String>();
		importCommands.add("beast/commands/");
		importCommands.add("bsh/commands/");

    	setLayout(new BorderLayout());
        if (docMaker == null) {
        	if (!new File(BEAST_DOC_DIR).exists()) {
        		new File(BEAST_DOC_DIR).mkdirs();
        	}
            docMaker = new DocMaker(new String[]{BEAST_DOC_DIR});
        }

        // initialise JEditorPane
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	initComponents();
            }
       });

        JScrollPane scroller = new JScrollPane(panel);
//        editorPane = new JEditorPane();
//        editorPane.setEditable(false);
//        editorPane.setContentType("text/html");
//        editorPane.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
//
//        editorPane.addHyperlinkListener(this);
//
//        JScrollPane scroller = new JScrollPane(editorPane);

        // add the navigation buttons at the top
        JToolBar toolBar = new JToolBar();
        
		btnPrev = new JButton("");
		btnPrev.setIcon(EditorPanel.getIcon("/beasy/shell/icons/prev.png", this));
		btnPrev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goPrev();
			}

		});
		btnPrev.setToolTipText("Show previous help page");
		toolBar.add(btnPrev);
		
		btnNext = new JButton("");
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				goNext();
			}
		});
		btnNext.setIcon(EditorPanel.getIcon("/beasy/shell/icons/next.png", this));
		btnNext.setToolTipText("Show next help page");
		toolBar.add(btnNext);

		btnHome = new JButton("");
		btnHome.setIcon(EditorPanel.getIcon("/beasy/shell/icons/home.png", this));
		btnHome.setToolTipText("Goto home of help");
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goHome();
			}
		});
		toolBar.add(btnHome);
		//btnPrev.setMnemonic(KeyEvent.VK_RIGHT);
		//btnNext.setMnemonic(KeyEvent.VK_LEFT);
		
//		btnExport = new JButton("");
//		btnExport.setIcon(new ImageIcon(EditorPanel.class
//				.getResource("/beasy/shell/icons/export.png")));
//		btnExport.setToolTipText("Export current chart to bitmap or pdf");
//		btnExport.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				doExport();
//			}
//		});
//		toolBar.add(btnExport);


		
		ImageIcon icon = EditorPanel.getIcon("/beasy/shell/icons/search.png", this);
		if (icon != null) {
			image = icon.getImage();
		}
		searchField = new JTextField() {
			private static final long serialVersionUID = 1L;

				@Override
				protected void paintComponent(Graphics g) {  
	                super.paintComponent(g); 
	                if (image != null) {
		                int y = (getHeight() - image.getHeight(null))/2;
		                int x = getWidth() - 17;
		                g.drawImage(image, x, y, this);
	                } else {
		                int y = getHeight()/2;
		                int x = getWidth() - 17;
		                g.drawString("H", x, y);
	                }
	            }  
	        };  
	        searchField.setToolTipText("search help page by matching text");
		toolBar.add(searchField);
		searchField.setColumns(8);
		searchField.addActionListener(this);
//		searchField.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                filter();
//            }
//
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//            	filter();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//            	filter();
//            }
//        });
		toolBar.add(searchField);
		
		JButton prevButton = new JButton("");
		prevButton.setToolTipText("Find Previous");
		icon = EditorPanel.getIcon("/beasy/shell/icons/findup.png", this);
		if(icon != null) {
			prevButton.setIcon(icon);
		}
		prevButton.setActionCommand("FindPrev");
		prevButton.addActionListener(this);
		toolBar.add(prevButton);

		final JButton nextButton = new JButton("");
		icon = EditorPanel.getIcon("/beasy/shell/icons/finddown.png", this);
		if (icon != null) {
			nextButton.setIcon(icon);
		}
		nextButton.setToolTipText("Find Next");
		nextButton.setActionCommand("FindNext");
		nextButton.addActionListener(this);
		toolBar.add(nextButton);

		
		
		add(toolBar, BorderLayout.NORTH);
        add(scroller, BorderLayout.CENTER);

        try {
        	String path = new File(".").getAbsolutePath();
        	if (new File(path  + "/" + INITIAL_PAGE).exists()) {
            	setURL(new URL("file://" + path  + "/" + INITIAL_PAGE));
        	} else {
            	setURL(new URL("file://" + PackageManager.getPackageUserDir() + BeasyStudio.PACKAGENAME + INITIAL_PAGE));
        	}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
    } // c'tor

    
//    protected void doExport() {
//	    Platform.runLater(new Runnable() { 
//	    	public void run() { 
//				File file = beast.app.shell.Utils.getSaveFile("Save chart as", new File("."), "Image file (*.pdf, *.png, *.jpg, *.bmp, *.tex)", "*.pdf", "*.png", "*.jpg", "*.bmp", "*.tex");
//				if (file != null) {
//					ChartPanel.doExport(file, panel);
//				}
//	    	} 
//	    });
//	}

	void filter() {
    	actionPerformed(null);
    }
    
    boolean backward = false;
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		backward = "FindPrev".equals(command);
		
		
        Platform.runLater(new Runnable() {
            @Override 
            public void run() {
				// Create an object defining our search parameters.
				//SearchContext context = new SearchContext();
				String text = searchField.getText();
				if (text.length() == 0) {
					return;
				}
				Object o = engine.executeScript("window.find('" + text + "', false, " + backward +")");
				if (!(boolean)o) {
					searchField.setBackground(Color.RED);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
					}
					searchField.setBackground(Color.WHITE);
				}
            }
        });
	}
    
    private void initComponents() {
        createScene();
 
//        ActionListener al = new ActionListener() {
//            @Override 
//            public void actionPerformed(ActionEvent e) {
//                loadURL(txtURL.getText());
//            }
//        };
// 
//        btnGo.addActionListener(al);
//        txtURL.addActionListener(al);
  
        progressBar.setPreferredSize(new Dimension(150, 18));
        progressBar.setStringPainted(true);
  
//        JPanel topBar = new JPanel(new BorderLayout(5, 0));
//        topBar.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
//        topBar.add(txtURL, BorderLayout.CENTER);
//        topBar.add(btnGo, BorderLayout.EAST);
 
        JPanel statusBar = new JPanel(new BorderLayout(5, 0));
        statusBar.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
        statusBar.add(lblStatus, BorderLayout.CENTER);
        statusBar.add(progressBar, BorderLayout.EAST);
 
        //panel.add(topBar, BorderLayout.NORTH);
        panel.add(jfxPanel, BorderLayout.CENTER);
        panel.add(statusBar, BorderLayout.SOUTH);
        
        //getContentPane().add(panel);
        jfxPanel.setPreferredSize(new Dimension(400, 400));
        setPreferredSize(new Dimension(400, 400));
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //pack();

    }
 
    private void createScene() {
 
        Platform.runLater(new Runnable() {
            @Override 
            public void run() {
 
                WebView view = new WebView();
                engine = view.getEngine();
 
				//engine.load("file:lib/jquery-2.1.1.min.js");
				//engine.load("file:lib/highlight.js");

                engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
                	@Override
                	public void changed(ObservableValue ov, State oldState, State newState) {
                        if (newState == Worker.State.SUCCEEDED) {
                                // note next classes are from org.w3c.dom domain
                            EventListener listener = new EventListener() {
								@Override
								public void handleEvent(org.w3c.dom.events.Event evt) {
									String href = ((Element)evt.getTarget()).getAttribute("href");
									goToLink(href);
								}
                            };

                            org.w3c.dom.Document doc = engine.getDocument();
                            //Element el = doc.getElementById("a");
                            NodeList lista = doc.getElementsByTagName("a");
                            for (int i=0; i<lista.getLength(); i++)
                                ((org.w3c.dom.events.EventTarget)lista.item(i)).addEventListener("click", listener, false);
                        }
                    }                	
                	

                });
                engine.titleProperty().addListener(new ChangeListener<String>() {
                	@Override
                  public void changed(ObservableValue<? extends String> observable, String oldValue, final String newValue) {
                      SwingUtilities.invokeLater(new Runnable() {
                          @Override 
                          public void run() {
                        	  lblStatus.setText(newValue);
                          }
                      });
                  }				
                });
 
//                engine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
//                    @Override 
//                    public void handle(final WebEvent<String> event) {
//                        SwingUtilities.invokeLater(new Runnable() {
//                            @Override 
//                            public void run() {
//                                lblStatus.setText(event.getData());
//                                System.out.println(event.getData());
//                            }
//                        });
//                    }
//                });
// 
//                engine.locationProperty().addListener(new ChangeListener<String>() {
//                    @Override
//                    public void changed(ObservableValue<? extends String> ov, String oldValue, final String newValue) {
//                        SwingUtilities.invokeLater(new Runnable() {
//                            @Override 
//                            public void run() {
//                                txtURL.setText(newValue);
//                            }
//                        });
//                    }
//                });
 
                engine.getLoadWorker().workDoneProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, final Number newValue) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override 
                            public void run() {
                                progressBar.setValue(newValue.intValue());
                            }
                        });
                    }
                });

                engine.getLoadWorker()
                        .exceptionProperty()
                        .addListener(new ChangeListener<Throwable>() {
 
                            @Override
							public void changed(ObservableValue<? extends Throwable> o, Throwable old, final Throwable value) {
                                if (engine.getLoadWorker().getState() == FAILED) {
                                    SwingUtilities.invokeLater(new Runnable() {
                                        @Override public void run() {
                                            JOptionPane.showMessageDialog(
                                                    panel,
                                                    (value != null) ?
                                                    engine.getLocation() + "\n" + value.getMessage() :
                                                    engine.getLocation() + "\nUnexpected error.",
                                                    "Loading error...",
                                                    JOptionPane.ERROR_MESSAGE);
                                        }
                                    });
                                }
                            }
                        });
                
                jfxPanel.setScene(new Scene(view));
                jfxPanel.addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(java.awt.event.KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void keyReleased(java.awt.event.KeyEvent e) {
						// TODO Auto-generated method stub
						System.out.println(e);
						if (e.isControlDown() && e.getKeyCode() == 70) {
							searchField.requestFocus();
						}
						if (e.isAltDown() && e.getKeyCode() == 37) {
							goPrev();
						}
						if (e.isAltDown() && e.getKeyCode() == 39) {
							goNext();
						}
						if (e.getKeyCode() == 109) {
							zoomOut();
						}
						if (e.getKeyCode() == 107) {
							zoomIn();
						}
						
					}

					@Override
					public void keyPressed(java.awt.event.KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
				});

            }
        });
    }
    
    public void loadURL(final String url) {
        Platform.runLater(new Runnable() {
            @Override 
            public void run() {
                String tmp = toURL(url);
 
                if (tmp == null) {
                    tmp = toURL("http://" + url);
                }
 
                engine.load(tmp);
            }
        });
        zoom(zoom);
    }

    public void loadContent(final String content) {
        Platform.runLater(new Runnable() {
            @Override 
            public void run() {
                engine.loadContent(content);
            }
        });
        zoom(zoom);
    }

    private static String toURL(String str) {
        try {
            return new URL(str).toExternalForm();
        } catch (MalformedURLException exception) {
                return null;
        }
    }
    
    
	public void setURL(URL url) {
    	while (pages.size() > currentPage + 1) {
        	pages.remove(pages.size() - 1);
    	}
    	pages.add(url);
    	currentPage = pages.size() - 1;
        updateState();
    }

    public void setText(String sHTML) {
    	while (pages.size() > currentPage + 1) {
        	pages.remove(pages.size() - 1);
    	}
    	pages.add(sHTML);
    	currentPage = pages.size() - 1;
        updateState();
    }

    
    
    protected void goHome() {
    	currentPage = 0;
        updateState();		
	}

    
    public void goPrev()
    {    
      final WebHistory history=engine.getHistory();
      ObservableList<WebHistory.Entry> entryList=history.getEntries();
      int currentIndex=history.getCurrentIndex();
//      Out("currentIndex = "+currentIndex);
//      Out(entryList.toString().replace("],","]\n"));

      Platform.runLater(new Runnable() { @Override
	public void run() { history.go(-1); } });
      entryList.get(currentIndex>0?currentIndex-1:currentIndex).getUrl();
      zoom(zoom);
    }

    public void goNext()
    {    
      final WebHistory history=engine.getHistory();
      ObservableList<WebHistory.Entry> entryList=history.getEntries();
      int currentIndex=history.getCurrentIndex();
//      Out("currentIndex = "+currentIndex);
//      Out(entryList.toString().replace("],","]\n"));

      Platform.runLater(new Runnable() { @Override
	public void run() { history.go(1); } });
      entryList.get(currentIndex<entryList.size()-1?currentIndex+1:currentIndex).getUrl();
      zoom(zoom);
    }    
    
//	private void goPrev() {
//        if (currentPage > 0) {
//            currentPage--;
//        }
//        updateState();
//	}
//
//	protected void goNext() {
//        if (currentPage < pages.size() - 1) {
//            currentPage++;
//        }
//        updateState();
//	}

//    @Override
//    public void hyperlinkUpdate(HyperlinkEvent link) {
////        try {
//            HyperlinkEvent.EventType type = link.getEventType();
//            if (type == HyperlinkEvent.EventType.ACTIVATED) {
    private void goToLink(String href) {
    		try {
            	// check whether it is help on a BEASTObject
                String beastObject = href;//link.getDescription();
                if (beastObject.equals("BEASTObjectDocs")) {
                    // magic initialisation of DocHelper
                	try {
                		if (!docsGenerated) {
                			docMaker.generateDocs();
                			docsGenerated = true;
                		}
						setURL(new URL("file://" + BEAST_DOC_DIR + "/contents.html"));
						return;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
                beastObject = beastObject.replaceAll(".html", "");
				Class c = null;
                try {
                	c = BEASTClassLoader.forName(beastObject);
				} catch (ClassNotFoundException e1) {
					c = null;
				}
                if (c != null) {
                    try {
                    	setText(docMaker.getHTML(beastObject, false));
                    } catch (Exception e) {
                    }
                } else {
                	
                }
                	
            	// check whether it is a regular page on the file system
            	String docPage = href;//.getDescription();
            	if (canLoadPage(docPage)) {
            		return;
            	}

            	// check whether it is a BeastShell command
            	// TODO
            	for (String path : importCommands) {
            		String scriptPath = path +"/"+ docPage +".bsh";
            		InputStream in = BEASTClassLoader.classLoader.getResourceAsStream( path );
            		if (in != null) {
            			StringBuffer buf = new StringBuffer();
            			try {
            				char ch = ' ';
            				while (ch != '\n') {
            					ch = (char) in.read();
            					buf.append(ch);
            				}
            				String header = buf.toString();
            				if (header.indexOf("@see(")>=0) {
            					header = header.substring(header.indexOf("@see("), header.indexOf(")"));
                            	if (canLoadPage(header)) {
                            		return;
                            	}                					
            				}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
            		}

                	
                	// give up, it is probably an external link then
					setURL(new URL(href));//link.getURL());
                	
                }

                	
        } catch (Exception e) {
            // ignore
            System.err.println(e.getMessage());
        }
    } // hyperlinkUpdate

    private boolean canLoadPage(String docPage) {
    	if (new File(docPage).exists()) {
        	String path = new File(".").getAbsolutePath();
           	try {
				setURL(new URL("file://" + path  + "/" + docPage));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
           	return true;
    	} else if(new File(PackageManager.getPackageUserDir() + BeasyStudio.PACKAGENAME + docPage).exists()) {
        	try {
				setURL(new URL("file://" + PackageManager.getPackageUserDir() + BeasyStudio.PACKAGENAME + docPage));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
           	return true;
    	}
    	return false;
	}

	/**
     * change html text and enable/disable buttons (where appropriate) *
     */
    void updateState() {
        Object page = pages.get(currentPage);
        if (page instanceof String) {
        	//editorPane.setText((String) page);
        	loadContent((String) page);
        } else if (page instanceof URL) {
	        //try {
        	loadURL(((URL) page).toString());
	        	//editorPane.setPage((URL) page);
	        //} catch (IOException e) {
	        //	engine.loadContent(e.getMessage());
	        	//editorPane.setText(e.getMessage());
	        //}
        }

        if (engine != null) {
	        final WebHistory history=engine.getHistory();
	        ObservableList<WebHistory.Entry> entryList=history.getEntries();
	        int currentIndex=history.getCurrentIndex();
	        btnPrev.setEnabled(currentIndex > 0);
	        btnNext.setEnabled(currentIndex < entryList.size() - 1);
        }
    } // updateState


    double zoom = 1.0;
    
	private void zoomIn() {
		zoom(zoom * 1.1);
	}

	private void zoomOut() {
		zoom(zoom /1.1);
		
	}
    
	void zoom(double _zoom) {
		this.zoom = _zoom;
		
        Platform.runLater(new Runnable() {
            @Override 
            public void run() {
				engine.executeScript("document.body.style.zoom=" + zoom);
            }
        });
	}

}
