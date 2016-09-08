import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.applet.Applet;
import javax.swing.*;
import java.lang.StringBuffer;
import java.io.IOException;
import java.io.*;
import java.sql.*;  
import javax.swing.plaf.metal.*; 
import java.text.*;
public class Schedule extends JFrame {

private JLabel BusNo,RegNo,RouteNo,RouteName,DriverNo,DriverName,
	           DeptTime,date,Trip;
private JComboBox cboBusNo,cboRouteNo,cboRouteName,cboDriverNo,
	           cboDriverName,cboRegNo,cboTrip;
private JTextField txtDepTime,txtdate;
private JButton Check,Schedule,Cancel,jButton4;
private DateButton s_date;
int Year;	
	String is;
Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel jPanel1;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JPanel jPanel5;
    private static JTextArea txtInfo=new JTextArea( 15, 40 );
	private Connection dbconn;
	private static String info;
	Connection con;
	public Schedule () {
        super("Scheduling Process");
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String database = "";
			database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
			database += "Travels.mdb" + ";DriverID=22;READONLY=true}";
			//now we 	can get connection from DriverManager
			con = DriverManager.getConnection(database, "", "");
		}
		catch (Exception e)
		{ System.out.println("Error in Con :" + e); }

		setLayout(null);

		BusNo = new JLabel("Bus Number ");
		RegNo = new JLabel("Reg Number ");
		RouteNo = new JLabel("Route Number");
		RouteName = new JLabel("Route Name ");
        DriverNo = new JLabel("Driver Number");
        DriverName = new JLabel("Driver Name");
        date=new JLabel ("Date Scheduled");
		Trip = new JLabel("Trip Number");
		DeptTime=new JLabel("Departure Time");
		
		cboBusNo = new JComboBox();
		cboRegNo   = new JComboBox();
		cboRouteNo = new JComboBox();
		cboRouteName = new JComboBox();
        cboDriverNo = new JComboBox();
        cboDriverName = new JComboBox();
        txtDepTime=new JTextField(10);
		cboTrip = new JComboBox();
		Check=new JButton("View Shedules",new ImageIcon("INF/select.png"));
		Schedule = new JButton("Schedule", new ImageIcon("INF/reset.png"));
		Schedule.setBounds(285, 260, 120, 25);
		add(Schedule);
//		Enable=new JButton ("Start",new ImageIcon("Icon/i16x16/contents.png"));
		Cancel = new JButton("Cancel", new ImageIcon("INF/exit.png"));
		Cancel.setBounds(420, 260, 100, 25);
		add(Cancel);

		jButton4 = new JButton("PRINT", new ImageIcon("INF/prints.png"));
		s_date=new DateButton();
		cboTrip.addItem("1");
		cboTrip.addItem("2");
		//Labels Settings
		BusNo.setFont(new Font("sansserif", Font.ITALIC, 14));
		RegNo.setFont(new Font("sansserif", Font.ITALIC, 14));
		RouteNo.setFont(new Font("sansserif", Font.ITALIC, 14));
		RouteName.setFont(new Font("sansserif", Font.ITALIC, 14));
		DriverNo .setFont(new Font("sansserif", Font.ITALIC, 14));
		date.setFont(new Font("sansserif", Font.ITALIC, 14));
		Trip.setFont(new Font("sansserif", Font.ITALIC, 14));
		DeptTime.setFont(new Font("sansserif", Font.ITALIC, 14));
		DriverName.setFont(new Font("sansserif", Font.ITALIC, 14));
		DriverName.setForeground(Color.blue);
		DeptTime.setForeground(Color.blue);
		BusNo.setForeground(Color.blue);
		RegNo.setForeground(Color.blue);
		RouteNo.setForeground(Color.blue);
		RouteName.setForeground(Color.blue);
		DriverNo.setForeground(Color.blue);
		DriverName.setForeground(Color.blue);
		date.setForeground(Color.blue);
		Trip.setForeground(Color.blue);
		
		jPanel1 = new JPanel(new java.awt.GridLayout(9,2));	////jPanel1.
		BusNo.setBounds(100,5,100,20);
		add(BusNo);	//jPanel1.
		cboBusNo.setBounds(250, 5, 150, 25);
		add(cboBusNo);	//jPanel1.
		//--------------------------------------------------------------
		RegNo.setBounds(100, 30, 100, 20);
		add(RegNo);	//jPanel1.
		cboRegNo.setBounds(250, 30, 150, 25);
		add(cboRegNo);	//jPanel1.
		//--------------------------------------------------------------
		RouteNo.setBounds(100, 55, 150, 20);
		add(RouteNo);		//jPanel1.
		cboRouteNo.setBounds(250, 55, 150, 25);
		add(cboRouteNo);	//jPanel1.
		//--------------------------------------------------------------
		RouteName.setBounds(100, 80, 150, 20);
		add(RouteName);//jPanel1.
		cboRouteName.setBounds(250, 80, 150, 25);
		add(cboRouteName);
		//--------------------------------------------------------------
		//jPanel1.
		add(DriverNo);//jPanel1.
		DriverNo.setBounds(100, 105, 150, 20);
		add(cboDriverNo);
		cboDriverNo.setBounds(250, 105, 150, 25);
		//jPanel1.
		//--------------------------------------------------------------
		add(DriverName);//jPanel1.
		DriverName.setBounds(100, 130, 150, 20);
		add(cboDriverName);
		cboDriverName.setBounds(250, 130, 150, 25);
		//--------------------------------------------------------------
		//jPanel1.
		add(DeptTime);//jPanel1.
		DeptTime.setBounds(100, 155, 150, 20);
		add(txtDepTime);
		txtDepTime.setBounds(250, 155, 150, 25);
		//--------------------------------------------------------------
		//jPanel1.
		add(date);//jPanel1.
		date.setBounds(100, 180, 150, 20);
		add(s_date);
		s_date.setBounds(250, 180, 150, 25);
		//jPanel1.
		add(Trip);//jPanel1.
		Trip.setBounds(100, 205, 150, 20);
		add(cboTrip);
		cboTrip.setBounds(250, 205, 150, 25);
        
        cboRouteNo.addItem("Select");
        cboBusNo.addItem("Select");
        cboRouteName.addItem("Select");
        cboDriverNo.addItem("Select");
	    cboDriverName.addItem("Select");               
	    cboRegNo.addItem("Select");               
		//Labels Settings
		
		jPanel3 = new javax.swing.JPanel(new java.awt.FlowLayout());

		jPanel3.add(jPanel1);
	    
		jPanel4 = new javax.swing.JPanel(new java.awt.FlowLayout());
//		jPanel4.add(Enable);
		//jPanel4.add(Check);
		//jPanel4.add(Schedule);
		//jPanel4.add(Cancel);
		//jPanel4.add(jButton4);
		//add(jPanel3);

		Check.setBounds(130,260,140,25);
		add(Check);
		Check.setVisible(true);
		//Schedule = new JButton("Schedule", new ImageIcon("INF/reset.png"));
		////		Enable=new JButton ("Start",new ImageIcon("Icon/i16x16/contents.png"));
		//Cancel = new JButton("Cancel", new ImageIcon("INF/exit.png"));
		//jButton4 = new JButton("PRINT", new ImageIcon("INF/prints.png"));


		//jPanel5 = new javax.swing.JPanel(new java.awt.BorderLayout());
		//jPanel5.add(jPanel3, BorderLayout.CENTER);
		//jPanel5.add(jPanel4, BorderLayout.SOUTH);
		//add(jPanel5);
		//jPanel5.setVisible(true);
		//jPanel5.setBounds(0, 0, 500, 500);


		paintComponents(getGraphics());

		setSize(550,330);		
		setLocation((screen.width - 500)/2,((screen.height-350)/2));
		//setResizable(false);
		setVisible(true);
	    
		setCbx();
        setCombo();
        setrt();
       
		Schedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    validator();
				}
		});
		Check.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				new ViewSchedules().setVisible(true);
				
				
			}
		});
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				//new Scheduling_report ().setVisible(true);
				
				
			}
		});
		cboBusNo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			cboRegNo.setSelectedIndex(cboBusNo.getSelectedIndex());
				
			}
		});
		cboRegNo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			cboBusNo.setSelectedIndex(cboRegNo.getSelectedIndex());
				
			}
		});
		
		cboRouteNo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			cboRouteName.setSelectedIndex(cboRouteNo.getSelectedIndex());
				
			}
		});
		cboRouteName.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			cboRouteNo.setSelectedIndex(cboRouteName.getSelectedIndex());
				
			}
		});
		cboDriverName.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			cboDriverNo.setSelectedIndex(cboDriverName.getSelectedIndex());
				
			}
		});
		cboDriverNo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			cboDriverName.setSelectedIndex(cboDriverNo.getSelectedIndex());
				
			}
		});
		Cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				dispose();
				
			}
		}); 	
		
	}
	
		 
	private void setCbx()
	{
		try
		{
		ResultSet rst=con.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT Bus_RegNo,BusNo FROM Buses order by BusNo");
			
			while(rst.next())
			{
				
				cboRegNo.addItem(rst.getString(1));
			    cboBusNo.addItem(rst.getString(2)) ;
			}	
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}			
	}
		private void setrt()
	{
		try
		{
		ResultSet rst=con.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT Route_No,RouteName FROM Route ");
			
			while(rst.next())
			
			{	
			    cboRouteNo.addItem(rst.getString(1));
				cboRouteName.addItem(rst.getString(2)) ;
			
			    
			}	
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}			
	}
   private void setCombo()
	{
		String dr;
		dr="Driver";   
		try
		{
		ResultSet rst=con.createStatement().executeQuery("SELECT * FROM Employee WHERE Employee.Designation='Driver'");
			while(rst.next())
			{
				cboDriverNo.addItem(rst.getString(1));
			    cboDriverName.addItem(rst.getString(2));
			}	
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}
			
			
	}
 
	public static void main(String[] args)
	{JFrame.setDefaultLookAndFeelDecorated(true);
	//	new MainMenu().setVisible(true);
		
	}
   public  void validator()
   {
	    String SQL;
		//SQL = "SELECT * FROM Validator WHERE Bus_No='" + cboBusNo.getSelectedItem() + "'  AND DriverNo='"+cboDriverNo.getSelectedItem()+"'AND Trip_No='"+cboTrip.getSelectedItem()+"'AND Date_Schedule='"+ s_date.getText()+"'";
		SQL = ("SELECT * FROM Validator WHERE Bus_No='" + cboBusNo.getSelectedItem() + "' AND Trip_No='"+cboTrip.getSelectedItem()+"'AND Date_Schedule='"+ s_date.getText()+"'OR DriverNo='"+cboDriverNo.getSelectedItem()+"' AND Trip_No='"+cboTrip.getSelectedItem()+"'AND Date_Schedule='"+ s_date.getText()+"'");
		       
		try
		{
			Statement stmt   = con.createStatement();

			stmt.execute(SQL);
			ResultSet rs = stmt.getResultSet();
			
			boolean recordfound = rs.next();

			if (recordfound)
			{
				
			 JOptionPane.showMessageDialog(null,"Either You are Trying Give a driver \n"+
			 " Another bus or giving one bus two \n"+
			 "Drivers at the same time.","Error",JOptionPane.INFORMATION_MESSAGE);
			 return;
			}
			else{
				try 
				{   
                    if (cboRouteNo.getSelectedItem() == ("Select")){
                    JOptionPane.showMessageDialog(null,"please choose route number","INFORMATION",
                    JOptionPane.DEFAULT_OPTION);
                    return;
                    }
                    if (cboRouteName.getSelectedItem() == ("Select")){
                    JOptionPane.showMessageDialog(null,"please Choose RouteName","INFORMATION",
                    JOptionPane.DEFAULT_OPTION);
                    return;
                    }
                    if (cboBusNo.getSelectedItem() == ("Select")){
                    JOptionPane.showMessageDialog(null,"please choose bus number","INFORMATION",
                    JOptionPane.DEFAULT_OPTION);
                    return;
                    }
                    if (cboRegNo.getSelectedItem() == ("Select")){
                    JOptionPane.showMessageDialog(null,"please choose registration number","INFORMATION",
                    JOptionPane.DEFAULT_OPTION);
                    return;
                    }
                    if (cboDriverNo.getSelectedItem() == ("Select")){
                    JOptionPane.showMessageDialog(null,"please choose driver number","INFORMATION",
                    JOptionPane.DEFAULT_OPTION);
                    return;
                    }
                    if (cboDriverName.getSelectedItem() == ("Select")){
                    JOptionPane.showMessageDialog(null,"please choose DriverName","INFORMATION",
                    JOptionPane.DEFAULT_OPTION);
                    return;
                    }
                    
					Statement statement =con.createStatement();
					{ 
					    	String temp = "INSERT INTO Schedules (Bus_No,Bus_RegNo, Route_No, Route_Name,empNo,Driver_Name,Date_Scheduled,Trip_No,Dept_Time) VALUES ('"+
                                                    
                                              cboBusNo.getSelectedItem() 	   + "', '" +  
			   							   	  cboRegNo.getSelectedItem()       + "', '" +  
			   							   	  cboRouteNo.getSelectedItem()	   + "', '" +  
			   							   	  cboRouteName.getSelectedItem()   + "', '" + 
			   							   	  cboDriverNo.getSelectedItem()	   + "', '" +  
			   							   	  cboDriverName.getSelectedItem()  + "', '" +  
			   							   	  s_date.getText()                 + "', '" +
			   							   	  cboTrip.getSelectedItem()        + "', '" +
			   							   	  txtDepTime.getText()	       +   "')";
			   							   	 
			   							   	 
			   							   	 
				                   int result = statement.executeUpdate( temp );
						           String temp2="INSERT INTO Validator(Bus_No,DriverNo,RouteNo,Date_Schedule,Trip_No)VALUES('"+
						                      cboBusNo.getSelectedItem() 	   + "', '" +  
			   							   	  cboDriverNo.getSelectedItem()    + "', '" +  
					                          cboRouteNo.getSelectedItem()     + "' ,'" +
					                          s_date.getText()                 +"' ,'"  +
					                          cboTrip.getSelectedItem() 	   + "')";
					   
					              int results=statement.executeUpdate(temp2);
					              
					    JOptionPane.showMessageDialog(null,"Scheduling Succesfully done","Sucess??",
                        JOptionPane.DEFAULT_OPTION);
					      }
				 
				     }
				
				 catch ( SQLException sqlex ) {
                         sqlex.printStackTrace();
             }
				
			}
			}
			catch(Exception ex){ex.printStackTrace();}	
		}
			
   }