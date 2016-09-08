import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class LoginWindow extends JFrame implements ActionListener
{
	JLabel		error,login,pic,name,password,photo,lblPerson;
	JTextField		txt_name;
	JPasswordField 	txt_password;
	JComboBox cmbo_Person;
	ImageIcon	[]image=new ImageIcon[5];
	JButton		cmd_back,cmd_ok,cmd_login,cmd_cancel;
	JButton create;
	Connection	con;
	String database = "";
	String n="", p="";
	public LoginWindow()
	{
		setLayout(null);		
		setDefaultLookAndFeelDecorated(true);
		setTitle("Travels Booking System ");
		image[4] = new ImageIcon("INF//row_canoe.gif"); 
		login=new JLabel("    Login");
		login.setBounds(100,25,250,50);
		add(login);
		login.setForeground(Color.red);
		login.setIcon(image[4]);
		login.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	
		name=new JLabel("LoginId");
		name.setBounds(100,80,50,50);
		add(name);
		name.setForeground(Color.blue);	

		txt_name=new JTextField("Ajinkya");
		txt_name.setBounds(180,100,120,20);
		add(txt_name);
		txt_name.addActionListener(this);
		//txt_name.addKeyListener(this);

		password=new JLabel("Password");
		password.setBounds(100,140,100,20);
		add(password);
		password.setForeground(Color.blue);

		txt_password=new JPasswordField("password");
		txt_password.setBounds(180,140,120,20);
		add(txt_password);
		txt_password.addActionListener(this);

		lblPerson = new JLabel("Login As");
		lblPerson.setBounds(100,185, 150, 20);
		add(lblPerson);
		lblPerson.setForeground(Color.blue);

		cmbo_Person=new JComboBox();
		cmbo_Person.addItem("Manager");
                cmbo_Person.addItem("College");
				 cmbo_Person.addItem("Employee");

		cmbo_Person.setBounds(180,185,120,18);
		add(cmbo_Person);

		image[2] = new ImageIcon("INF/images3.jpg");		//D:/jdk1.5/Doctor+/
		photo=new JLabel("");
		photo.setBounds(50,20,500,300);
		//add(photo);
		photo.setIcon(image[2]);

		cmd_login=new JButton("Login");
		cmd_login.setBounds(100,230,80,20);
		add(cmd_login);
		cmd_login.addActionListener(this);

		cmd_cancel = new JButton("Cancel");
		cmd_cancel.setBounds(220, 230, 80, 20);
		add(cmd_cancel);
		cmd_cancel.addActionListener(this);


		JScrollPane jsp = new JScrollPane();
		jsp.setBounds(50, 80, 300, 200);
		add(jsp);

		create = new JButton("Create New Acount");
		create.setBounds(50, 290, 180, 20);
		add(create);
		create.setVisible(true);
		create.addActionListener(this);

		cmd_back = new JButton("Cancel");
		cmd_back.setBounds(250, 290, 80, 20);
		add(cmd_back);
		cmd_back.addActionListener(this);
		cmd_back.setVisible(false);		


		cmd_ok=new JButton("Ok");
		cmd_ok.setBounds(280,330,80,20);
		add(cmd_ok);
		cmd_ok.addActionListener(this);
		cmd_ok.setVisible(false);		

		error=new JLabel("");
		error.setBounds(20,315,350,50);
		add(error);
		error.setForeground(Color.red);

		image[1] = new ImageIcon("INF/notebox.jpg");	//D:/jdk1.5/Doctor+/	
		pic=new JLabel("");
		pic.setBounds(10,20,500,300);
		add(pic);
		pic.setIcon(image[1]);

		paintComponents(getGraphics());
		
		setBounds(250,150,400,400);		
		
		WA wa=new WA();
		addWindowListener(wa);
		setResizable(false);			   
		setIconImage(new ImageIcon("INF/Icon.png").getImage());		
		setVisible(true);
		makeConnection();
	}
	
	public void makeConnection()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");			
			database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
			database += "Travels.mdb" + ";DriverID=22;READONLY=true}";
			//now we can get connection from DriverManager
			con = DriverManager.getConnection(database, "", "");

		}
		catch (Exception e)
		{ System.out.println("Error in making Connection !"); }
	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == cmd_back)
		{
			create.setVisible(false);
			cmd_back.setVisible(false);
			cmd_login.setVisible(true);
			cmd_cancel.setVisible(true);
		}
		if (ae.getSource() == cmd_cancel)
		{
			System.exit(0);
		}
		if (ae.getSource() == cmd_ok)
		{
			cmd_login.setEnabled(true);
			txt_name.setText("");
			txt_password.setText("");
			cmd_ok.setVisible(false);
			error.setText(" ");
		}

		if(ae.getSource()==txt_name)
		{
			txt_password.requestFocus();
		}
		if(ae.getSource()==txt_password)
		{
			cmd_login.requestFocus();
		}
		if (ae.getSource() == create)
		{
			n = txt_name.getText();
			p = txt_password.getText();
			if (n.equals("") || p.equals(""))
			{
				cmd_ok.setVisible(true); cmd_ok.requestFocus(); cmd_login.setEnabled(false); error.setText("Please Check Your Id & Password !");
			}
			else
			{
				    String ObjButtons[] = { "Yes", "No" };
					int PromptResult = JOptionPane.showOptionDialog(null, "Are you Sure make this Account ?",
					"Warning !", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
					if (PromptResult == 0)
					{
						try
						{
							Statement stcheck = con.createStatement();
							ResultSet rscheck = stcheck.executeQuery("select * from Users where category='" + cmbo_Person.getSelectedItem() + "' AND username='" + n + "' ");
							if (rscheck.next())
							{
								JOptionPane.showMessageDialog(null, "Username Already Exists", "Sorry", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
							Statement stn = con.createStatement();
							stn.executeUpdate("insert into Users values('"+cmbo_Person.getSelectedItem()+"','" + n + "','" + p + "')");
							JOptionPane.showMessageDialog(null, "New Account Created", "Done", JOptionPane.INFORMATION_MESSAGE);
							txt_name.setText("");
							txt_password.setText("");
							create.setVisible(false);
							cmd_login.setText("Login");
							cmd_login.setVisible(true);
							cmd_cancel.setVisible(true);
							cmd_back.setVisible(false);
							}
						}
						catch (Exception e)
						{ }
					}					
			}
		}
		if (ae.getSource() == cmd_login || ae.getSource() == txt_password)
		{
			if (cmd_login.getText().equals("Cancel"))
			{
				create.setVisible(false);
				cmd_login.setText("Login");
				txt_name.setText("");
				txt_password.setText("");
			}
			else
			{				
				n = txt_name.getText();
				p = txt_password.getText();
				if (n.equalsIgnoreCase("admin") && p.equalsIgnoreCase("jack202") && cmbo_Person.getSelectedItem().equals("Manager"))
				{
					txt_name.setText("");
					txt_password.setText("");
					create.setVisible(true);
					cmd_cancel.setVisible(false);
					cmd_back.setVisible(true);
					//cmd_ok.setText("Cancel");
					//cmd_ok.setVisible(true);
					cmd_login.setVisible(false);
				}
				else
				{
					try
					{
						ResultSet rs;
						Statement st = con.createStatement();
						rs = st.executeQuery("select * from Users where category='" + cmbo_Person.getSelectedItem() + "' AND username='" + n + "' AND password='" + p + "'");						
						if (rs.next())
						{
							setVisible(false); rs.close(); st.close();						
							MainWindow m = new MainWindow();
						}
						else
						{
							cmd_ok.setVisible(true); cmd_ok.requestFocus(); cmd_login.setEnabled(false); error.setText("               Please Check Your Id & Password !");
						}
					}
					catch (Exception e)
					{
						System.out.println("Error in creating DSN [Login]:" + e);
						e.printStackTrace();
					}
				}
			}
		}
	}

		class WA extends WindowAdapter
		{
			public void windowClosing(WindowEvent we)
			{
				dispose();System.exit(-1);
			}
		}

}
class Load
{
	public static void main(String cp[])
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		splashLoad sp = new splashLoad();
		LoginWindow l=new LoginWindow ();
	}
	
}