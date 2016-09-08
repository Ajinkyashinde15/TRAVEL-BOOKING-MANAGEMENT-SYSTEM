import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
class Routes extends JFrame implements ActionListener
{
	JTable tbSalesView;
	DefaultTableModel dtm;
	JScrollPane jsp;
	JComboBox chDate, chDate1;
	Connection con;
	ResultSet rs1, rs2;
	String head2[] ={ " Route No ", " Route Name", " Depot ", " Destination ", "Distance", " Fare " };
	ImageIcon store[] = new ImageIcon[3];
	MainWindow parent;
	int total = 0, count = 0, d;
	//-------------------------------------------------------------------------------------------------
	JLabel lbl_search;
	JButton cmd_show, cmd_Update, cmd_close, cmd_print, cmd_Delete, jButton1, jButton3;
	JTextField txt_search;
	Calendar cal;
	String cdate = "";
	boolean ps = false;
	boolean call = false;
	//-------------------------------------------------------------------------------------------------
	public Routes()
	{
		showFrame();
		System.out.println("Into Routes 1");
	}
	public Routes(MainWindow parent)
	{
		this.parent = parent;
		showFrame();
		call = true;
	}
	public void showFrame()
	{
		System.out.println("Into routes");
		setLayout(null);
		createCon();
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(head2);
		tbSalesView = new JTable(dtm);
		jsp = new JScrollPane(tbSalesView);
		jsp.setBounds(20, 9, 750, 400);
		add(jsp);
		tbSalesView.setRowHeight(20);
		setTitle("Route Details");

		jButton1 = new JButton("Add New", new ImageIcon("INF/contents.png"));
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

		jButton1.setBounds(100, 430, 110, 20);
		add(jButton1);
		jButton1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new AddNewRoute().setVisible(true);
				showRecords();
			}
		});
		
		cmd_Delete = new JButton("Delete", new ImageIcon("INF/delete.gif"));
		cmd_Delete.setBounds(460, 430, 110, 20);
		add(cmd_Delete);
		cmd_Delete.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e) 
			{
				try{
						String s= (String)dtm.getValueAt(tbSalesView.getSelectedRow(), 0);
						int value =Integer.parseInt(s);
						int DResult = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete Record?");
						if (DResult == JOptionPane.NO_OPTION) 
						{
							JOptionPane.showMessageDialog(null,"Deletion Cancel", "DELETION",JOptionPane.DEFAULT_OPTION);
						}
						if (DResult == JOptionPane.YES_OPTION)
						{   
							Statement statement =con.createStatement();
							System.out.print((String)dtm.getValueAt(tbSalesView.getSelectedRow(), 0));
							String query =("DELETE  * FROM Route where Route_No =" + value);
							int result = statement.executeUpdate( query );
							if ( result == 1 )
							{
								JOptionPane.showMessageDialog(null,"Record Deleted",
								"DELETION",JOptionPane.DEFAULT_OPTION);
								showRecords();
							}
							statement.close();
						}
					}catch(Exception ea)
					{
						JOptionPane.showMessageDialog(null, "Select Valid Route to delete!",
								"No Route found!", JOptionPane.ERROR_MESSAGE);
					}
			}
		});

		cmd_close = new JButton("Close", new ImageIcon("INF/cancel.png"));
		cmd_close.setBounds(580, 430, 110, 20);
		add(cmd_close);
		cmd_close.addActionListener(this);


		cmd_Update = new JButton("Update", new ImageIcon("INF/log users.png"));
		cmd_Update.setBounds(220, 430, 110, 20);
		add(cmd_Update);
		cmd_Update.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (call == true) { release(); }
				
				try
				{
					String s= (String)dtm.getValueAt(tbSalesView.getSelectedRow(), 0);
					int value =Integer.parseInt(s);
					new UpdateRoute(value);
					dispose();
				}
				catch(Exception ea)
				{
					JOptionPane.showMessageDialog(null, "Select Valid Route to Update!",
									"No Route found!", JOptionPane.ERROR_MESSAGE);
					ea.printStackTrace();
				}
			}
		});







	/*	cmd_Update = new JButton("Update", new ImageIcon("INF/log users.png"));
		cmd_Update.setBounds(220, 430, 110, 20);
		add(cmd_Update);
		cmd_Update.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			String s= (String)dtm.getValueAt(tbSalesView.getSelectedRow(), 0);
						int value =Integer.parseInt(s);
						System.out.println(value);
				new UpdateRoute(value);
				
				if (call == true) { release(); }
				dispose();
			}
		});
		
	*/	
		showRecords();
		setBounds(154, 135, 800, 500);
		setVisible(true);

		WA wa = new WA();
		addWindowListener(wa);
		setResizable(false);

	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == cmd_close)
		{
			if (call == true) { parent.lock = false; } dispose();
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
	public void release() { parent.lock = false; }
	public void showRecords()
	{
		dtm.setRowCount(0);
		System.gc();
		try
		{
			createCon();
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet r1, r2;
			r1 = st.executeQuery("select * from Route");
			String[] t = new String[6];
			while (r1.next())
			{
				t[0] = r1.getString("Route_No");
				t[1] = r1.getString("RouteName");
				t[2] = r1.getString("Depot");
				t[3] = r1.getString("Destination");			
				t[4] = r1.getString("Distance");
				t[5] = r1.getString("Fare_Charged");				
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
			if (call == true) { parent.lock = false; }
			System.gc();
			dispose();
		}
	}

}

