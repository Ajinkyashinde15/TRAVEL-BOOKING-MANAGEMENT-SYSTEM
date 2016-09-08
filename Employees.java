import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
class Employees extends JFrame implements ActionListener
{
	JTable tbSalesView;
	DefaultTableModel dtm;
	JScrollPane jsp;
	JComboBox chDate, chDate1;
	JButton AddNew, jButton3, cmd_Update, cmd_Delete;
	Connection con;
	ResultSet rs1, rs2;
	String head2[] ={ " Emploee No ", " Name ", " Gender ", " Date Of Birth ", "Designation", " Contact ", "E-Mail", " Address " };
	ImageIcon store[] = new ImageIcon[3];
	MainWindow parent;
	int total = 0, count = 0, d;
	//-------------------------------------------------------------------------------------------------
	JLabel lbl_search;
	JButton cmd_show, cmd_pend, cmd_next, cmd_prev, cmd_search, cmd_close, cmd_print;
	JTextField txt_search;
	Calendar cal;
	String cdate = "";
	boolean ps = false;
	boolean callp = false;
	//-------------------------------------------------------------------------------------------------
	public Employees()
	{ showFrame(); }
	public Employees(MainWindow parent)
	{
		this.parent = parent;
		showFrame();
		callp = true;
	}
	public void showFrame()
	{
		setLayout(null);

		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(head2);
		tbSalesView = new JTable(dtm);
		jsp = new JScrollPane(tbSalesView);
		jsp.setBounds(20, 9, 750, 400);
		add(jsp);
		tbSalesView.setRowHeight(20);
		setTitle("Employee Details");

		AddNew = new JButton("ADD NEW", new ImageIcon("INF/contents.png"));
		AddNew.setBounds(100, 430, 110, 20);
		add(AddNew);
		AddNew.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new AddNewEmployee(); parent.lock = false; setVisible(false); dispose();
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
				if (callp == true) { release(); }
				
				try
				{
					String s= (String)dtm.getValueAt(tbSalesView.getSelectedRow(), 0);
					System.out.print(s);
					int value =Integer.parseInt(s);
					new UpdateEmployee(value);
					dispose();
				}
				catch(Exception ea)
				{
					JOptionPane.showMessageDialog(null, "Select Valid Employee to Update!",
									"No employee found!", JOptionPane.ERROR_MESSAGE);
				}
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
							String query =("DELETE  * FROM Employee where EmpNo =" + value);
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
						JOptionPane.showMessageDialog(null, "Select Valid Employee to delete!",
								"No Employee found!", JOptionPane.ERROR_MESSAGE);
					}
			}
		});

		cmd_close = new JButton("Close", new ImageIcon("INF/cancel.png"));
		cmd_close.setBounds(580, 430, 110, 20);
		add(cmd_close);
		cmd_close.addActionListener(this);

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
			if (callp == true) { parent.lock = false; }
			dispose();
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
			ResultSet r1, r2;
			r1 = st.executeQuery("select * from Employee");
			String[] t = new String[8];
			while (r1.next())
			{
				t[0] = r1.getString("empNo"); String name = r1.getString("Fname") + " " + r1.getString("Mname") + " " + r1.getString("Lname");
				t[1] = name + "";
				t[2] = r1.getString("Gender");
				t[3] = r1.getString("DOB");
				t[4] = r1.getString("Designation");
				t[5] = r1.getString("Telephone");
				t[6] = r1.getString("E_Mail");
				t[7] = r1.getString("Address");
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
			if (callp == true) { parent.lock = false; }
			System.gc();
			dispose();
		}
	}

}

