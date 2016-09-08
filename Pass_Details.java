import java.sql.*;
import javax.swing.*;
import java.util.Date;
import java.text.*;
import java.awt.*;
import java.awt.event.*;

public class Pass_Details extends JFrame
{
	public Container content;
	public JPanel reportingPanel;
	public JTabbedPane listsTabs;
	public JTextArea listPane;
	public JPanel reportPanel;
	public static final int SET_SIZE = 0, PRINT = 1, CLOSE = 2;
	public JPanel statusPanel;
	public JComboBox graphTypesCombo;
	public Color skyblue = new Color(150, 190, 255);
	public final ImageIcon imageIcon = new ImageIcon("Icon/header/cool.png");
	private static Connection dbcon = null;
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	Statement stmt = null;
	private JButton print, cancel;
	private JPanel panel;
	Connection con;
	public Pass_Details()
	{

		super("Passanger Report");

		content = getContentPane();
		content.setBackground(skyblue);
		listsTabs = new JTabbedPane();
		//print = new JButton("PRINT ", new ImageIcon("Icon/i16x16/prints.png"));
		cancel = new JButton("CANCEL", new ImageIcon("Icon/i16x16/exit.png"));
		panel = new JPanel();
		//panel.add(print);
		panel.add(cancel);

		reportingPanel = new JPanel();
		reportingPanel.setLayout(new BorderLayout());
		reportingPanel.setBorder(BorderFactory.createEtchedBorder());
		reportingPanel.add(new JLabel("Pass Report"), BorderLayout.NORTH);
		reportingPanel.add(panel, BorderLayout.SOUTH);

		reportPanel = new JPanel();
		reportPanel.setLayout(new GridLayout(1, 1));
		reportPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.blue));
		reportPanel.setBackground(Color.white);

		reportingPanel.add(new JScrollPane(reportPanel), BorderLayout.CENTER);
		listsTabs.add(reportingPanel);
		setLocation((screen.width - 1270) / 2, ((screen.height - 740) / 2));
		setResizable(false);

		listPane = new JTextArea()
		{
			Image image = imageIcon.getImage();
			{
				setOpaque(false);
			}

			public void paint(Graphics g)
			{
				g.drawImage(image, 340, 30, this);
				g.setColor(Color.blue);

				g.drawString("Phone:02183233177: Cellphone: 9765265855", 385, 70);
				
				g.drawString("Fax: +000000000 ", 385, 90);
				
				g.drawString("Address: Solapur, Maha, India ", 385, 110);
				
				g.drawString("Email:ajinkyashinde15@gmail.com", 385, 140);
				g.setColor(Color.black);
				super.paint(g);
			}
		};


		listPane.setEditable(false);
		listPane.setFont(new Font("Serif", Font.BOLD, 12));
		listPane.setForeground(Color.black);

		listPane.setLineWrap(true);
		listPane.setWrapStyleWord(true);
		reportPanel.add(listPane);


		try
		{
			makeConnection();
			Statement s = con.createStatement();
		}
		catch (Exception excp)
		{
			excp.printStackTrace();
		}
		printList();



		content.add(listsTabs, BorderLayout.CENTER);

		cancel.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				dispose();

			}
		});

		setResizable(false);
		setSize(1000, 720);
		setVisible(true);


		//WA wa=new WA();
		///addWindowListener(wa);
		setResizable(false);			   
		setIconImage(new ImageIcon("INF/Icon.png").getImage());		
		setVisible(true);
		//makeConnection();

	//	setLocation((screen.width - 500)/2,((screen.height-350)/2));



	}

	public void makeConnection()
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
		{
			System.out.println("Error in making connection: " + e);
		}
	}
	private void printList()
	{
		try
		{

			ResultSet rst = con.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE).executeQuery("select Pass_No,Pass_Name,Address,Tel_No,Date_of_Travel from Passengers");


			listPane.append("\n\n\n\n\n\n\n\n\n\n\n");
			listPane.append("     Pass_No" + "\t\t" + "Pass_Name" + "\t\t" + "Address" + "\t\t" + "Tel_No" + "\tDate_of_Travel from Passengers\n");
			while (rst.next())
			{
				listPane.append("\n       ");
				listPane.append(rst.getString(1).trim());
				listPane.append("\t\t");
				listPane.append(rst.getString(2).trim());
				listPane.append("\t\t");
				listPane.append(rst.getString(3).trim());
				listPane.append("\t\t");
				listPane.append(rst.getString(4).trim());
				listPane.append("\t\t");
				listPane.append(rst.getString(5).trim());
				listPane.append("\n\n");
			}


			if (rst != null)
				rst.close();

		}
		catch (SQLException sqle)
		{
			JOptionPane.showMessageDialog(null, " No Records found"
							  + sqle.getMessage());
			return;
		}
	}




	public static void main(String[] args)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		Pass_Details s=new Pass_Details();
		s.setVisible(true);
	
	}

}
