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
    public class AddNewEmployee extends javax.swing.JFrame {
	
	Dimension screen    = Toolkit.getDefaultToolkit().getScreenSize();
	private JLabel       empNo,Sname,Fname,Lname,Gender,Designation,telephone,
	                    email,address,DOB;
	private JTextField  txtEmpNo,txtSname,txtFname,txtLname,txtDesignation,
	                    txttelephone,txtemail,txtaddress;
	
	private JButton jButton1;
	private JButton jButton2;
	private JButton Clear,Next;
	private JPanel jPanel1;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JPanel jPanel5;
	private DateButton dob;
		Connection con;		
	private JComboBox cbogender;
    private static JTextArea txtInfo=new JTextArea( 15, 40 );
	private Connection dbconn;
	private static String info;
    public AddNewEmployee()
	{		
		super("Add New Employee");
               
		setDefaultCloseOperation(javax.swing.JFrame.HIDE_ON_CLOSE);

		setResizable(false);

		 empNo= new JLabel("Employee Number ");
		 Sname= new JLabel("Surname ");
		 Fname= new JLabel("First Name ");
		 Lname= new JLabel("Mid Name ");
         Gender= new JLabel("Gender ");
         Designation= new JLabel("Designation ");
		 telephone=new JLabel("Telephone Number");
		 email=new JLabel("E-mail Address");
		 address=new JLabel("Address");
		 DOB=new JLabel("DOB");
		txtEmpNo = new JTextField(10);
		txtSname = new JTextField(10);
		txtFname = new JTextField(10);
		txtLname = new JTextField(10);
        cbogender = new JComboBox();
		txtDesignation= new JTextField(10);
		txttelephone=new JTextField(10);
		txtemail=new JTextField(10);
		txtaddress=new JTextField(10);
		
		jButton1 = new JButton("ADD RECORD",new ImageIcon("INF/save.png"));
		jButton2 = new JButton("CANCEL",new ImageIcon("INF/cancel.png"));
        Clear=new JButton ("CLEAR",new ImageIcon("INF/clear.png"));
        dob=new DateButton();
        dob.setForeground(Color.red);
        
        jPanel1 = new JPanel(new java.awt.GridLayout(10,2));
        jPanel1.setPreferredSize(new Dimension(400,250));
		jPanel1.add(empNo);jPanel1.add(txtEmpNo);		
		jPanel1.add(Fname);jPanel1.add(txtFname);
		jPanel1.add(Lname);jPanel1.add(txtLname);
		jPanel1.add(Sname); jPanel1.add(txtSname);
        jPanel1.add(Gender);jPanel1.add(cbogender);
        
        jPanel1.add(DOB);jPanel1.add(dob);
        jPanel1.add(telephone);jPanel1.add(txttelephone);
        jPanel1.add(email);jPanel1.add(txtemail);
        jPanel1.add(address);jPanel1.add(txtaddress);
        jPanel1.add(Designation);jPanel1.add(txtDesignation);
        
		jPanel4=new JPanel();
		jPanel4.add(jButton1);
		jPanel4.add(jButton2);
		jPanel4.add(Clear);
		
		jPanel3 = new JPanel();
		jPanel3.add(jPanel1);
		jPanel3.add(jPanel4);
		add(jPanel3);
		setSize(400,250);
		setResizable(false);
		 cbogender.addItem("Male");
		 cbogender.addItem("Female");
		 setLocation((screen.width - 500)/2,((screen.height-350)/2));
		 	//----------------------------------------------------------------------------------------	
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
	//----------------------------------------------------------------------------------------	  
	      generator();		 
		 txtSname.addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
           char c = e.getKeyChar();
           if (!(Character.isLetter(c) ||
              (c == KeyEvent.VK_BACK_SPACE) ||
              (c==KeyEvent.VK_SPACE) ||
              (c == KeyEvent.VK_DELETE))) {
             
             getToolkit().beep();
             JOptionPane.showMessageDialog(null,"This Field Only acept text","Travels Booking System",
             JOptionPane.DEFAULT_OPTION);
             e.consume();
           }
         }
       });
       txtFname.addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
           char c = e.getKeyChar();
           if (!(Character.isLetter(c) ||
              (c == KeyEvent.VK_BACK_SPACE) ||
              (c==KeyEvent.VK_SPACE) ||
              (c == KeyEvent.VK_DELETE))) {
             
             getToolkit().beep();
             JOptionPane.showMessageDialog(null,"This Field Only acept text","Travels Booking System",
             JOptionPane.DEFAULT_OPTION);
             e.consume();
           }
         }
       });
       txtLname.addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
           char c = e.getKeyChar();
           if (!(Character.isLetter(c) ||
              (c == KeyEvent.VK_BACK_SPACE) ||
              (c==KeyEvent.VK_SPACE) ||
              (c == KeyEvent.VK_DELETE))) {
             
             getToolkit().beep();
             JOptionPane.showMessageDialog(null,"This Field Only acept text","Travels Booking System",
             JOptionPane.DEFAULT_OPTION);
             e.consume();
           }
         }
       });
       
       
       txtDesignation.addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
           char c = e.getKeyChar();
           if (!(Character.isLetter(c) ||
              (c == KeyEvent.VK_BACK_SPACE) ||
              (c==KeyEvent.VK_SPACE) ||
              (c == KeyEvent.VK_DELETE))) {
             txtDesignation.requestFocus();
             getToolkit().beep();
             JOptionPane.showMessageDialog(null,"This Field Only acept text","Travels Booking System",
             JOptionPane.DEFAULT_OPTION);
             e.consume();
           }
         }
       });
	   txttelephone.addKeyListener(new KeyAdapter()
	   {
		   public void keyTyped(KeyEvent e)
		   {
			   char c = e.getKeyChar();
			   if (!(Character.isDigit(c) ||
				  (c == KeyEvent.VK_BACK_SPACE) ||
				  (c == KeyEvent.VK_SPACE) ||
				  (c == KeyEvent.VK_DELETE)))
			   {
				   txttelephone.requestFocus();
				   getToolkit().beep();
				   JOptionPane.showMessageDialog(null, "This Field Only acept text", "Travels Booking System",
				   JOptionPane.DEFAULT_OPTION);
				   e.consume();
			   }
		   }
	   });
       txttelephone.addFocusListener(new FocusAdapter() {
         public void focusLost(FocusEvent e) {
           JTextField textField =
             (JTextField)e.getSource();
           String content = textField.getText();
           if (content.length() != 0) {
             try {
               //Integer.parseInt(content);
             } catch (NumberFormatException nfe) {
               getToolkit().beep();
			   JOptionPane.showMessageDialog(null, "Invalid data entry", "Travels Booking System",
               JOptionPane.DEFAULT_OPTION);
               textField.requestFocus();
               txttelephone.setText("");
             }
           }
         }
       });
		jButton1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				if (txtEmpNo.getText() == null ||
                txtEmpNo.getText().equals("")){   
                JOptionPane.showMessageDialog(null,"Enter Employee Number",
				"Travels Booking System"
                ,JOptionPane.DEFAULT_OPTION);
                 txtEmpNo.requestFocus();
                 return;}
                
                if (txtSname.getText() == null ||
                txtSname.getText().equals("")){   
                JOptionPane.showMessageDialog(null,"Enter Employee Surname ",
				"Travels Booking System"
                ,JOptionPane.DEFAULT_OPTION);
                 txtSname.requestFocus();
                 return;}
                 
                if (txtFname.getText() == null ||
                txtFname.getText().equals("")){   
                JOptionPane.showMessageDialog(null,"Enetr Employee First Name",
				"Travels Booking System"
                ,JOptionPane.DEFAULT_OPTION);
                 txtFname.requestFocus();
                 return;}
                 
                if (txtLname.getText() == null ||
                txtLname.getText().equals("")){   
                JOptionPane.showMessageDialog(null,"Enetr Employee Last Name",
				"Travels Booking System"
                ,JOptionPane.DEFAULT_OPTION);
                 txtLname.requestFocus();
                 return;}
                if (txttelephone.getText() == null ||
				txttelephone.getText().equals("") || txttelephone.getText().length() > 10)
				{   
                JOptionPane.showMessageDialog(null,"Enter Correct Contact number !",
                "Travels Booking System"
                ,JOptionPane.DEFAULT_OPTION);
                 txttelephone.requestFocus();
                 return;}
                 if (txtemail.getText() == null ||
                txtemail.getText().equals("")){   
                JOptionPane.showMessageDialog(null,"Enter E-mail address",
				"Travels Booking System"
                ,JOptionPane.DEFAULT_OPTION);
                 txtemail.requestFocus();
                 return;}
                 if (txtaddress.getText() == null ||
                txtaddress.getText().equals("")){   
                JOptionPane.showMessageDialog(null,"Enter Address",
				"Travels Booking System"
                ,JOptionPane.DEFAULT_OPTION);
                 txtaddress.requestFocus();
                 return;}
                 
                if (txtDesignation.getText() == null ||
                txtDesignation.getText().equals("")){   
                JOptionPane.showMessageDialog(null,"Enter Employee designation",
				"Travels Booking System"
                ,JOptionPane.DEFAULT_OPTION);
                 txtDesignation.requestFocus();
                 return;}
                 
		try{
			Statement statement =con.createStatement();
				{
			String temp = "INSERT INTO Employee (EmpNo, Fname, Mname, Lname, Gender,DOB,Designation,Telephone,E_Mail,Address) VALUES ('"+
                                                    
                                              txtEmpNo.getText() 	   + "', '" +  
			   							   	  txtFname.getText()       + "', '" +  
			   							   	  txtLname.getText()	   + "', '" +  
			   							   	  txtSname.getText()	   + "', '" +  
			   							   	  cbogender.getSelectedItem()+ "', '" +  
			   							   	  dob.getText()	       + "', '" +  
			   							   	  txtDesignation.getText() + "', '" +  
			   							   	  txttelephone.getText()   + "', '" +  
			   							   	  txtemail.getText()	   + "', '" +  
			   							   	  txtaddress.getText() 	   + "')";
				int result = statement.executeUpdate( temp );
				String ObjButtons[] = {"Yes","No"};
				 int PromptResult = JOptionPane.showOptionDialog(null,"Record succesfully added.Do you want to add another?",
		             "Travels Booking System",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		             if (PromptResult==0){
		             	generator();
		             	
		             	txtSname.setText("");
		             	txtFname.setText("");
		             	txtLname.setText("");

		             	txtDesignation.setText("");
		             	txttelephone.setText("");
		             	txtemail.setText("");

		             	txtaddress.setText("");
		             	
		             }
		             else{
		             	new Employees().setVisible(true);
		             	setVisible(false);
		               
		             }
					}
					
				}
				catch ( SQLException sqlex )

                      {
                         sqlex.printStackTrace();
                      }
					}
					});

		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
//				new Employees();
				dispose();
			}
		});
		Clear.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				//txtEmpNo.setText ("");
				txtSname.setText ("");
				txtFname.setText ("");
				txtLname.setText ("");
//				txtGender.setText ("");
				txtDesignation.setText ("");
				txttelephone.setText("");
//				txtdob.setText("");
				txtemail.setText("");
				txtaddress.setText("");
				
			}
		});

		jPanel5 = new javax.swing.JPanel(new java.awt.BorderLayout());

		jPanel5.add(jPanel3, BorderLayout.CENTER);
		jPanel5.add(jPanel4, BorderLayout.SOUTH);

		getContentPane().add(jPanel5);

		pack();
		setVisible(true);
	}
    //validating textboxes
	public static void main(String args[]) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		//new LoginScreen().setVisible(true);

		new AddNewEmployee().setVisible(false);
	}
	private void generator()
	{
		
		try
		{
		ResultSet rst=con.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT empNo FROM Employee");
			txtEmpNo.setText("1000");
			while(rst.next())
			{
				String s;
				int number=rst.getInt(1);
				number=number+1;
				
				s=""+number;
				txtEmpNo.setText(s);
			    txtEmpNo.setEditable(false);
			}	
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}
	}
}