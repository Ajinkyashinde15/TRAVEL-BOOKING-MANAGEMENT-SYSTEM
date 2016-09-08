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
public class UpdateRoute extends javax.swing.JFrame {
	
	private JLabel routeNo,routeName,From,To,Distance,Amount;
	private JTextField txtRouteNo,
	txtRouteName,txtFrom,txtTo,txtDistance,txtAmount;
	private JButton Update,Search,Clear;
	Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
	String route,name,from,to,distance,amount;
	private JButton Cancel;
	private JPanel jPanel1;
	final JFileChooser fc 	= new JFileChooser();
	String getPicture;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JPanel jPanel5;
	Connection con;
	private Connection dbconn;
	private static String info;
	public UpdateRoute(int rNo){
		super("Update Routes");

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		WA wa = new WA();
		addWindowListener(wa);		

        routeNo = new JLabel("Route No ");
		routeName = new JLabel("Route Name ");
		From = new JLabel("From");
		To = new JLabel("To");
        Distance = new JLabel("Distance");
	
		txtRouteNo = new JTextField(10);
		txtRouteName = new JTextField(10);
		txtFrom= new JTextField(10);
		txtTo = new JTextField(10);
        txtDistance = new JTextField(15);
		Amount=new JLabel("Fare_Charged");
		txtAmount=new JTextField(10);
		txtRouteNo.setText(route);
		txtRouteName.setText(name);
		txtFrom.setText(from);
		txtTo.setText(to);
        txtDistance.setText(distance);
        txtAmount.setText(amount);
		
		Update = new JButton("Update",new ImageIcon("INF/reset.png"));
		Clear = new JButton("Clear", new ImageIcon("INF/clear.png"));
		Cancel = new JButton("Cancel", new ImageIcon("INF/exit.png"));
		jPanel1= new javax.swing.JPanel(new java.awt.GridLayout(6,2));
		jPanel1.add(routeNo);jPanel1.add(txtRouteNo);
		jPanel1.add(routeName);jPanel1.add(txtRouteName);
		jPanel1.add(From);jPanel1.add(txtFrom);
		jPanel1.add(To);jPanel1.add(txtTo);
        jPanel1.add(Distance);jPanel1.add(txtDistance);
		jPanel1.add(Amount);jPanel1.add(txtAmount);

		jPanel3 = new javax.swing.JPanel(new java.awt.FlowLayout());

		jPanel3.add(jPanel1);
	

		jPanel4 = new javax.swing.JPanel(new java.awt.FlowLayout());
         
		jPanel4.add(Update);
		jPanel4.add(Cancel);
		jPanel4.add(Clear);
	    setResizable(false);
		setSize(400, 250);
		setVisible(true);
		setBounds(250,200,500,300);
		
		//setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String database = "";
			database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
			database += "Travels.mdb" + ";DriverID=22;READONLY=true}";
			//now we 	can get connection from DriverManager
			con = DriverManager.getConnection(database, "", "");
			Statement statement = con.createStatement();
			String query = ("SELECT * FROM Route where Route_No =" + rNo);
				ResultSet rs = statement.executeQuery(query);

				if (rs.next())
				{
					
					txtRouteNo.setText (rs.getString(1));
					txtRouteName.setText (rs.getString(2));
					txtFrom.setText (rs.getString(3));
					txtTo.setText (rs.getString(4));
					txtDistance.setText (rs.getString(5));
					txtAmount.setText(rs.getString(6));
				}
				txtRouteNo.setEditable(false);
		}
		catch (Exception e)
		{ System.out.println("Error in Con :" + e); }
		txtRouteName.addKeyListener(new KeyAdapter() {
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
       txtTo.addKeyListener(new KeyAdapter() {
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
       txtFrom.addKeyListener(new KeyAdapter() {
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
       txtAmount.addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
           char c = e.getKeyChar();
           if (!(Character.isDigit(c) ||
              (c == KeyEvent.VK_BACK_SPACE) ||
              (c==KeyEvent.VK_SPACE) ||
              (c == KeyEvent.VK_DELETE))) {
             
             getToolkit().beep();
             JOptionPane.showMessageDialog(null,"Amount is in Digit","ERROR",
             JOptionPane.DEFAULT_OPTION);
             e.consume();
           }
         }
       });
       txtDistance.addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
           char c = e.getKeyChar();
           if (!(Character.isDigit(c) ||
              (c == KeyEvent.VK_BACK_SPACE) ||
              (c==KeyEvent.VK_SPACE) ||
              (c == KeyEvent.VK_DELETE))) {
             
             getToolkit().beep();
             JOptionPane.showMessageDialog(null,"Distance in digit","ERROR",
             JOptionPane.DEFAULT_OPTION);
             e.consume();
           }
         }
       });
      
	  Update.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			  if (txtRouteNo.getText() == null ||
                txtRouteNo.getText().equals("")){   
                JOptionPane.showMessageDialog(null,"Enter route number","Travels Booking System",
                JOptionPane.DEFAULT_OPTION);
                txtRouteNo.requestFocus();
                return;}
                if (txtRouteName.getText() == null ||
                txtRouteName.getText().equals("")){   
                JOptionPane.showMessageDialog(null,"Enter route name","Travels Booking System",
                JOptionPane.DEFAULT_OPTION);
                txtRouteName.requestFocus();
                return;}
                if (txtFrom.getText() == null ||
                txtFrom.getText().equals("")){   
                JOptionPane.showMessageDialog(null,"Enter From","Travels Booking System",
                JOptionPane.DEFAULT_OPTION);
                txtFrom.requestFocus();
                return;}
                if (txtTo.getText() == null ||
                txtTo.getText().equals("")){   
                JOptionPane.showMessageDialog(null,"Enter To","Travels Booking System",
                JOptionPane.DEFAULT_OPTION);
                txtTo.requestFocus();
                return;}
                if (txtDistance.getText() == null ||
                txtDistance.getText().equals("")){   
                JOptionPane.showMessageDialog(null,"Enter Distance","Travels Booking System",
                JOptionPane.DEFAULT_OPTION);
                txtDistance.requestFocus();
                return;}
				int result = 0;
				try 
				{					
					Statement statement =con.createStatement();					
					String temp = "UPDATE Route SET " +
					"  RouteName        ='"+ txtRouteName.getText() +
                    "',Depot           ='"+ txtFrom.getText()       +
					"',Destination    ='" + txtTo.getText()         +
					"',Distance   ='" + txtDistance.getText()       +
					"',Fare_Charged='"+txtAmount.getText()          +
					"' WHERE Route_No ="     + txtRouteNo.getText()  ;
                    result = statement.executeUpdate( temp );					
					
				      setVisible(false);
				
				      dispose();					  
					
					if (result==0)
					{
						JOptionPane.showMessageDialog(null, "No Record To Update !", "Sorry !", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Record Updated !", "Done !", JOptionPane.INFORMATION_MESSAGE);
					}
					statement.close();
					new Routes();
					con.close();
				}
				
				 catch ( SQLException sqlex ) {
                        sqlex.printStackTrace();

             }
				
			}
		});

		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Routes();
				dispose();
			}
		});
		
		Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtRouteNo.setText ("");
				txtRouteName.setText ("");
				txtFrom.setText ("");
				txtTo.setText ("");
				txtDistance.setText ("");
				txtAmount.setText("");
		}
		});

		txtRouteNo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					if (!txtRouteNo.equals(""))
					{

						Statement statement = con.createStatement();
						String query = ("SELECT * FROM Route where Route_No =" + txtRouteNo.getText() + "");
						ResultSet rs = statement.executeQuery(query);
						if (rs.next())
						{
							txtRouteNo.setText(rs.getString(1));
							txtRouteName.setText(rs.getString(2));
							txtFrom.setText(rs.getString(3));
							txtDistance.setText(rs.getString(4));
							txtTo.setText(rs.getString(5));
							txtAmount.setText(rs.getString(6));
						}
						else
						{
							txtRouteNo.setText("");
							txtRouteName.setText("");
							txtFrom.setText("");
							txtTo.setText("");
							txtDistance.setText("");
							txtAmount.setText("");
							JOptionPane.showMessageDialog(null, "No Record Found ", "Sorry ", JOptionPane.ERROR_MESSAGE);
						}
						statement.close();
					}
				}
				catch (SQLException sqlex)
				{
					sqlex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Please Check Route No ", "Sorry ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
				//-----------------------------------------------------------------------------------------------

		jPanel5 = new javax.swing.JPanel(new java.awt.BorderLayout());

		jPanel5.add(jPanel3, java.awt.BorderLayout.CENTER);
		jPanel5.add(jPanel4, java.awt.BorderLayout.SOUTH);

		getContentPane().add(jPanel5);

		//pack();
		setVisible(true);
	}

	public static void main(String args[]) {
		JFrame.setDefaultLookAndFeelDecorated(true);
	//	new LoginScreen().setVisible(true);

		new UpdateRoute(0).setVisible(false);
	}
	class WA extends WindowAdapter
	{
		public void windowClosing(WindowEvent we)
		{
			setVisible(false);
			new Routes();
			dispose();						
		}
	}
}