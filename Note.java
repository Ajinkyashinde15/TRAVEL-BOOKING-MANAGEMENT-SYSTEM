import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.text.*;
import java.text.*;
import java.util.*;
import javax.swing.undo.*;
import javax.swing.event.*;
import java.net.*;



public class Note extends JFrame
{
	
	Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
	JTextArea area = new JTextArea(0,0);
	JScrollPane scroller = new JScrollPane(area);
	
	public JPopupMenu pop = new JPopupMenu();
	
	JMenu menu = new JMenu("Edit");
	JMenuItem menuItem = new JMenuItem("copy");
	JMenuItem menucut = new JMenuItem("cut");
	JMenuItem menuclear = new JMenuItem("clear");
	JMenuItem menupaste = new JMenuItem("paste");
	
	JToolBar toolBar = new JToolBar();
	
	JButton newFile = new JButton(new ImageIcon("new.jpg"));
	JButton openFile = new JButton(new ImageIcon("open.jpg"));
	JButton saveFile = new JButton(new ImageIcon("save.jpg"));
	
	JButton cutFile = new JButton(new ImageIcon("cut.jpg"));
	JButton copyFile = new JButton(new ImageIcon("copy.jpg"));
	JButton pasteFile = new JButton(new ImageIcon("paste.jpg"));
	
	JMenuBar menuBar = new JMenuBar();
	
	JMenu FILE = new JMenu("File");
	JMenu EDIT = new JMenu("Edit");
	JMenu FORMAT = new JMenu("Format");
	JMenu VIEW = new JMenu("View");
	//JMenu HELP = new JMenu("Help");
	
	JMenuItem NEWFILE = new JMenuItem("New", new ImageIcon("new.jpg"));
	JMenuItem OPENFILE = new JMenuItem("Open", new ImageIcon("open.jpg"));
	JMenuItem SAVEFILE = new JMenuItem("Save", new ImageIcon("save.jpg"));
	JMenuItem SAVEASFILE = new JMenuItem("Save As...");
	JMenuItem PRINTFILE = new JMenuItem("Print...");
	JMenuItem EXITFILE = new JMenuItem("Exit");
	
	JMenuItem UNDOEDIT = new JMenuItem("Undo");
	JMenuItem REDOEDIT = new JMenuItem("Redo");
	JMenuItem COPYEDIT = new JMenuItem("copy");
	JMenuItem CUTEDIT = new JMenuItem("cut");
	JMenuItem PASTEDIT = new JMenuItem("paste");
	JMenuItem DELETEDIT = new JMenuItem("Delete");
	JMenuItem FINDEDIT = new JMenuItem("Find");
	JMenuItem FINDNEXTEDIT = new JMenuItem("Find Next");
	JMenuItem REPLACEDIT = new JMenuItem("Replace");
	JMenuItem GOTOEDIT = new JMenuItem("Go To");
	JMenuItem SELECTEDIT = new JMenuItem("Select All");
	JMenuItem TIMEDIT = new JMenuItem("Time/Date");
	
	JCheckBoxMenuItem WORDFORMAT = new JCheckBoxMenuItem("Word Wrap");
	JMenuItem FONT = new JMenuItem("Font");
	
	JCheckBoxMenuItem STATUSVIEW = new JCheckBoxMenuItem("Status Bar");
	
	//JMenuItem ABOUT = new JMenuItem("About");
	
	String file = null;
	String fileN;
	
	boolean opened = false;
	
	JPanel statusPanel = new JPanel();
	
	JLabel statusLabel;
	
	JPanel aboutPanel = new JPanel();
	
	int ind = 0;
	
	StringBuffer sbufer;
	String findString;
	
	fontSelector fontS = new fontSelector();
	
	UndoManager undo = new UndoManager();
	UndoAction undoAction = new UndoAction();
	RedoAction redoAction = new RedoAction();
	
	public Note()
	{
		
		super("untitled");
		
		
		this.setSize(800,600);
		
		
		this.getContentPane().setLayout(new BorderLayout());
		
		
		area.setLineWrap(true);
		
		
		area.requestFocus(true);
		
	
		this.getContentPane().add(scroller, BorderLayout.CENTER);
		
		
		this.getContentPane().add(statusPanel, BorderLayout.SOUTH);
		
		
		area.setDragEnabled(true);
		
				
		toolBar.setFloatable(false); 
		
	
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		
		MouseListener popupListener = new PopupListener();
		area.addMouseListener(popupListener);
		
		
		area.getDocument().addUndoableEditListener(new MyUndoableEditListener());
		
	
		
		FILE.add(NEWFILE);
		FILE.add(OPENFILE);
		FILE.add(SAVEFILE);
		FILE.add(SAVEASFILE);
		FILE.addSeparator();
		FILE.add(PRINTFILE);
		FILE.addSeparator();
		FILE.add(EXITFILE);
		
		EDIT.add(undoAction);
		EDIT.add(redoAction);
		EDIT.add(CUTEDIT);
		EDIT.add(COPYEDIT);
		EDIT.add(PASTEDIT);
		EDIT.add(DELETEDIT);
		EDIT.addSeparator();
		EDIT.add(FINDEDIT);
		EDIT.add(FINDNEXTEDIT);
		EDIT.add(REPLACEDIT);
		EDIT.add(GOTOEDIT);
		EDIT.addSeparator();
		EDIT.add(SELECTEDIT);
		EDIT.add(TIMEDIT);
		
		FORMAT.add(WORDFORMAT);
		WORDFORMAT.setSelected(true);
		FORMAT.add(FONT);
		
		VIEW.add(STATUSVIEW);
		STATUSVIEW.setSelected(true);
		
//		HELP.add(ABOUT);
				
		FILE.setMnemonic(KeyEvent.VK_F);
		menuBar.add(FILE);
		EDIT.setMnemonic(KeyEvent.VK_E);
		menuBar.add(EDIT);
		FORMAT.setMnemonic(KeyEvent.VK_T);
		menuBar.add(FORMAT);
		VIEW.setMnemonic(KeyEvent.VK_V);
		menuBar.add(VIEW);

		
		
		this.setJMenuBar(menuBar);
		
		
		pop.add(undoAction);
		pop.add(redoAction);
		pop.addSeparator();
		pop.add(menuItem);
		pop.add(menucut);
		pop.add(menupaste);
		pop.add(menuclear);
		
		
		newFile.setBorder(null);
		openFile.setBorder(null);
		saveFile.setBorder(null);
		cutFile.setBorder(null);
		copyFile.setBorder(null);
		pasteFile.setBorder(null);
		
		
		toolBar.add(newFile);
		newFile.setToolTipText("New file");
		toolBar.addSeparator();
		toolBar.add(openFile); 
		openFile.setToolTipText("Open file");
		toolBar.add(saveFile);
		saveFile.setToolTipText("Save file");
		toolBar.addSeparator();
		toolBar.add(cutFile);
		cutFile.setToolTipText("Cut");
		toolBar.add(copyFile);
		copyFile.setToolTipText("Copy");
		toolBar.add(pasteFile);
		pasteFile.setToolTipText("Paste");
		
		
		setLocation((screen.width-1270)/2,((screen.height-740)/2));
		newFile.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				opened = false;
				if(area.getText().equals(""))
				{
					System.out.print("text is empty");
				}
				else
				{
					int confirm = JOptionPane.showConfirmDialog(null,
					"Would you like to save?",
					"New File",
					JOptionPane.YES_NO_CANCEL_OPTION);
	
	 				if( confirm == JOptionPane.YES_OPTION )
	 				{
	 					saveFile();
						area.setText(null);
						statusPanel.removeAll();
						statusPanel.validate();
					}
					else
	 				if( confirm == JOptionPane.CANCEL_OPTION )
	 				{}
					else if( confirm == JOptionPane.NO_OPTION )
					{
						area.setText(null);
						statusPanel.removeAll();
						statusPanel.validate();
					}
				}
			}
		});
		
		
		openFile.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				openFile();
			}
		});
		
		
		saveFile.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				saveFile();
			}
		});
		
		
		cutFile.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.cut();
			}
		});
		
		
		copyFile.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.copy();
			}
		});
		
	
		pasteFile.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.paste();
			}
		});
		
		
		
		menuItem.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.copy();
				menupaste.setEnabled(true);
				pasteFile.setEnabled(true);
				PASTEDIT.setEnabled(true);
			}
		});
		
	
		menucut.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.cut();
				menupaste.setEnabled(true);
				pasteFile.setEnabled(true);
				PASTEDIT.setEnabled(true);
			}
		});
		
		
		menupaste.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.paste();
			}
		});
		
		
		menuclear.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.setText(null);
			}
		});
		
		
		NEWFILE.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		NEWFILE.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				opened = false;
				int confirm = JOptionPane.showConfirmDialog(null,
				"Would you like to save?",
				"New File",
				JOptionPane.YES_NO_CANCEL_OPTION);

 				if( confirm == JOptionPane.YES_OPTION )
 				{
 					saveFile();
					area.setText(null);
					statusPanel.removeAll();
					statusPanel.validate();
				}
				else
 				if( confirm == JOptionPane.CANCEL_OPTION )
 				{}
				else
				{
					area.setText(null);
					statusPanel.removeAll();
					statusPanel.validate();
				}
			}
		});
		
		
		SAVEFILE.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		SAVEFILE.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				saveFile();
			}
		});
		
		
		SAVEASFILE.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				opened = false;
				saveFile();
			}
		});
		SELECTEDIT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		SELECTEDIT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.selectAll();
			}
		});
		
		
		PRINTFILE.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		PRINTFILE.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(area.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Text Area is empty.");
				else
				print(createBuffer());
				
			}
		});
		
		
		STATUSVIEW.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(STATUSVIEW.isSelected())
				statusPanel.setVisible(true);
				else
				statusPanel.setVisible(false);
			}
		});
		
	
		OPENFILE.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		OPENFILE.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				openFile();
			}
		});
		
		
		CUTEDIT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		CUTEDIT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.cut();
			}
		});
		
		
		COPYEDIT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		COPYEDIT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.copy();
			}
		});
		
		
		PASTEDIT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		PASTEDIT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.paste();
			}
		});
		
	
		FONT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		FONT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				fontS.setVisible(true);
				fontS.okButton.addActionListener(new ActionListener ()
					{
						public void actionPerformed(ActionEvent ae)
						{
							Font selectedFont = fontS.returnFont();
							area.setFont(selectedFont);
							fontS.setVisible(false);
						}
					});
					
				fontS.cancelButton.addActionListener(new ActionListener ()
					{
						public void actionPerformed(ActionEvent ae)
						{
							fontS.setVisible(false);
						}
					});
			}
		});
		
		
		TIMEDIT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Date currentDate;
				SimpleDateFormat formatter;
				String dd;
				formatter = new SimpleDateFormat("KK:mm aa MMMMMMMMM dd yyyy", Locale.getDefault());
				currentDate = new java.util.Date();
				dd = formatter.format(currentDate);
				area.insert(dd,area.getCaretPosition());
			}
		});
		
		
		FINDEDIT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					sbufer = new StringBuffer(area.getText());
					findString = JOptionPane.showInputDialog(null, "Find");
					ind = sbufer.indexOf(findString);
					area.setCaretPosition(ind);
					area.setSelectionStart(ind);
					area.setSelectionEnd(ind+findString.length());
				}
				catch(IllegalArgumentException npe)
				{
					JOptionPane.showMessageDialog(null, "String not found");
				}catch(NullPointerException nfe){}
			}
		});
		
		
		FINDNEXTEDIT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					sbufer = new StringBuffer(area.getText());
					findString = JOptionPane.showInputDialog(null, "Find");
					ind = sbufer.indexOf(findString, area.getCaretPosition());
					area.setCaretPosition(ind);
					area.setSelectionStart(ind);
					area.setSelectionEnd(ind+findString.length());
				}
				catch(IllegalArgumentException npe)
				{
					JOptionPane.showMessageDialog(null, "String not found");
				}catch(NullPointerException nfe){}
			}
		});
		
		
		EXITFILE.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		EXITFILE.addActionListener(new ActionListener()
		{
            public void actionPerformed(ActionEvent e)
            {
            	int confirm = JOptionPane.showConfirmDialog(null,
				"Would you like to save?",
				"Exit Application",
				JOptionPane.YES_NO_CANCEL_OPTION);

 				if( confirm == JOptionPane.YES_OPTION )
 				{
 					saveFile();
 					dispose();
 					//System.exit(0);
 				}
 				else
 				if( confirm == JOptionPane.CANCEL_OPTION )
 				{}
 				else
 				{
 					dispose();
 					//System.exit(0);
 				}
            }
        });
		
		
		
		REPLACEDIT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					String replace = JOptionPane.showInputDialog(null, "Replace");
					area.replaceSelection(replace);
				}catch(NumberFormatException nfe){}
			}
		});
		
	
		GOTOEDIT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{	
					Element root = area.getDocument().getDefaultRootElement();
					Element paragraph=root.getElement(Integer.parseInt(JOptionPane.showInputDialog(null, "Go to line")));
					area.setCaretPosition(paragraph.getStartOffset()-1);
				}catch(NullPointerException npe)
				{
					JOptionPane.showMessageDialog(null, "Invalid line number");
				}catch(NumberFormatException nfe)
				{
					
				}
			}
		});
		
		
		DELETEDIT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.replaceSelection(null);
			}
		});
		
		
		WORDFORMAT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(WORDFORMAT.isSelected())
				area.setLineWrap(true);
				else
				area.setLineWrap(false);
			}
		});
				
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null,
				"Would you like to save?",
				"Exit Application",
				JOptionPane.YES_NO_CANCEL_OPTION);

 				if( confirm == JOptionPane.YES_OPTION )
 				{
 					saveFile();
 					dispose();
 					//System.exit(0);
 				}
 				else
 				if( confirm == JOptionPane.CANCEL_OPTION )
 				{
 					
 				}
 				else
 				{
 					dispose();
 					//System.exit(0);
 				}
            }
        });
						
	}
	

	public static void main(String args[])
	{
		Note l = new Note();
		l.setVisible(true);
	}
	
	
	public void saveFile()
	{
		String line = area.getText();
				if(opened==true)
				{
					try
					{
						FileWriter output = new FileWriter(file);
						BufferedWriter bufout = new BufferedWriter(output);
						bufout.write(line, 0, line.length());
						JOptionPane.showMessageDialog(null, "Save Successful");
						bufout.close();
						output.close();
					}catch(IOException ioe){ioe.printStackTrace();}
				}
				else
				{
					JFileChooser fc = new JFileChooser();
					int result = fc.showSaveDialog(new JPanel());
					
					if(result == JFileChooser.APPROVE_OPTION) 
					{
				       fileN = String.valueOf(fc.getSelectedFile());
				    
						try
						{
							FileWriter output = new FileWriter(fileN);
							BufferedWriter bufout = new BufferedWriter(output);
							bufout.write(line, 0, line.length());
							JOptionPane.showMessageDialog(null, "Save Successful");
							bufout.close();
							output.close();
							opened = true;
						}catch(IOException ioe){ioe.printStackTrace();}
					}
				}
	}
	

	public String createBuffer()
	{
		String buffer;
		buffer = area.getText();
		return buffer;
	}

    private void print(String s)
	{
		StringReader sr = new StringReader(s);
		LineNumberReader lnr = new LineNumberReader(sr);
		Font typeface = new Font("Monospaced", Font.PLAIN, 12);
		Properties p = new Properties();
		PrintJob pjob = getToolkit().getPrintJob(this, "Print report", p);

		if (pjob != null) {
			Graphics pg = pjob.getGraphics();
			if (pg != null) {
				FontMetrics fm = pg.getFontMetrics(typeface);
				int margin = 20;
				int pageHeight = pjob.getPageDimension().height - margin;
    			int fontHeight = fm.getHeight();
    			int fontDescent = fm.getDescent();
    			int curHeight = margin;

				String nextLine;
				pg.setFont (area.getFont());

				try
				{
					do
					{
						nextLine = lnr.readLine();
						if (nextLine != null) {
							if ((curHeight + fontHeight) > pageHeight)
							{ 
								pg.dispose();
								pg = pjob.getGraphics();
								curHeight = margin;
							}

							curHeight += fontHeight;

							if (pg != null)
							{
								pg.setFont (typeface);
								pg.drawString (nextLine, margin, curHeight - fontDescent);
							}
						}
					}
					while (nextLine != null);

				}
				catch (EOFException eof)
				{
				}
				catch (Throwable t)
				{
					t.printStackTrace();
				}
			}
			pg.dispose();
		}
		if (pjob != null)
			pjob.end();
	}
	
	
	public void openFile()
	{
				statusPanel.removeAll();
				statusPanel.validate();
				area.setText(null);
				JFileChooser fc = new JFileChooser();
				int result = fc.showOpenDialog(new JPanel());
				
				if(result == JFileChooser.APPROVE_OPTION) 
				{
					String file = String.valueOf(fc.getSelectedFile());
					
					
					File fil = new File(file);
					newFile.setEnabled(false);
	
					
					Thread loader = new FileLoader(fil, area.getDocument());
					loader.start();
					statusPanel.removeAll();
					statusPanel.revalidate();
				}else{}
	}
	


class PopupListener extends MouseAdapter {
   
    public void mousePressed(MouseEvent e) {
    	    	
    	maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            pop.show(e.getComponent(),
                       e.getX(), e.getY());
        }
    }
}


public class MyUndoableEditListener
          implements UndoableEditListener
{
    public void undoableEditHappened(UndoableEditEvent e) {
       
        undo.addEdit(e.getEdit());
        undoAction.update();
        redoAction.update();
    }
}  

class FileLoader extends Thread {
	
	JLabel state;

	FileLoader(File f, Document doc) {
	    setPriority(4);
	    this.f = f;
	    this.doc = doc;
	}

        public void run() {
	    try {
		
		statusPanel.removeAll();
		JProgressBar progress = new JProgressBar();
		progress.setMinimum(0);
		progress.setMaximum((int) f.length());
		statusPanel.add(new JLabel("opened so far "));
		statusPanel.add(progress);
		statusPanel.revalidate();

		
		Reader in = new FileReader(f);
		char[] buff = new char[4096];
		int nch;
	    while ((nch = in.read(buff, 0, buff.length)) != -1) {
		    doc.insertString(doc.getLength(), new String(buff, 0, nch), null);
		    progress.setValue(progress.getValue() + nch);
		}

	
		statusPanel.removeAll();
		statusPanel.revalidate();

	    }
	    catch (IOException e) {
		System.err.println(e.toString());
	    }
	    catch (BadLocationException e) {
		System.err.println(e.getMessage());
	    }
		newFile.setEnabled(true);
	}
	Document doc;
	File f;
}



class UndoAction extends AbstractAction {
	public UndoAction() {
	    super("Undo");
	    setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
	    try {
		undo.undo();
	    } catch (CannotUndoException ex) {
		System.out.println("Unable to undo: " + ex);
		ex.printStackTrace();
	    }
	    update();
	    redoAction.update();
	}

	protected void update() {
	    if(undo.canUndo()) {
		setEnabled(true);
		putValue("Undo", undo.getUndoPresentationName());
	    }
	    else {
		setEnabled(false);
		putValue(Action.NAME, "Undo");
	    }
	}
    }

class RedoAction extends AbstractAction {
	public RedoAction() {
	    super("Redo");
	    setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
	    try {
		undo.redo();
	    } catch (CannotRedoException ex) {
		System.out.println("Unable to redo: " + ex);
		ex.printStackTrace();
	    }
	    update();
	    undoAction.update();
	}

	protected void update() {
	    if(undo.canRedo()) {
		setEnabled(true);
		putValue("Redo", undo.getRedoPresentationName());
	    }
	    else {
		setEnabled(false);
		putValue(Action.NAME, "Redo");
	    }
	}
    }
    

//END MAIN CLASS
}
