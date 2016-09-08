
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.awt.datatransfer.StringSelection.*;
import java.io.*;
import javax.swing.table.*;
import java.util.*;
import javax.swing.plaf.metal.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

			
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JFrame;


class MainWindow extends JFrame implements WindowListener,ActionListener, ItemListener 
{	
	Buses bus;
	Employees emp;
	Routes rout;
	Passengers pass;
	Calculator calc;
	Note note;
	AddNewPassenger addpass;
	AddNewBus addbus;
	AddNewRoute addroute;
	Schedule sched;
	AddNewEmployee addemp;
	Payment pay;
	Booking book;
	About about;
	Help help;
	Imap imap;
	Wmap wmap;
	Mmap mmap;
	Searchb searchb;
	Searchp searchp;
	Searche searche; 
	SimpleAnalogClock clock;
	Employee_Details employee_Details ;
	Pass_Details pass_Details ;
	Bus_Details bus_Details;
	//MetalThemeMenu DefaultMetalTheme;
	GrayTheme GrayTheme;
	SolidTheme SolidTheme;
	AquaTheme AquaTheme;
	SandTheme SandTheme;
	GreenTheme GreenTheme;
	
    //For Program's ToolBar.

	private	JToolBar toolBar;

	//for button

	JButton  b_apas,b_abus,b_cal,b_note,b_pay,b_book,b_help,b_exit;
	//-------------------------------------------------------------------------
	JInternalFrame		propertiesFrame, dateFrame, DesignFrame,toolbarFrame;
	JSplitPane			properties,divider;
	JLayeredPane		errorlist;
	//-------------------------------------------------------------------------
	JMenuBar			mb;
	JMenu					file,view, process, tools,search,map,m1help,mclock,mdetails,mnuOpt,mtheme;
	JMenuItem			mBuses, mEmployees, mRoutes, mExit ,mPassengers ,mBooking,
	                    mScheduling,mPayment,mCalculator,mNote,addRoute,addEmp,addBus,addPass,
	                   psearch,esearch,bsearch,mimap,mmmap,mhelp,mabout1,mwmap,mclock1,mpass_Details
						,memp_Details,mbus_Details,msimpleanalogclock,mgreytheme,maquatheme,mgreentheme,msolidtheme,msandtheme;

	JMenuItem change, style, theme;	//Option Menu Options.
	//-------------------------------------------------------------------------
	BevelBorder	bevel;


	private String strings[] = {"1. Metal", "2. Motif", "3. Windows"};
	private UIManager.LookAndFeelInfo looks[] = UIManager.getInstalledLookAndFeels ();
	private ButtonGroup group = new ButtonGroup ();
	private JRadioButtonMenuItem radio[] = new JRadioButtonMenuItem[strings.length];

	
	JTabbedPane		jtp;
	JScrollPane jspt;
	JScrollPane jspt1;
	JButton cmd_date; JLabel ldate,lbl_image,lbl_hold;
	JToolBar				toolbar;
	JTable					tbAppearance, tbDesign ,tbBehaviour;
	JTabbedPane		tab,tooltab;
	JTable					tbl;
	JLabel wait,adv;
	Calendar cal;
	private DateButton s_date;
	boolean lock = false;

	public MainWindow()
	{
		setLayout(null);
		setTitle("Travels Booking System");
		setDefaultLookAndFeelDecorated(true);
		//ImageIcon ii;
		//ii = new ImageIcon("INF\\1.jpg");
		//wait = new JLabel(ii);
		//add(wait);
		//wait.setBounds(220, 180, 600, 300);
		//adv = new JLabel("<html><marquee direction = Left><center><font color=blue >Welcome to Travels Booking System</font></center></marquee></html>");
		//add(adv);
		//adv.setBounds(0, 10, 1600, 300);	



       //Creating the ToolBar's Buttons of Program.
		b_apas = new JButton ("aaa",new ImageIcon ("Images/NotePad.gif"));
		b_apas.setToolTipText ("Add New Passanger");
		b_apas.addActionListener (this);
		b_abus = new JButton ("aaa",new ImageIcon ("Images/ImationDisk.gif"));
		b_abus.setToolTipText ("Add New Bus");
		b_abus.addActionListener (this);
		b_cal = new JButton (new ImageIcon ("Images/SuperDisk.gif"));
		b_cal.setToolTipText ("Calculator");
		b_cal.addActionListener (this);
		b_note = new JButton (new ImageIcon ("Images/Paproll.gif"));
		b_note.setToolTipText ("Notepad");
		b_note.addActionListener (this);
		b_pay = new JButton (new ImageIcon ("inf//PAPROLL.gif"));
		b_pay.setToolTipText ("Payment");
		b_pay.addActionListener (this);
		b_book = new JButton (new ImageIcon ("Images/Search.gif"));
		b_book.setToolTipText ("Booking");
		b_book.addActionListener (this);
		b_help = new JButton (new ImageIcon ("INF//Help.gif"));
		b_help.setToolTipText ("Help of Software");
		b_help.addActionListener (this);
		b_exit = new JButton (new ImageIcon ("INF//exit.PNG"));
		b_exit.setToolTipText ("Exit All");
		b_exit.addActionListener (this);

		//toolBar.add (b_apas);
	
	    add (b_apas);
		b_abus.setBounds(10,00,100,25);
	    add (b_abus);
		b_apas.setBounds(115,00,100,25);
		add (b_cal);
		b_cal.setBounds(220,00,100,25);
		add (b_note);
		b_note.setBounds(325,00,100,25);
		add (b_book);
		b_book.setBounds(430,00,100,25);
		add (b_pay);
		b_pay.setBounds(535,00,100,25);
		add (b_help);
		b_help.setBounds(640,00,100,25);
		add (b_exit);
		b_exit.setBounds(745,00,100,25);


		setBounds(0,0,1280,800);
		setIconImage(new ImageIcon("INF/Icon.png").getImage());

//---------------------------Background---------------------------------------------

		addWindowListener(this);
		lbl_image = new JLabel();
		lbl_image.setBounds(1,25,8000,800);
		add(lbl_image);
		ImageIcon bk = new ImageIcon("BUS image/mbus.jpg");
		lbl_image.setIcon(bk);
//--------------------------------------------------------------------------------------
		lbl_hold = new JLabel();
		lbl_hold.setBounds(100, 40, 400, 400);
		add(lbl_hold);
		ImageIcon bk1 = new ImageIcon("INF/hold.png");
		lbl_hold.setIcon(bk1);
		//--------------------------------------------------------------------------------------------
		mb = new JMenuBar();
		setJMenuBar(mb);
		file = new JMenu("      File      ");
		mb.add(file);
		mBuses = new JMenuItem("  Buses   ");
		file.add(mBuses);
		mBuses.setMnemonic('B');    
		mBuses.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_B,ActionEvent.CTRL_MASK
					)
				);
	mBuses.setActionCommand("Buses");
	mBuses.addActionListener(JMenuActionListener);
		//----------------------------------------------------------------------------------------------
		file.addSeparator();
		mEmployees = new JMenuItem(" Employees ");
		file.add(mEmployees);
		mEmployees.setMnemonic('E');
		mEmployees.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_E, ActionEvent.CTRL_MASK
					)
				);
		mEmployees.setActionCommand("Employees");
		mEmployees.addActionListener(JMenuActionListener); 
		//--------------------------------------------------------------------------------------------------
		file.addSeparator();
		mRoutes = new JMenuItem(" Routes ");
		file.add(mRoutes);
		mRoutes.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_R, ActionEvent.CTRL_MASK
					)
				);
		mRoutes.setActionCommand("Routes");
		mRoutes.addActionListener(JMenuActionListener);
		//--------------------------------------------------------------------------------------------------
		file.addSeparator();
		mPassengers = new JMenuItem(" Passengers ");
		file.add(mPassengers);
		mPassengers.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_P, ActionEvent.CTRL_MASK
					)
				);
		mPassengers.setActionCommand("Passengers");
		mPassengers.addActionListener(JMenuActionListener); 
		file.addSeparator(); 
		file.addSeparator();
		//--------------------------------------------------------------------------------------------------
		mExit= new JMenuItem(" Exit ");
		file.add(mExit);
		mExit.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_X, ActionEvent.CTRL_MASK
					)
				);
		mExit.setActionCommand("Exit");
		mExit.addActionListener(JMenuActionListener);
		//--------------------------------------Tools--------------------------------------------------------------

                 JMenu tools = new JMenu("    Tools      ");
	mb.add(tools);
	mCalculator = new JMenuItem("  Calculator   ");
		tools.add(mCalculator);
		mCalculator.setMnemonic('C');    
mCalculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK
					)
				);
	mCalculator.setActionCommand("Calculator");
	mCalculator.addActionListener(JMenuActionListener);
		tools.addSeparator();
		//file.addSeparator();
			//-----------------------------------------------------------------------
		
		mNote = new JMenuItem(" Note ");
		tools.add(mNote);
		mNote.setMnemonic('N');
		mNote.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_N, ActionEvent.CTRL_MASK
					)
				);
		mNote.setActionCommand("Note");
		mNote.addActionListener(JMenuActionListener); 

//-------------------------------------------------------------------------------------
		mExit.setIcon(new ImageIcon("INF/exit.png"));

		view = new JMenu("    View      ");
		mb.add(view);
		mBooking = new JMenuItem(" Booking "); view.add(mBooking);view.addSeparator();
		mBooking.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_G, ActionEvent.CTRL_MASK
					)
				);
		mBooking.setActionCommand("Booking");
		mBooking.addActionListener(JMenuActionListener);


		/*mScheduling = new JMenuItem(" Scheduling "); view.add(mScheduling); view.addSeparator();
		mScheduling.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_T, ActionEvent.CTRL_MASK
					)
				);
		mScheduling.setActionCommand("Schedule");
		mScheduling.addActionListener(JMenuActionListener);
		*/

		mPayment = new JMenuItem(" Payment "); view.add(mPayment);
		mPayment.setMnemonic('C');
		mPayment.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_C, ActionEvent.CTRL_MASK
					)
				);
		mPayment.setActionCommand("Payment");
		mPayment.addActionListener(JMenuActionListener);
		//----------------------------------------------------------------				
		process = new JMenu(" Process     ");
		mb.add(process);
		//----------------------------------------------------------------
		addPass = new JMenuItem(" Add Passenger ");
		process.add(addPass);
		addPass.setMnemonic('O');
		addPass.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_O, ActionEvent.CTRL_MASK
					)
				);
		addPass.setActionCommand("AddPass");
		addPass.addActionListener(JMenuActionListener);
		//----------------------------------------------------------------

		addBus = new JMenuItem(" Add Bus ");
		process.add(addBus);
		addBus.setMnemonic('K');
		addBus.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_K, ActionEvent.CTRL_MASK
					)
				);
		addBus.setActionCommand("AddBus");
		addBus.addActionListener(JMenuActionListener);
		//------------------------------------------------------------------		
		addRoute = new JMenuItem(" Add Route ");
		process.add(addRoute);
		addRoute.setMnemonic('E');
		addRoute.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_L, ActionEvent.CTRL_MASK
					)
				);
		addRoute.setActionCommand("AddRoute");
		addRoute.addActionListener(JMenuActionListener);
		//------------------------------------------------------------------
		addEmp = new JMenuItem(" Add Employee ");
		process.add(addEmp);
		addEmp.setMnemonic('M');
		addEmp.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_M, ActionEvent.CTRL_MASK
					)
				);
		addEmp.setActionCommand("AddEmp");
		addEmp.addActionListener(JMenuActionListener); 
		//----------------------------------------------------------------
		s_date = new DateButton();
		toolbar = new JToolBar("Formatting");
		add(toolbar, BorderLayout.NORTH);
		toolbar.setBounds(0, 5,6040, 20);
		for (int i = 0; i < 88; i++) { toolbar.addSeparator(); }
		ldate = new JLabel("Current Date :"); toolbar.add(ldate);	
		//for (int i = 0; i < 80; i++)
		toolbar.addSeparator();
			toolbar.add(s_date);		
		toolbar.setBorder(bevel);
		toolbar.setRollover(true);	
		//-------------------------------------------------------------------
		search = new JMenu("    Search    ");
		mb.add(search);
		
		
		
		psearch = new JMenuItem("Passanger search ");
		search.add(psearch);
		psearch.setActionCommand("Searchp");
		psearch.addActionListener(JMenuActionListener); 
		
		
		search.addSeparator();
		
		
		esearch = new JMenuItem("Employee search ");
		search.add(esearch);
	
		
		esearch.setActionCommand("Searche");
		esearch.addActionListener(JMenuActionListener); 
	
	    search.addSeparator();
		
		bsearch = new JMenuItem("Bus search ");
		search.add(bsearch);

		bsearch.setActionCommand("Searchb");
		bsearch.addActionListener(JMenuActionListener); 
		
		//-------------------------------------------------------------------
		map = new JMenu("      Map    ");
		mb.add(map);
	
		mmmap = new JMenuItem("Maharastra Map");
		map.add(mmmap);
	
		mmmap.setActionCommand("Mmap");
		mmmap.addActionListener(JMenuActionListener); 
		
		map.addSeparator();
		
		mimap = new JMenuItem("India Map");
		map.add(mimap);
		map.addSeparator();
		
		mimap.setActionCommand("Imap");
		mimap.addActionListener(JMenuActionListener); 	
		
		mwmap = new JMenuItem("World map" );
		map.add(mwmap);

		mwmap.setActionCommand("Wmap");
		mwmap.addActionListener(JMenuActionListener); 	

		//-------------------------------------------------------------------

		
		mnuOpt = new JMenu ("   LookAndFeel  ");
		mb.add (mnuOpt);

		//MenuItems for OptionMenu.

		//Menu For Changing the Program's Layout.
		style = new JMenu ("Change Layout Style");
		style.setMnemonic ((int)'L');
		for( int i = 0; i < radio.length ; i++ ) {			//Creating the subMenus of Style Menu.
			radio[i] = new JRadioButtonMenuItem (strings[i]);	//Build an Array of Layouts to Apply.
			radio[i].addItemListener (this);			//Setting their Actions.
			group.add (radio[i]);					//Making them Grouped.
			style.add (radio[i]);					//Adding to Style MenuOption.
		}

		//MetalTheme[];

	/*	//SubMenu of Theme For Applying different Themes to Program By Building an Array of Themes to Apply.
		MetalTheme[] themes = { new GreenTheme(), new AquaTheme(), new SandTheme(), new SolidTheme(), new MilkyTheme(), new GrayTheme() };
		theme = new MetalThemeMenu ("Apply Theme", themes);		//Putting the Themes in ThemeMenu.
		theme.setMnemonic ((int)'M');
*/
		JMenu mtheme=new JMenu("Change Theme");
		mnuOpt.add(mtheme);
		mnuOpt.addSeparator ();

		mnuOpt.addActionListener (this);

		 mgreytheme=new JMenuItem("Gray Theme");
		 mtheme.add(mgreytheme);
		 mgreytheme.addActionListener (this);
		 mtheme.addSeparator ();


		 mgreentheme=new JMenuItem("Green Theme");
		 mtheme.add(mgreentheme);
		 mtheme.addSeparator ();
		 mgreentheme.addActionListener (this);

		 msandtheme=new JMenuItem("Sand Theme");
		 mtheme.add(msandtheme);
		 msandtheme.addActionListener (this);
		 mtheme.addSeparator ();


		 msolidtheme=new JMenuItem("Solid Theme");
		 mtheme.add(msolidtheme);
		 msolidtheme.addActionListener (this);
		 mtheme.addSeparator ();


	     maquatheme=new JMenuItem("Aqua Theme");
		 mtheme.add(maquatheme);
		 maquatheme.addActionListener (this);

		
		//Options Menu Items.
		mnuOpt.add (style);
		
	//	mnuOpt.add (theme);
		
		//-------------------------------------------------------------------
		
		
         m1help = new JMenu("      Help   ");
		 mb.add(m1help);
         mhelp = new JMenuItem("Window Help ");
		 m1help.add(mhelp);
		 
		 mhelp.setActionCommand("Help");
		 mhelp.addActionListener(JMenuActionListener); 	
		 
		 m1help.addSeparator();
		 
		 mabout1 = new JMenuItem("About Software");
		 m1help.add(mabout1);
		 

		 mabout1.setActionCommand("About");
		 mabout1.addActionListener(JMenuActionListener); 	
		 
//---------------------------------------------------------------------------------------------------------
	     mdetails = new JMenu("     Details  ");
		mb.add(mdetails);
		

		mpass_Details = new JMenuItem("Passanger Details");
		mdetails.add(mpass_Details);

		mpass_Details.setActionCommand("Passanger Details");
		mpass_Details.addActionListener(JMenuActionListener);

		mdetails.addSeparator();
//------------------------------------------------------------
		memp_Details= new JMenuItem("Employee Details");
		mdetails.add(memp_Details);

		memp_Details.setActionCommand("Employee Details");
		memp_Details.addActionListener(JMenuActionListener); 

		mdetails.addSeparator();
//-------------------------------------------------------------------------------
		mbus_Details = new JMenuItem("Bus Details");
		mdetails.add(mbus_Details);

		mbus_Details.setActionCommand("Bus Details");
		mbus_Details.addActionListener(JMenuActionListener); 

//-------------------------------------------------------------------------------
		 mclock = new JMenu("    Clock    ");
	     mb.add(mclock);
		

		msimpleanalogclock = new JMenuItem("SimpleAnalogClock");
		mclock.add(msimpleanalogclock);

		msimpleanalogclock.setActionCommand("SimpleAnalogClock");
		//msimpleanalogclock.addActionListener(JMenuActionListener);

		//mdetails.addSeparator();

		setBounds(1, 2,1020, 735);
 
		mimap.addActionListener(this);
		mwmap.addActionListener(this);
		mmmap.addActionListener(this);
		mpass_Details.addActionListener(this);
		memp_Details.addActionListener(this);
	    mbus_Details.addActionListener(this);
		mgreytheme.addActionListener(this);
		mgreentheme.addActionListener(this);	
	    maquatheme.addActionListener(this);
		msolidtheme.addActionListener(this);	
	    msandtheme.addActionListener(this);

		setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
	
	   
		if(e.getSource()==mmmap)
		 {
		    
			new Mahamap();
		 
		 }

		 if(e.getSource()==mhelp)
		 {
		    
			new Help();
		 
		 }
		  if(e.getSource()==mabout1)
		 {
		    
			new About();
		 
		 }
		  if(e.getSource()==mpass_Details)
		 {
		    
			new Pass_Details();
		 
		 }
		  if(e.getSource()==memp_Details)
		 {
		    
			new Employee_Details();
		 
		 }
		  if(e.getSource()==mbus_Details)
		 {
		    
			new Bus_Details();
		 
		 }
		  if(e.getSource()==mgreytheme)
		 {
		    
			 new GrayTheme();
		 
		 }
		  if(e.getSource()==mgreentheme) 
		 {
		  
			  new GreenTheme();
			  
		 }
		  if(e.getSource()==msolidtheme)
		 {
		    
			 new SolidTheme();
		 
		 }
		  if(e.getSource()==maquatheme)
		 {
		    
			 new  AquaTheme();
		 
		 }
		  if(e.getSource()==b_apas)
		 {
		    
			 new AddNewPassenger();
		 
		 }
		   if(e.getSource()==b_abus)
		 {
		    
			 new AddNewBus();
		 
		 }
		   if(e.getSource()==b_cal)
		 {
		    
			 new Calculator();
		 
		 }
		   if(e.getSource()==b_note)
		 {
		    
			//new Note();
			 new Note().setVisible(true);
		 
		 }
		   if(e.getSource()==b_pay)
		 {
		    
			 new Payment();
		 
		 }
		   if(e.getSource()==b_book)
		 {
		    
			 new Booking();
		 
		 }
		   if(e.getSource()==b_help)
		 {
		    
			 new Help().setVisible(true);;
		 
		 }
		   if(e.getSource()==b_exit)
		 {
		    
			 System.exit(0);
		 
		 }



	
	}

	//Function Perform By LookAndFeel Menu.

	public void itemStateChanged (ItemEvent e) {

		for( int i = 0; i < radio.length; i++ )
			if(radio[i].isSelected()) {
				changeLookAndFeel (i);
			}

	}


	//Function for Changing the Program's Look.

	public void changeLookAndFeel (int val) {

		try {
			UIManager.setLookAndFeel (looks[val].getClassName());
			SwingUtilities.updateComponentTreeUI (this);
		}
		catch (Exception e) { }

	}

	
	public void show(String page)
	{
		//if (lock ==false)
		//{
			if (page.equalsIgnoreCase("Buses"))
			{ bus = new Buses(this); lock = true;}
			if (page.equalsIgnoreCase("Employees"))
			{emp = new Employees(this); lock = true;}
			if (page.equalsIgnoreCase("Routes"))
			{rout = new Routes(this);lock = true;}
			if (page.equalsIgnoreCase("Passengers"))
			{ pass = new Passengers(this); lock = true; }
			if (page.equalsIgnoreCase("AddBus"))
			{ addbus = new AddNewBus(); }
			if (page.equalsIgnoreCase("AddRoute"))
			{ addroute = new AddNewRoute(); }
			if (page.equalsIgnoreCase("AddEmp"))
			{ addemp = new AddNewEmployee(); }
			if (page.equalsIgnoreCase("AddPass"))
			{ addpass = new AddNewPassenger(); }
			if (page.equalsIgnoreCase("Schedule"))
			{ sched = new Schedule(); }
			if (page.equalsIgnoreCase("Payment"))
			{ pay = new Payment(); }
			if (page.equalsIgnoreCase("Booking"))
			{ book = new Booking(); }
			if(page.equalsIgnoreCase("Calculator"))
			{ calc= new Calculator();}
			if(page.equalsIgnoreCase("Note"))
			{
				note= new Note();
		    }
	       if(page.equalsIgnoreCase("About"))
	       {
		     about= new About();
           }
		    if(page.equalsIgnoreCase("Help"))
	       {
		     help= new Help();
           }

		   if(page.equalsIgnoreCase("Mmap"))
		   { mmap= new Mmap();}
		   if(page.equalsIgnoreCase("Imap"))
		   {
			imap= new Imap();
	       }

	       if(page.equalsIgnoreCase("Wmap"))
	      {
		    wmap= new Wmap();
           }
	    
		 if(page.equalsIgnoreCase("Searchp"))
	     {
	    	 searchp= new Searchp();
         }
		  if(page.equalsIgnoreCase("Searchb"))
          { 
        	 searchb= new Searchb();
    	  }
         if(page.equalsIgnoreCase("Searche"))
          { 
        	 searche= new Searche();
    	  }
		 
		/*else
		{
				JOptionPane.showMessageDialog(null, "Please Close The Current Form !", "Oop's !", JOptionPane.ERROR_MESSAGE); 
	    }*/
	}

	
	ActionListener JMenuActionListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			String srcObject = e.getActionCommand();
			if (srcObject == "Buses") { try { show("Buses"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "Employees") { try { show("Employees"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "Routes") { try { show("Routes"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "Passengers") { try { show("Passengers"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "Exit") { UnloadWindow(); }
			if (srcObject == "AddBus") { try { show("AddBus"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "AddRoute") { try { show("AddRoute"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "AddEmp") { try { show("AddEmp"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "AddPass") { try { show("AddPass"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "Schedule") { try { show("Schedule"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "Payment") { try { show("Payment"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "Booking") { try { show("Booking"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "About") { try { show("About"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "Help") { try { show("Help"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "Imap") { try { show("Imap"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "Mmap") { try { show("Mmap"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "Wmap") { try { show("Wmap"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "Searchp") { try { show("Searchp"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "Searchb") { try { show("Searchb"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "Searche") { try { show("Searche"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }
			if (srcObject == "SimpleAnalogClock") { try { show("SimpleAnalogClock"); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }


if (srcObject == "Calculator") { try { 
//runComponents("Calc.exe");
new Calculator().setVisible(true); } catch (Exception sqle) { JOptionPane.showMessageDialog(null, "Unable To Get Data !", "Sorry !", JOptionPane.ERROR_MESSAGE); } }


if(srcObject=="Note"){ 
				try{
				 //runComponents("Notepad.exe");
				 new Note().setVisible(true);
				}catch(Exception sqle){
				}
			}



		}
	};
		public static void  main(String args[])
		{

			MainWindow m=new MainWindow();
			m.setVisible(true);
			
		}
	public void windowOpened(WindowEvent e)
	{
	}
	public void windowClosing(WindowEvent e)
	{
		UnloadWindow();
	}
	public void windowClosed(WindowEvent e)
	{
	}
	public void windowIconified(WindowEvent e)
	{
	}
	public void windowDeiconified(WindowEvent e)
	{
	}
	public void windowActivated(WindowEvent e)
	{
	}
	public void windowDeactivated(WindowEvent e)
	{
	}
	protected void UnloadWindow()
	{
		String ObjButtons[] = { "Yes", "No" };
		int PromptResult = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?",
		"Travels Booking System", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);

		System.out.println("PromptResult: "+PromptResult);
		if (PromptResult==1)
		{
			new MainWindow();
		}
		else
		{
			System.out.println(" ------------------------------------------------------------\n" +
										  " ------------------------------------------------------------\n" +
										  "              *  *  *  *   Happy Journy	  *  *  *  * 	 \n"+
										  " ------------------------------------------------------------\n" +
										  " ------------------------------------------------------------\n");			
			System.exit(0);
		}
		
	}
}

	