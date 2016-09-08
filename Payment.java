import java.applet.*;
import java.awt.*;
import java.text.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.io.*;
import java.sql.*;
import javax.swing.plaf.metal.*;
import java.util.*;
import java.text.*;
import java.io.*;
import java.awt.PrintJob.*;

public class Payment extends JFrame
{
	private JLabel label1,label2,label3,label4,label5,label6,label7;
	public JTextField text1,text4,text5,text6,text7;
	public JComboBox combo1,combo2,combo3,combo4,combo5,combo6,combo7,combo8;
	private JButton button1,button2,button3,button4,button5,button6;
	private JPanel panel1,pane,panel3;
	Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
	public DateButton p_date;
	String payNo,pasNo,pasName,mode,dt,amount,rev;
	Connection con;

	public Payment()
	{
		super ("Payment Process");
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
		label1=new JLabel("Paymnent Number");
		label2=new JLabel("Passenger Number");
		label3=new JLabel("Passenger Name");
		label6=new JLabel("Mode of Payment");
		label4=new JLabel("Date of Payment");
		label5=new JLabel("Amount Paid");
		label7=new JLabel("Received By");
		text1=new JTextField(10);
		text5=new JTextField(10);
		p_date=new DateButton();
		p_date.setForeground(Color.red);
		combo1=new JComboBox();
		combo2=new JComboBox();
		combo3=new JComboBox();
		combo4=new JComboBox();
		
		combo4.addItem("Cash"); // combo4=mode of payment
		combo8=new JComboBox();
		button1=new JButton ("PAY",new ImageIcon("INF/contents.png"));
		button2 = new JButton("PRINT RECEIPT", new ImageIcon("INF/prints.png"));
		button3 = new JButton("CANCEL", new ImageIcon("INF/exit.png"));
//		button4 = new JButton("SEARCH", new ImageIcon("INF/search.png"));
		button5 = new JButton("DELETE", new ImageIcon("INF/delete.png"));
		//combo3.addItem(new)
		panel1=new JPanel (new GridLayout(7,2));
		panel1.setPreferredSize(new Dimension(350,250));
		panel1.add(label1);panel1.add(text1);
		panel1.add(label2);panel1.add(combo1);
		panel1.add(label3);panel1.add(combo2);
		panel1.add(label6);panel1.add(combo4);
		panel1.add(label4);panel1.add(p_date);
		panel1.add(label5);panel1.add(combo8);
		panel1.add(label7);panel1.add(combo3); 


		fillCombo3();


		//combo8.removeAllItems();
		pane=new JPanel();
		pane.add(button1);
		
		pane.add(button2);
		pane.add(button3); 
//		pane.add(button4);
		
		panel3=new JPanel();
		panel3.add(panel1);
		panel3.add(pane);
		
		button2.setEnabled(false);
		add(panel3);
		setSize(500,350);
		setVisible(true);
		setCombo();
		setcbr();
		generator();
		setamount();
		setLocation(250,200);
		setResizable(false);
		combo1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			combo2.setSelectedIndex(combo1.getSelectedIndex());
				combo8.removeItem(combo8.getSelectedItem());
				setamount();
			}
		});
		
		combo2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			combo1.setSelectedIndex(combo2.getSelectedIndex());
			combo8.removeItem(combo8.getSelectedItem());
			setamount();
				}
		});
		
		button3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			setVisible(true);
				dispose();
				
			}
		});
		
			button2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) 
			{
				ReciptDetail rd=new ReciptDetail();
				rd.paymentno=text1.getText();
				rd.passengerno=combo1.getSelectedItem().toString();
				rd.passengername=combo2.getSelectedItem().toString();
				rd.modeofpay=combo4.getSelectedItem().toString();
				rd.dateofpay=p_date.getDate().toString().toString();
				rd.amtpaid=combo8.getSelectedItem().toString();
			
				System.out.println("combo8:"+rd.amtpaid);
				rd.recivedby=combo3.getSelectedItem().toString();
				
				try
				{
					String filename="G:\\NEw\\Travels\\backup\\"+rd.passengername+"_receipt.html";
					FileWriter fw=new FileWriter(new File(filename));
					HtmlHelper hh=new HtmlHelper();
					fw.write(hh.GetHtml(rd));
					fw.close();
					JOptionPane.showMessageDialog(null,"Receipt generated Successfully","Receipt",
					JOptionPane.DEFAULT_OPTION);
				}catch(Exception ea)
				{}
				button2.setEnabled(false);
			}
		});
		
		

		button1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
		     			
           if (combo1.getSelectedItem()==null){
           	JOptionPane.showMessageDialog(null,"All Passenger have paid","Travels Booking System",
            JOptionPane.DEFAULT_OPTION);
           return;}
           if (combo2.getSelectedItem()==null){
           	JOptionPane.showMessageDialog(null,"All Passenger have paid","Travels Booking System",
            JOptionPane.DEFAULT_OPTION);
            return;}
           generator();
           
           if(combo8.getSelectedItem()==null){
           	JOptionPane.showMessageDialog(null,"The Passenger has not been booked","ERROR",
           	JOptionPane.DEFAULT_OPTION);
           return;}
		   try
		   {
			   Statement statement = con.createStatement();
			   {


				   String temp = "UPDATE Passengers SET Pay_Status='Paid'" +
					   "WHERE Pass_NO LIKE  '" + combo1.getSelectedItem() + "'";
				   int result = statement.executeUpdate(temp);

			   }

		   }

		   catch (SQLException sqlex)
		   {
			   sqlex.printStackTrace();
		   }
		   String name = combo2.getSelectedItem() + ""; String num = combo1.getSelectedItem() + "";
			try{
			Statement statement =con.createStatement();			
			
					String temp = "INSERT INTO Payment (Payment_No, Pass_No, Pass_Name, Payment_Mode, Date_Payment,Amount_Paid,Received_By) VALUES ('"+
                                                    
                                              text1.getText() 	           + "', '" +  
			   							   	  combo1.getSelectedItem()     + "', '" +  
			   							   	  combo2.getSelectedItem()	   + "', '" +  
			   							   	  combo4.getSelectedItem()	   + "', '" + 
			   							   	  p_date.getText()	           + "', '" +  
		 	   							   	  combo8.getSelectedItem()     + "', '" +  
			   							   	  combo3.getSelectedItem() 	   + "')";
								
				 
				        int result = statement.executeUpdate( temp );
                        JOptionPane.showMessageDialog(null,"Passenger Account updated","Travels Booking System",
                        JOptionPane.DEFAULT_OPTION);
                        
			
				}
				catch ( SQLException sqlex )

                      {
                         sqlex.printStackTrace();
                         
                      }
					 // combo2.removeItem(name); System.out.println("Removed Name");
					 // combo1.removeItem(num); System.out.println("Removed No");
					  
                button1.setEnabled(false);
                button2.setEnabled(true);
			}
		});
/*		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
                     {
                       if ( !text1.equals("") )
                       {

                              Statement statement =con.createStatement();
                              String query =("SELECT * FROM Payment where Payment_No ='" + text1.getText() + "'");
                                 
                                 ResultSet rs = statement.executeQuery( query );
                                    display ( rs );
                                 statement.close();
                        }    
                     }

                     catch ( SQLException sqlex )

                     {
                             sqlex.printStackTrace();
                     }
				setVisible(true);
				
			}
		});*/
		
	}
	
       public static void main(String []args){
       JFrame.setDefaultLookAndFeelDecorated(true);
       	new Payment().setVisible(true);
    }

	private void fillCombo3()
	{
		try
		{
			Statement st=con.createStatement();
			String query="select username from Users";
			ResultSet rs=st.executeQuery(query);
		
			while(rs.next())
			{
				combo3.addItem(rs.getString("username"));
			}
		}
		catch(Exception ex)
		{
			System.out.println("Problem in loading users.");
			ex.printStackTrace();
		}
	}
	
    private void generator()
	{
		
		try
		{
		ResultSet rst=con.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT Payment_No FROM Payment");
			text1.setText("1000");
			while(rst.next())
			{
				String s;
				int number=rst.getInt(1);
				number=number+1;
				
				s=""+number;
				text1.setText(s);
			    int x;
			    
			}	
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}		
	}
   private void setCombo()
	{
	
		try
		{
			ResultSet rst = con.createStatement().executeQuery("SELECT Employee.empNo, Employee.Fname, Employee.Mname, Employee.Lname, Employee.Designation FROM Employee WHERE Employee.Designation='Booking Clerk'");//Emp.empNo, Emp.Sname, Emp.Fname, Emp.Lname, Emp.Designation
			while(rst.next())
			{
				combo3.addItem(rst.getString(2));			    
			}	
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}
			
			
	}
	private void setcbr()
	{
	
		try
		{
		ResultSet rst=con.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM Passengers where Booked_Status='Booked' and Pay_Status='Not_Paid'");
			while(rst.next())
			{
				combo1.addItem(rst.getString(1));
				combo2.addItem(rst.getString(2));
			    
			}	
		}
		catch(Exception n)
		{
			n.printStackTrace(); System.out.println("Error is here! !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
			
			
	}
	
	private void setamount()
	{
	
		try
		{
		ResultSet rst=con.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT Amount FROM BOOKING  where Pass_No='" + combo1.getSelectedItem() + "'");
			while(rst.next())
			{
				combo8.addItem(rst.getString(1));
				
			    
			}	
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}
			
			
	}
		public void display( ResultSet rs )
          {
             try
             { 
               boolean recordNumber = rs.next();
                if ( recordNumber)
                {   
                   payNo = rs.getString(1);
                   pasNo =rs.getString(2);
                   pasName = rs.getString(3);
                   mode = rs.getString(4);
                   dt = rs.getString(5);
                   amount = rs.getString(6);
                   rev = rs.getString(7);                   
                   
                   text1.setText(payNo);
                   combo1.setSelectedItem(pasNo);
                   combo2.setSelectedItem(pasName);
                   combo4.setSelectedItem(mode);
	               p_date.setText(dt);
	               combo8.setSelectedItem(amount); 
                   combo3.setSelectedItem(rev);
				   System.out.println("Records Displayed");
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
            			
}