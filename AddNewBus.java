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
    public class AddNewBus extends JFrame {
	private JLabel BusNo,RegNo,Model,Capacity,DP,Ins,DI,DE;
	
	private JTextField txtBusNo,txtRegNo,txtModel,txtCapacity,txtIns;
		Connection con;
	private JButton AddNew,Cancel,Clear,Next;
	private JPanel jPanel1;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JPanel jPanel5;
    private static JTextArea txtInfo=new JTextArea( 15, 40 );
	private Connection dbconn;
	private static String info;
	private DateButton date_bought;
    private DateButton date_ins;
    private DateButton date_expiry;
    private Date startDate;
    private Date endDate;
    Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
		public AddNewBus()
		{
		
		super("New Bus Entry");
               
		setDefaultCloseOperation(javax.swing.JFrame.HIDE_ON_CLOSE);

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

	
		txtBusNo.setForeground(Color.blue);
		AddNew = new JButton("ADD RECORD",new ImageIcon("Icon/i16x16/contents.png"));
		Cancel = new JButton("CANCEL",new ImageIcon("Icon/i16x16/exit.png"));
        Clear=new JButton ("CLEAR",new ImageIcon("Icon/i16x16/delete.png"));
	    date_bought = new DateButton();
	    date_ins=new DateButton();
	    date_expiry=new DateButton();
	    
	    date_ins.setForeground(Color.red);
	    date_bought.setForeground(Color.red);
	    date_expiry.setForeground(Color.red);
		
		jPanel1 = new JPanel(new GridLayout(8,2));
        jPanel1.setPreferredSize(new Dimension(400,250));
		jPanel1.add(BusNo);jPanel1.add(txtBusNo);
		jPanel1.add(RegNo);jPanel1.add(txtRegNo);
		jPanel1.add(Model);jPanel1.add(txtModel);
		jPanel1.add(Capacity);jPanel1.add(txtCapacity);
        jPanel1.add(DP);jPanel1.add(date_bought);
        jPanel1.add(Ins);jPanel1.add(txtIns);
        jPanel1.add(DI);jPanel1.add(date_ins);
        jPanel1.add(DE);jPanel1.add(date_expiry);
       
		jPanel3 = new JPanel(new java.awt.FlowLayout());

		jPanel3.add(jPanel1);
	

		jPanel4 = new javax.swing.JPanel(new java.awt.FlowLayout());

		jPanel4.add(AddNew);
		jPanel4.add(Cancel);
		jPanel4.add(Clear);
		
		
		setLocation((screen.width - 500)/2,((screen.height-350)/2));
		setResizable(false);	
		 txtModel.addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
           char c = e.getKeyChar();
           if (!(Character.isLetter(c) ||
              (c == KeyEvent.VK_BACK_SPACE) ||
              (c==KeyEvent.VK_SPACE) ||
              (c == KeyEvent.VK_DELETE))) {
             
             getToolkit().beep();
             JOptionPane.showMessageDialog(null,"This Field Only acept text","ERROR",
             JOptionPane.DEFAULT_OPTION);
             e.consume();
           }
         }
       });
       txtIns.addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
           char c = e.getKeyChar();
           if (!(Character.isLetter(c) ||
              (c == KeyEvent.VK_BACK_SPACE) ||
              (c==KeyEvent.VK_SPACE) ||
              (c == KeyEvent.VK_DELETE))) {
             
             getToolkit().beep();
             JOptionPane.showMessageDialog(null,"This Field Only acept text","ERROR",
             JOptionPane.DEFAULT_OPTION);
             e.consume();
           }
         }
       });
       
       
       txtCapacity.addFocusListener(new FocusAdapter() {
         public void focusLost(FocusEvent e) {
           JTextField textField =
             (JTextField)e.getSource();
           String content = textField.getText();
           if (content.length() != 0) {
             try {
               Integer.parseInt(content);
             } catch (NumberFormatException nfe) {
               getToolkit().beep();
               JOptionPane.showMessageDialog(null,"Invalid data entry","ERROR",
               JOptionPane.DEFAULT_OPTION);
               textField.requestFocus();
               txtCapacity.setText("");
             }
           }
         }
       });


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
	      

		AddNew.addActionListener(new ActionListener() 
		{
	 public void actionPerformed(ActionEvent e) {
		 
      if (txtBusNo.getText() == null ||
      txtBusNo.getText().equals("")){   
      JOptionPane.showMessageDialog(null,"Enter bus number","ERROR"
      ,JOptionPane.DEFAULT_OPTION);
      txtBusNo.requestFocus();
      return;
      }
      
      if (txtRegNo.getText() == null ||
      txtRegNo.getText().equals("")){   
      JOptionPane.showMessageDialog(null,"Enter Reg Number","ERROR",
      JOptionPane.DEFAULT_OPTION);
      txtRegNo.requestFocus();
      return;}
      if (txtModel.getText() == null ||
      txtModel.getText().equals("")){   
      JOptionPane.showMessageDialog(null," Model Field is required","ERROR",
      JOptionPane.DEFAULT_OPTION);
      txtModel.requestFocus();
      return;}
      if (txtCapacity.getText() == null ||
      txtCapacity.getText().equals("")){   
      JOptionPane.showMessageDialog(null," Enter bus capacity","ERROR",
      JOptionPane.DEFAULT_OPTION);
      txtCapacity.requestFocus();
      return;}
	  try
	  {
		  int caps = Integer.parseInt(txtCapacity.getText().trim());
		  if (caps < 25 || caps > 55)
		  {
			  JOptionPane.showMessageDialog(null, " Bus Capacity is invalid !", "ERROR !",
			  JOptionPane.DEFAULT_OPTION);
			  txtCapacity.requestFocus();
			  return;
		  }
	  }
	  catch (NumberFormatException ne)
	  {
		  JOptionPane.showMessageDialog(null,"Invalid Capacity !","Sorry !",JOptionPane.ERROR_MESSAGE);
	  }      
      
      if (txtIns.getText() == null ||
      txtIns.getText().equals("")){   
      JOptionPane.showMessageDialog(null," Insurance status entry is required",
      "ERROR",JOptionPane.DEFAULT_OPTION);
      txtIns.requestFocus();
      return;}
		 //-------------------------------------------------------------------------------------------------------------------------------
	  if(check(txtBusNo.getText())==false)
      {
				try{
			Statement statement =con.createStatement();
			String temp = "INSERT INTO Buses (BusNo, Bus_RegNo, Model, Capacity, DateBought,Insurance_Status,Date_Insured,Insurance_Expiry) VALUES ('"+
                                                    
                                              txtBusNo.getText() 	   + "', '" +  
			   							   	  txtRegNo.getText()       + "', '" +  
			   							   	  txtModel.getText()	   + "', '" +  
			   							   	  txtCapacity.getText()	   + "', '" + 
			   							   	  date_bought.getText()	   + "', '" +  
			   							   	  txtIns.getText()         + "', '" +  
			   							   	  date_ins.getText()       + "', '" +  			   							   	  
			   							   	  date_expiry.getText() 	       + "')";
			   							   	 
				int result = statement.executeUpdate( temp );
				String ObjButtons[] = {"Yes","No"};
				 int PromptResult = JOptionPane.showOptionDialog(null,"Record succesfully added.Do you want to add another?",
		             "Travels Booking System",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		             if (PromptResult==0){
		             	
		             
		             	txtRegNo.setText("");
		             	txtModel.setText("");
		             	
		             	txtCapacity.setText("");

		             	txtIns.setText("");
	             
		             }
		             else{
		             	
		             	setVisible(false);
		               
		             }
				}
				catch ( SQLException sqlex )

                      {
                         sqlex.printStackTrace();
                         
                      }
					  
					}
					else { JOptionPane.showMessageDialog(null, "Bus No Already Exists !", "Sorry ! ", JOptionPane.ERROR_MESSAGE); }
				}
				
					});
				
		Cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			setVisible(true);
				dispose();
				new DateButton().setVisible(true);
				
			}
		});
		
		 Clear.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				txtBusNo.setText ("");
				txtRegNo.setText ("");
				txtModel.setText ("");
				txtCapacity.setText ("");
//				txtDP.setText ("");
				txtIns.setText ("");
//				txtDI.setText ("");
//				txtDE.setText("");
				
			}
		});

		jPanel5 = new javax.swing.JPanel(new java.awt.BorderLayout());

		jPanel5.add(jPanel3, BorderLayout.CENTER);
		jPanel5.add(jPanel4, BorderLayout.SOUTH);

		getContentPane().add(jPanel5);

		pack();
		setVisible(true);
    
}
 private boolean check(String num)
	{
		try
		{
		    ResultSet rst=con.createStatement().executeQuery("select * from Buses where BusNo ='"+num+"'");
			if (rst.next())
			{
				System.out.println("Found"); return true; 
			}
			else
			{ System.out.println("Found"); return false; }
		}
		catch(Exception n)
		{
			n.printStackTrace();
			return false;			
		}		
	}

   
	public static void main(String args[]) {

		JFrame.setDefaultLookAndFeelDecorated(true);
		//new LoginScreen().setVisible(true);
	}
    
	
}
