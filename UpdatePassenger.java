import java.applet.Applet;
import java.awt.*;
import java.text.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.StringBuffer;
import java.io.IOException;
import java.io.*;
import java.sql.*;
import javax.swing.plaf.metal.*; 
public class UpdatePassenger extends JFrame{
	private JLabel label1,label2,label3,label4,label5,label6,label7,label8;
	private JTextField text1,text2,text3,text4,text6;
	private JButton button1,button2,button3,button4,button5;
	private JPanel panel1,panel2,panel3;
	private JComboBox combo1,combo2;
	private DateButton dob;
	String t1,t2,t3,t4,t5,t6,t7;
	Connection con;
	Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
	public UpdatePassenger(String pId)
	{
		super ("Update Passenger Details");
		label1=new JLabel("Passenger Number");
		label2=new JLabel("Passenger Name");
		label3=new JLabel("Address");
		label4=new JLabel("Telephone Number");
		label5=new JLabel("Date_of_Travel");
		label6=new JLabel("From");
		label7=new JLabel("To");
		text1=new JTextField(10);
		text2=new JTextField(10);
		text3=new JTextField(10);
		text4=new JTextField(10);
		text6=new JTextField(10);
		dob  =new DateButton();
	    dob.setForeground(Color.red);
		combo1=new JComboBox();
		combo2=new JComboBox();		
		button1=new JButton("UPDATE",new ImageIcon("INF/reset.png"));
		button3=new JButton("DELETE",new ImageIcon("INF/delete.png"));
		button4=new JButton("CLEAR",new ImageIcon("INF/clear.png"));
		button5=new JButton("CANCEL",new ImageIcon("INF/exit.png"));
		
		panel1=new JPanel(new GridLayout(7,2));
		panel1.setPreferredSize(new Dimension(350,250));
		panel1.add(label1);panel1.add(text1);
		panel1.add(label2);panel1.add(text2);
		panel1.add(label3);panel1.add(text3);
		panel1.add(label4);panel1.add(text4);
		panel1.add(label5);panel1.add(dob);
		panel1.add(label6);panel1.add(combo1);
		panel1.add(label7);panel1.add(combo2);
		
		
		panel2=new JPanel();
		panel2.add(button1);
		panel2.add(button3);
		panel2.add(button4);
		panel2.add(button5);
		panel3=new JPanel();
		
		text1.setText("");
		text2.setText("");
		text3.setText("");
		text4.setText("");
		dob.setText("");
		
		panel3.setPreferredSize(new Dimension(550,400));
		panel3.add(panel1);
		panel3.add(panel2);
		add(panel3);
		setSize(540,350);
		setResizable(false);
		setVisible(true);

		//----------------------------------------------------------------------------------------	
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String database = "";
			database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
			database += "Travels.mdb" + ";DriverID=22;READONLY=true}";
			//now we 	can get connection from DriverManager
			con = DriverManager.getConnection(database, "", "");
			Statement statement = con.createStatement();
			String query = "SELECT * FROM Passengers where Pass_No ='"+ pId+"'";
			ResultSet rs = statement.executeQuery(query);
			if (rs.next())
			{
				
				text1.setText(rs.getString(1));
				text2.setText(rs.getString(2));
				text3.setText(rs.getString(3));
				text4.setText(rs.getString(4));
				dob.setText(rs.getString(5));
			}
		}
		catch (Exception e)
		{ System.out.println("Error in Con :" + e); }
		//----------------------------------------------------------------------------------------	  

		setCbx();
		setLocation((screen.width - 500)/2,((screen.height-350)/2));	
		text1.addFocusListener(new FocusAdapter() {
         public void focusLost(FocusEvent e) {
           JTextField textField =
             (JTextField)e.getSource();
           String content = textField.getText();
           if (content.length() != 0) {
             try {
               Integer.parseInt(content);
             } catch (NumberFormatException nfe) {
               getToolkit().beep();
               JOptionPane.showMessageDialog(null,"Invalid data entry","Travels Booking System",
               JOptionPane.DEFAULT_OPTION);
               textField.requestFocus();
               text1.setText("");
             }
           }
         }
       });

	   text1.addActionListener(new ActionListener()
	   {
		   public void actionPerformed(ActionEvent e)
		   {
			   showRecords();
		   }
	   });
	

       text2.addKeyListener(new KeyAdapter() {
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
       
      text4.addFocusListener(new FocusAdapter() {
         public void focusLost(FocusEvent e) {
           JTextField textField =
             (JTextField)e.getSource();
           String content = textField.getText();
           if (content.length() != 0) {
             try {
               Integer.parseInt(content);
             } catch (NumberFormatException nfe) {
               getToolkit().beep();
               JOptionPane.showMessageDialog(null,"Invalid data entry","Travels Booking System",
               JOptionPane.DEFAULT_OPTION);
               textField.requestFocus();
               text4.setText("");
             }
           }
         }
       });
	button1.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent e) {
	  if (text1.getText() == null ||
      text1.getText().equals("")){   
      JOptionPane.showMessageDialog(null,"Enter Passenger number","Travels Booking System"
      ,JOptionPane.DEFAULT_OPTION);
      text1.requestFocus();
      return;
      }
      if (text2.getText() == null ||
      text2.getText().equals("")){   
      JOptionPane.showMessageDialog(null,"Enter passenger name","Travels Booking System",
      JOptionPane.DEFAULT_OPTION);
      text2.requestFocus();
      return;}
      if (text3.getText() == null ||
      text3.getText().equals("")){   
      JOptionPane.showMessageDialog(null,"Address Field is required","Travels Booking System",
      JOptionPane.DEFAULT_OPTION);
      text3.requestFocus();
      return;}
      if (text4.getText() == null ||
      text4.getText().equals("")){   
      JOptionPane.showMessageDialog(null,"Enter telephone number","Travels Booking System",
      JOptionPane.DEFAULT_OPTION);
      text4.requestFocus();
      return;}
     
				try 
				{
					Statement statement =con.createStatement();
					{
						 
	                
                     String temp = "UPDATE Passengers SET " +
					"  Pass_Name       ='" + text2.getText()          +
                    "',Address         ='" + text3.getText()          +	
					"',Tel_No          ='" + text4.getText()          +
					"',Date_of_Travel  ='" + dob.getText()          +
					"',Depot           ='" + combo1.getSelectedItem() +
					"',To              ='" + combo2.getSelectedItem() +
					
					"' WHERE Pass_No ='"+ text1.getText()+ "'";
					System.out.print(text1.getText());
                    int result = statement.executeUpdate( temp );
				     
				      dispose();
					}
				 
				}
				
				 catch ( SQLException sqlex ) {
                      sqlex.printStackTrace();
             }
				
			}
		});
			
	  
	button3.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent e) {
	int DResult = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete Record?");

	if (DResult == JOptionPane.NO_OPTION) {
	   JOptionPane.showMessageDialog(null,"Deletion Cancel",
       "DELETION",JOptionPane.DEFAULT_OPTION);
	} 		 
	
	 if (DResult == JOptionPane.YES_OPTION)
           {            
              try
              {
                    Statement statement =con.createStatement();
                     if (!text1.equals(""))
                        {
						System.out.print(text1.getText());
                              String query =("DELETE  FROM Passengers where Pass_No ='" + text1.getText() + "'");
                                   int result = statement.executeUpdate( query );

                                   if ( result == 0 )
                                 {
                                         JOptionPane.showMessageDialog(null,"Record Deleted",
                                        "DELETION",JOptionPane.DEFAULT_OPTION);
                                 }

                               else

                            {
                               text1.setText("");
                               text2.setText("");
                               text3.setText("");
                               text4.setText("");
                               
                            }
                             statement.close();
                       }

                        
            }

             catch ( SQLException sqlex )

             {
                     sqlex.printStackTrace(); 
             }
          }
		}
	 });
	 button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text1.setText ("");
				text2.setText ("");
				text3.setText ("");
				text4.setText ("");	
			}
		});	
		button5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
  }
	public void showRecords()
	{
		try
                     {
                       if ( !text1.equals("") )
                       {

                              Statement statement =con.createStatement();
                              String query =("SELECT * FROM Passengers where Pass_No ='" + text1.getText() + "'");                                 
                                 ResultSet rs = statement.executeQuery( query );
								 if (rs.next())
								 {
									 t1 = rs.getString(1);
									 t2 = rs.getString(2);
									 t3 = rs.getString(3);
									 t4 = rs.getString(4);
									 t5 = rs.getString(5);
									 t6 = rs.getString(6);
									 t7 = rs.getString(7);


									 text1.setText(t1);
									 text2.setText(t2);
									 text3.setText(t3);
									 text4.setText(t4);
									 dob.setText(t5);
									 combo1.setSelectedItem(t6);
									 combo2.setSelectedItem(t7);
								 }

								 else
								 {
									 JOptionPane.showMessageDialog(null, "Record Not found !", "Sorry !",
									 JOptionPane.INFORMATION_MESSAGE);
								 }
                                 statement.close();
                        }    
                     }

                     catch ( SQLException sqlex )

                     {
                             sqlex.printStackTrace();
							 JOptionPane.showMessageDialog(null, "Please Check Passenger No !", "Sorry !",
										JOptionPane.ERROR_MESSAGE);
                     }
				setVisible(true);		
			
	}
	private void setCbx()
	{
		try
		{
			ResultSet rst = con.createStatement().executeQuery("SELECT Depot,Destination FROM Route");
			while (rst.next())
			{
				combo1.addItem(rst.getString(1));
				combo2.addItem(rst.getString(2));
			}
		}
		catch (Exception n)
		{
			System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"); n.printStackTrace();
		}			
	}	
	public static void main(String[]args){
		JFrame.setDefaultLookAndFeelDecorated(true);
		new UpdatePassenger(null).setVisible(false);
	}
	
}