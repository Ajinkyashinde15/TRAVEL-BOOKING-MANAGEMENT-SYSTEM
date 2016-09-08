
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.TextArea;

import javax.swing.JFrame;


public class Help  extends JFrame implements ActionListener
{

	JButton b1;
	JLabel l1;
	Font f,f1,f2;
	TextArea t1;
	String s;
	JScrollBar sc;
	// Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

	public Help()
	{
		JButton b1=new JButton("Exit.......!!!!!!",new ImageIcon("CANCEL.PNG"));
		add(b1);
		b1.setBounds(180, 430, 120, 20);
		//b1.setBounds(400, 700, 20, 10);
		b1.addActionListener(this);
		setTitle("Help");
	

		 Font f=new Font("Swis721 Blk BT",Font.BOLD,80);
		 setFont(f);

		s="                                       Windows Help             " +
		
		
				"\n\nIn this 'Travel Mnagment System' Projects we " +
				"should maintain a Booking of employee records,bus"+
				"details,rout details,etc.   \n" +
				"IN this Project we should be used a Frame for" +
				" windows in java \n" +
				"And Create a graphical user Interface using Swing " +
				"components using " +
				"javax package.\n" +
				
				"In the this software first we are provides a " +
				"MenuItem ADD. IN that menuitem " +
				"employee can be add new bus,route,passangerand also" +
				" add employee details as " +
				"well as we should be edit,delete,or update details.  \n"+
				
				"\nThen second menuitem is process i that menu we only " +
				"add employee records. \n"  +
				
				"\nIn tool menuitem there are calculator for calculating " +
				"payments and add " +
				"report using notepad application." +
				"Employee can see a india,world map for guides a passanger " +
				"this facility should " +
				"be include in that software.\n" +
				
				"Also user can search passanger also be a bus in the Travel " +
				"managment system.\n" +
				
				"Thanks for purchasing this software.\n";
		
		
		 
		 
	    TextArea t1=new TextArea(s,10,40,Scrollbar.VERTICAL);
		t1.setEditable(false);
	
		t1.setBounds(20,100,450,300);
	
		add(t1);
		
		 Font f1=new Font("Swis721 Blk BT",Font.BOLD,15);
		 t1.setFont(f1);
		
		
		
		    setBounds(100,100,500,500);
		
		// sc.setBounds(20,100,450,300);
			
		 
       //Font f=new Font("Swis721 Blk BT",Font.BOLD,80);
		//setFont(f);

		//JLabel l1=new JLabel(20);
		JLabel l1=new JLabel("Windows Help");
		add(l1);
		Font f2=new Font("Swis721 Blk BT",Font.BOLD,25);
	    l1.setFont(f1);
		l1.setForeground(Color.red);
		l1.setBounds(170,10,150,100);		
		Color c=new Color(255,00,000);
	 
		setLayout(null);	
		setResizable(false);
		setBounds(250,50,450,450);
	
		//WA wa=new WA();
		///addWindowListener(wa);
		setResizable(false);			   
		setIconImage(new ImageIcon("INF/Icon.png").getImage());		
		setVisible(true);
		//makeConnection();

	//	setLocation((screen.width - 500)/2,((screen.height-350)/2));

	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			dispose();
		}else
		{
			dispose();
		
		}
		
	}
    public static void main(String args[])
    {
		JFrame.setDefaultLookAndFeelDecorated(true);
    	Help m=new Help();
    	m.setSize(500,500);
    	m.setVisible(true);

    	setDefaultLookAndFeelDecorated(true);
    	m.setTitle("Windows Help");
    }
	
	
	
}
