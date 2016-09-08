import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.text.*;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.text.*;
import java.util.Date;


class Receipt extends JFrame {
	public Container content;
	public JPanel reportingPanel;
	public JTabbedPane listsTabs;
	public JPanel chartPanel;
	public JButton hide;
	public JTextArea listPane;
	public JPanel reportPanel;
	public JButton drawGraphButton;
	Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
	public int ID;
	public Color skyblue=new Color(150,190,255);
	public 	final ImageIcon imageIcon = new ImageIcon("Icon/header/cool.png");
	private static Connection dbcon = null;
	private JButton print,cancel;
	private JPanel panel;	

	public ReciptDetail rd;
    Statement stmt = null;
	Connection con;
	 public Receipt()
	 {
	 	
	 	super("Receipt");
		rd=new ReciptDetail();
		content=getContentPane();
		content.setBackground(skyblue);
		
		print=new JButton("PRINT ",new ImageIcon("INF/prints.png"));
		cancel=new JButton("CANCEL",new ImageIcon("INF/exit.png"));
		panel=new JPanel();
		panel.add(print);
		panel.add(cancel);
		reportingPanel=new JPanel();
		reportingPanel.setLayout(new BorderLayout());
		reportingPanel.setBorder(BorderFactory.createEtchedBorder());
		reportingPanel.add(new JLabel("Receipt for Payment"),BorderLayout.NORTH);
		reportingPanel.add(panel,BorderLayout.SOUTH);
		reportPanel=new JPanel();
		reportPanel.setLayout(new GridLayout(1,1));
		reportPanel.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.blue));
		reportPanel.setBackground(Color.white);
		
		reportingPanel.add(new JScrollPane(reportPanel),BorderLayout.CENTER);
	    
		
	 
    	produceCertificate();
		listPane.setEditable(false);
		listPane.setFont(new Font("Serif", Font.PLAIN, 16));
		listPane.setForeground(Color.black);
	
		listPane.setLineWrap(true);
		listPane.setWrapStyleWord(true);
		reportPanel.add(listPane);
		setLocation((screen.width-1270)/2,((screen.height-740)/2));
		setResizable(false);
         
		JPanel dpanel=new JPanel();
		dpanel.setBorder(BorderFactory.createLoweredBevelBorder());
		dpanel.setLayout(new GridLayout(1,1));
		DateFormat defaultDate=DateFormat.getDateInstance(DateFormat.FULL);
      	content.add(reportingPanel,BorderLayout.CENTER);

      	setLocation(5,0);
      	setSize(780,700);
      	setVisible(true);
		
	 }
	 public void produceCertificate()
	 {
	 	listPane = new JTextArea() {
      Image image = imageIcon.getImage();
      {
        setOpaque(false);
      } 

      public void paint(Graphics g) {
        g.drawImage(image, 340, 30, this);
        g.setColor(Color.blue);
		String num, name, seat, amt, payon;
        g.drawString("PAYMENT RECEIPT",365,200);
		g.drawString("Payment Number      ", 80, 230);//+new Payment().text1.getText()
		g.drawString("Name of Passenger   ", 80, 260); //+new Payment().combo2.getSelectedItem()
		g.drawString("Seat No   ", 80, 290); //+new Payment().combo2.getSelectedItem()
		g.drawString("Amount Paid    ", 80, 320);//+new Payment().combo8.getSelectedItem()
		g.drawString("Pay on              ", 80, 350);//+new Payment().combo4.getSelectedItem()
		g.drawString("Date Paid           ", 620, 380);//+new Payment().p_date.getText()
		g.drawString("Received By         ", 80, 410);//+new Payment().combo3.getSelectedItem()
       
        g.setColor(Color.red);
		g.drawString(" * * * * * Have A Safe Journey * * * * * ", 200, 410);
       
      
        super.paint(g);
      }
    };
	 }
	
	
	 public static void main(String [] args)
	 {
	     Receipt r=new  Receipt();
		 r.setVisible(true);
		 r.setSize(800,800);
	 	
	 }
	
		 
	 
}

































































































		//g.drawString("Phone: +254 720576879: Cellphone: 0720576879",385,70);
		//g.drawString("Fax: +254 720576879 ",385,90);
		//g.drawString("Address: Box 6046-20100, Nakuru, Kenya ",385,110);
		//g.drawString("Email:rvs@gmail.com",385,140);
		//g.drawString("Website:www.rvs.co.ke",385,170);
		//g.setColor(Color.black);