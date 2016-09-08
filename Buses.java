import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
class Buses extends JFrame implements ActionListener
{
	JTable tbSalesView;
	DefaultTableModel dtm;
	JScrollPane jsp;
	JComboBox chDate, chDate1;
	Connection con;
	ResultSet rs1, rs2;//Bus RegNo" + "\t\t" + "Model" + "\t\t" + "Capacity" + "\t\t" + "Insurance_Status\t" + "Expiry Date
	String head2[] ={ " Bus RegNo "," Bus No", " Model ", " Capacity ", "Date Bought", " Insurance_Status ","Date Insured" ," Expiry Date " };
	ImageIcon store[] = new ImageIcon[3];
	MainWindow parent;	
	int total = 0, count = 0, d;
	//-------------------------------------------------------------------------------------------------
	JLabel lbl_search;
	JButton cmd_Update,cmd_show, cmd_delete, cmd_pend, cmd_next, cmd_prev, cmd_search, cmd_close, cmd_print, AddNew, jButton3;
	JTextField txt_search;
	Calendar cal;
	String cdate = "";
	boolean ps = false;
	//-------------------------------------------------------------------------------------------------
	public Buses(MainWindow parent)
	{		
		this.parent = parent;
	
			setLayout(null);
	
			dtm = new DefaultTableModel();
			dtm.setColumnIdentifiers(head2);
			tbSalesView = new JTable(dtm);
			jsp = new JScrollPane(tbSalesView);
			jsp.setBounds(20, 9, 750, 400);
			add(jsp);
			tbSalesView.setRowHeight(20);//Bus RegNo" + "\t\t" + "Model" + "\t\t" + "Capacity" + "\t\t" + "Insurance_Status\t" + "Expiry Date
			tbSalesView.getColumn(" Bus RegNo ").setMaxWidth(80);
			tbSalesView.getColumn(" Capacity ").setMaxWidth(80);
			//tbSalesView.getColumn(" Name ").setMaxWidth(500);
			//tbSalesView.getColumn(" Middle Name ").setMaxWidth(500);
			//tbSalesView.getColumn(" Surname ").setMaxWidth(500);
			setTitle("Bus Details");

		AddNew = new JButton("Add New", new ImageIcon("INF/contents.png"));
		AddNew.setBounds(100, 430, 110, 20);
		add(AddNew);
		AddNew.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new AddNewBus();
			}
		});

		jButton3 = new JButton("REFRESH", new ImageIcon("INF/reload.png"));
		jButton3.setBounds(340, 430, 110, 20);
		add(jButton3);
		jButton3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				showRecords();
			}
		});

		cmd_Update = new JButton("Update", new ImageIcon("INF/log users.png"));
		cmd_Update.setBounds(220, 430, 110, 20);
		add(cmd_Update);
		cmd_Update.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(tbSalesView.getSelectedRow()>0)
				{
					//release(); 
					String value = (String)dtm.getValueAt(tbSalesView.getSelectedRow(), 1);
					new UpdateBuses(value);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Select Valid Bus to Update!",
									"No bus found!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		cmd_close = new JButton("Close",new ImageIcon("INF/cancel.png"));
		cmd_close.setBounds(580, 430, 100, 20);
		add(cmd_close);
		cmd_close.addActionListener(this);
		
		cmd_delete = new JButton("Delete",new ImageIcon("INF/DELETE.gif"));
		cmd_delete.setBounds(460, 430, 100, 20);
		add(cmd_delete);
		cmd_delete.addActionListener(new java.awt.event.ActionListener() 
		{
			public void actionPerformed(java.awt.event.ActionEvent e) 
			{				
				if(tbSalesView.getSelectedRow()>0)
				{
					String value = (String)dtm.getValueAt(tbSalesView.getSelectedRow(), 1);
					int DResult = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete Record?");
					if (DResult == JOptionPane.NO_OPTION) 
					{
						JOptionPane.showMessageDialog(null,"Deletion Cancel", "DELETION",JOptionPane.DEFAULT_OPTION);
					}
					if (DResult == JOptionPane.YES_OPTION)
					{   
					try{					
						Statement statement =con.createStatement();
							System.out.print((String)dtm.getValueAt(tbSalesView.getSelectedRow(), 1));
							String query =("DELETE  * FROM Buses where BusNo ='" + value+ "'");
							int result = statement.executeUpdate( query );
							if ( result == 1 )
							{
								JOptionPane.showMessageDialog(null,"Record Deleted",
								"DELETION",JOptionPane.DEFAULT_OPTION);
								showRecords();
							}
							statement.close();
						}catch ( SQLException sqlex )
						{	
							sqlex.printStackTrace(); JOptionPane.showMessageDialog(null, "Deletion Failed !",
									"Sorry !", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Select Valid Bus to delete!",
									"No bus found!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
				
		createCon();
		showRecords();
		setBounds(154, 135, 800, 500);
		setVisible(true);

		WA wa = new WA();
		addWindowListener(wa);
		setResizable(false);

	}
		
	public void release()
	{
		parent.lock = false; 
	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == cmd_close)
		{
			parent.lock = false; dispose();
		}	
	}
	//----------------------------------------------------------------------------------------	
	public void createCon()
	{
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
	}
	//----------------------------------------------------------------------------------------	
	public void showRecords()
	{
		dtm.setRowCount(0);
		System.gc();
		try
		{
			createCon();
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet r1,r2;
			r1 = st.executeQuery("select * from Buses");
			String[] t = new String[8];
			while (r1.next())
				{
					t[0] = r1.getString("Bus_RegNo");
					t[1] = r1.getString("BusNo");
					t[2] = r1.getString("Model");//"Model"
					t[3] = r1.getString("Capacity");//"Capacity"
					t[4] = r1.getString("DateBought");
					t[5] = r1.getString("insurance_Status");//"insurance_Status"
					t[6] = r1.getString("Date_Insured");
					t[7] = r1.getString("Insurance_Expiry");//"Insurance_Expiry"
					dtm.addRow(t);					
				}
				
			}		
		catch (Exception e)
		{
			System.out.println("Error :" + e); //e.printStackTrace();
		}
	}
	
	//------------------------------------------------------------------------------------------------	
	class WA extends WindowAdapter
	{
		public void windowClosing(WindowEvent we)
		{
			 parent.lock = false; 
			System.gc();
			dispose();
		}
	}

}

