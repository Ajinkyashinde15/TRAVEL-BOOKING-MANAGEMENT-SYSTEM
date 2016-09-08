

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
public class UpdateBuses extends javax.swing.JFrame 
{
	private JLabel BusNo,RegNo,Model,Capacity,DP,Ins,DI,DE;
	private JTextField txtBusNo,txtRegNo,txtModel,txtCapacity,txtIns;
	private String _busNo=null;
	private JButton Update,Load,Clear;
	Connection con;
	String busNo, regNo,model, capacity, db,is,ie,id;
	private JButton jButton2;
	private JPanel jPanel1;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JPanel jPanel5;
	private DateButton date_bought;
    private DateButton date_ins;
    private DateButton date_expiry;
    private static JTextArea txtInfo=new JTextArea( 15, 40 );
	private Connection dbconn;
	private static String info;
	Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();

	public UpdateBuses(String busNo)
	{ 
		super("Update Bus Details");
		_busNo=busNo;	
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(true);

		BusNo = new JLabel("Bus Number ");
		RegNo = new JLabel("Reg Number ");
		Model = new JLabel("Model.: ");
		Capacity = new JLabel("Capacity ");
		DP = new JLabel("Date Purchased");
		Ins = new JLabel("Insurance Status");
		DI = new JLabel("Date Insured");
		DE = new JLabel("Insurance Expiry Date");
		
		txtBusNo = new JTextField(10);
		txtRegNo = new JTextField(10);
		txtModel = new JTextField(10);
		txtCapacity = new JTextField(10);
	   
		txtIns = new JTextField(10);

		txtRegNo.setEditable(false); txtModel.setEditable(false); txtCapacity.setEditable(false); txtIns.setEditable(false);
 
		date_bought  =new DateButton();
		date_ins     =new DateButton();
		date_expiry  =new DateButton();
		
		
		date_ins.setForeground(Color.red);
		date_bought.setForeground(Color.red);
		date_expiry.setForeground(Color.red);
		
		Update = new JButton("Update",new ImageIcon("INF/reset.png"));
		Clear=new JButton ("Clear",new ImageIcon("INF/exit.png"));
		jButton2 = new JButton("Cancel",new ImageIcon("INF/cancel.png"));
		
		jPanel1 = new JPanel(new java.awt.GridLayout(8,2));
		 jPanel1.setPreferredSize(new Dimension (400,250));
		jPanel1.add(BusNo);jPanel1.add(txtBusNo);
		jPanel1.add(RegNo);jPanel1.add(txtRegNo);
		jPanel1.add(Model);jPanel1.add(txtModel);
		jPanel1.add(Capacity);jPanel1.add(txtCapacity);
		jPanel1.add(DP);jPanel1.add(date_bought);
		jPanel1.add(Ins);jPanel1.add(txtIns);
		jPanel1.add(DI);jPanel1.add(date_ins );
		jPanel1.add(DE);jPanel1.add(date_expiry);
		

		jPanel3 = new javax.swing.JPanel(new java.awt.FlowLayout());

		jPanel3.add(jPanel1);
	

		jPanel4 = new javax.swing.JPanel(new java.awt.FlowLayout());
		
		jPanel4.add(Update);
		jPanel4.add(jButton2);
		jPanel4.add(Clear);		 
		setLocation((screen.width - 500)/2,((screen.height-350)/2));	
		setResizable(false);
				
		txtModel.addKeyListener
		(
			new KeyAdapter() 
			{
				public void keyTyped(KeyEvent e) 
				{
					char c = e.getKeyChar();
					if (!(Character.isLetter(c) ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c==KeyEvent.VK_SPACE) ||
						(c == KeyEvent.VK_DELETE))) 
						{
							getToolkit().beep();	
							JOptionPane.showMessageDialog(null,"This Field Only acept text","Travels Booking System", JOptionPane.DEFAULT_OPTION);
							e.consume();
						}
				}
			}
		);
		txtIns.addKeyListener
		(
			new KeyAdapter() 
			{
				public void keyTyped(KeyEvent e) 
				{
					char c = e.getKeyChar();
					if (!(Character.isLetter(c) ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c==KeyEvent.VK_SPACE) ||
						(c == KeyEvent.VK_DELETE))) 
						{
							getToolkit().beep();	
							JOptionPane.showMessageDialog(null,"This Field Only acept text","Travels Booking System", JOptionPane.DEFAULT_OPTION);
							e.consume();
						}
				} 
			}
		);
       
		txtCapacity.addFocusListener
		(
			new FocusAdapter() 
			{
				public void focusLost(FocusEvent e) 
				{
					JTextField textField =(JTextField)e.getSource();
					String content = textField.getText();
					if (content.length() != 0) 
					{
						try 
						{
							Integer.parseInt(content);
						} catch (NumberFormatException nfe) 
						{
							getToolkit().beep();
							JOptionPane.showMessageDialog(null,"Invalid data entry","Travels Booking System",
							JOptionPane.DEFAULT_OPTION);
							textField.requestFocus();
							txtCapacity.setText("");
						}
					}
				}
			}
		);

	   try
	   {
		   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		   String database = "";
		   database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
		   database += "Travels.mdb" + ";DriverID=22;READONLY=true}";
		   //now we 	can get connection from DriverManager
		   con = DriverManager.getConnection(database, "", "");
		   Statement statement = con.createStatement();
		String query = ("SELECT * FROM Buses where BusNo ='" + busNo + "'");
			
		ResultSet rs = statement.executeQuery(query);
			if (rs.next())
			{
				txtBusNo.setText(busNo);
				txtRegNo.setText(rs.getString(1));
				txtModel.setText(rs.getString(3));
				txtCapacity.setText(rs.getString(4));
				date_bought.setText(rs.getString(5));
				txtIns.setText(rs.getString(6));
				date_ins.setText(rs.getString(7));
				date_expiry.setText(rs.getString(8));
				unlock();
				statement.close();
			   txtBusNo.setEditable(false);
		   }
		}
		catch (Exception e)
	   { System.out.println("Error in Con :" + e); }
	      
                 
	Update.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent e) {
	  if (txtBusNo.getText() == null ||
      txtBusNo.getText().equals("")){   
      JOptionPane.showMessageDialog(null,"Error?? BusNo can't be null","Travels Booking System"
      ,JOptionPane.DEFAULT_OPTION);
      txtBusNo.requestFocus();
      return;
      }
      if (txtRegNo.getText() == null ||
      txtRegNo.getText().equals("")){   
      JOptionPane.showMessageDialog(null,"Error?? RegNo can't be null","Travels Booking System",
      JOptionPane.DEFAULT_OPTION);
      txtRegNo.requestFocus();
      return;}
      if (txtModel.getText() == null ||
      txtModel.getText().equals("")){   
      JOptionPane.showMessageDialog(null,"Error?? Model Field is required","Travels Booking System",
      JOptionPane.DEFAULT_OPTION);
      txtModel.requestFocus();
      return;}
      if (txtCapacity.getText() == null ||
      txtCapacity.getText().equals("")){   
      JOptionPane.showMessageDialog(null,"Error?? Enter bus capacity","Travels Booking System",
      JOptionPane.DEFAULT_OPTION);
      txtCapacity.requestFocus();
      return;}
      
      if (txtIns.getText() == null ||
      txtIns.getText().equals("")){   
      JOptionPane.showMessageDialog(null,"Error?? Insurance status entry is required",
      "Travels Booking System",JOptionPane.DEFAULT_OPTION);
      txtIns.requestFocus();
      return;}

	  
				try 
				{
					Statement statement =con.createStatement();							                
                    String temp = "UPDATE Buses SET " +
					" Bus_RegNo       ='" + txtRegNo.getText ()+
					"',  Model              ='" + txtModel.getText()      +
                    "',Capacity           ='" + txtCapacity.getText()   +					
					"',DateBought         ='" + date_bought.getText()   +
					"',Insurance_Status   ='" + txtIns.getText()        +
					"',Date_Insured       ='" + date_ins.getText()      +
					"',Insurance_Expiry   ='" + date_expiry.getText()   +
					"' WHERE BusNo ='"     + txtBusNo.getText() + "'";
                    int result = statement.executeUpdate( temp );
					if (result==1)
					{
						JOptionPane.showMessageDialog(null, "Records Updated !",
						"Travels Booking System", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please Check Bus No !",
								"Travels Booking System", JOptionPane.ERROR_MESSAGE);
					}

				}
				
				 catch ( SQLException sqlex ) {
					 sqlex.printStackTrace(); JOptionPane.showMessageDialog(null, "Error in Updation !",
								"Travels Booking System", JOptionPane.ERROR_MESSAGE);
             }
				
			}
		});

		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		//---------------------------------------------------------------------------------------
		Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBusNo.setText ("");
				txtRegNo.setText ("");
				txtModel.setText ("");
				txtCapacity.setText ("");

				txtIns.setText ("");
				date_bought.setText("") ;
				date_ins.setText("");
				date_expiry.setText("");
				
			}
		});

				//-----------------------------------------------------------------------------------------------------------------------------
			jPanel5 = new javax.swing.JPanel(new java.awt.BorderLayout());

		jPanel5.add(jPanel3, BorderLayout.CENTER);
		jPanel5.add(jPanel4, BorderLayout.SOUTH);

		getContentPane().add(jPanel5);

		pack();
		setVisible(true);
	}
	public void unlock()
	{
		txtRegNo.setEditable(true); txtModel.setEditable(true); txtCapacity.setEditable(true); txtIns.setEditable(true);
		date_bought.setEnabled(true);
		date_ins.setEnabled(true); ;
		date_expiry.setEnabled(true);
	}
	public void lock()
	{
		txtRegNo.setEditable(false); txtModel.setEditable(false); txtCapacity.setEditable(false); txtIns.setEditable(false);
		date_bought.setEnabled(false);
		date_ins.setEnabled(false);
		date_expiry.setEnabled(false);
		txtBusNo.setText("");
		txtRegNo.setText("");
		txtModel.setText("");
		txtCapacity.setText("");
		txtIns.setText("");
		date_bought.setText("");
		date_ins.setText("");
		date_expiry.setText("");
	}
	public void display( ResultSet rs )
          {
             try
             {
                //rs.next();
                
               boolean recordNumber = rs.next();
                if ( recordNumber)
                {  regNo = rs.getString(1);
                  
                   busNo=rs.getString(2);
                   
                   model = rs.getString(3);
                   capacity = rs.getString(4);
                   db = rs.getString(5);
                   
                   is = rs.getString(6);
                   ie= rs.getString(7);
                   id = rs.getString(8);
                   
                   txtBusNo.setText(busNo);
                   txtRegNo.setText(regNo);
	               txtModel.setText(model); 
                   txtCapacity.setText(capacity);
                   date_bought.setText(db);
                   txtIns.setText(is);
                   date_ins .setText(ie);
                   date_expiry.setText(id);
                }

                else
                   {
                         JOptionPane.showMessageDialog(null,"Record Not found","ERROR",
                         JOptionPane.DEFAULT_OPTION);        
                   }
               }
                catch ( SQLException sqlex )

                {   
                sqlex.printStackTrace();
               
               }

            }
            
         
	public static void main(String args[]) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		//new LoginScreen().setVisible(true);

		new UpdateBuses(null).setVisible(false);
	}
}