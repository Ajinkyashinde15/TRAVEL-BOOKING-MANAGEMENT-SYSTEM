
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.border.Border;


public class About  extends JFrame implements ActionListener
{

	JButton b1;
	JLabel l1;
	Font f,f1,f2;
	TextArea t1;
	String s;
	//JScrollBar sc;
	
	public About()
	{
		
		 setLayout(null);		
		setDefaultLookAndFeelDecorated(true);
		JButton b1=new JButton("Exit.......!!!!!!",new ImageIcon("CANCEL.PNG"));
		add(b1);
		b1.setBounds(180, 430, 120, 20);
		//b1.setBounds(400, 700, 20, 10);
		b1.addActionListener(this);
		
		 Font f=new Font("Swis721 Blk BT",Font.BOLD,180);
		 setFont(f);

			s="                                    About Projects          \n  " +
		
		
				

      "\nThe software program .Bus Reservation System. provides bus " +
      "\ntransportation system,a facility to reserved seats," +
     " \ncancellation of seats and different types of enquiry.\n"+
      "\nEXISTING SYSTEM & NEED FOR THE SYSTEM:"+

    "\nThere are some disadvantages of the existing system due to which " +
    "\nthe need for the new system is required; some of them are as follows: "+
    "\n(a) The application does not provide security to the system. "+
    "\n(b) There is no flexibility to the database. "+
    "\n(c) There is no GUI interaction for more convenience. "+
    "\n(d) Coding is not much efficient. "+

		"\nObjective To Be Fulfilled:\n"+

			"\n(a) Development of software in the given time.\n"+
			"\n	(b) To create an effective and efficient application.\n"+
				
			"\nUser Requirements:\n"+
			"\n	(a) The application should provide a user friendly environment\n"+
			"\n	(b) The application should be easily understandable and reliable\n"+
			"\n	(c) The application should fulfill all essential facilities.\n"+
			"\n	(d) The software being built must provide platform independent application.\n";

		 
		 
			 
		    TextArea t1=new TextArea(s,10,40,Scrollbar.VERTICAL);
			t1.setEditable(false);
		t1.setBounds(20,100,450,300);
	
		add(t1);
	
		 Font f1=new Font("Swis721 Blk BT",Font.BOLD,16);
		 t1.setFont(f1);
		// t1.setColor(Color.darkGray);
		//JScrollBar sc=new JScrollBar(JScrollBar.HORIZONTAL,2,3,20,30);
		//add(sc);
		
		
		 Container contentPane = this.getContentPane();
		    t1 = new TextArea();
		  
		    setBounds(150,300,500,500);
		
		// sc.setBounds(20,100,450,300);
			
		 
       //Font f=new Font("Swis721 Blk BT",Font.BOLD,80);
		//setFont(f);

		//JLabel l1=new JLabel(20);
		JLabel l1=new JLabel("About Project");
		add(l1);
		l1.setBounds(170,10,180,80);	
	l1.setForeground(Color.red);
		
		 Font f2=new Font("Bookman Old Style",Font.BOLD,20);
		 l1.setFont(f2);
		
		paintComponents(getGraphics());
        paintComponents(getGraphics());
		
		setBounds(250,150,400,400);		
	 
		setLayout(null);	
		setResizable(false);
		//setBounds(250,50,450,450);

		//WA wa=new WA();
		///addWindowListener(wa);
		setResizable(false);			   
		setIconImage(new ImageIcon("INF/Icon.png").getImage());		
		setVisible(true);
		//makeConnection();
	
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			dispose();
			
		}else{

		dispose();
		
		}
		String s=b1.getActionCommand();
		
	}
    public static void main(String args[])
    {
		JFrame.setDefaultLookAndFeelDecorated(true);
    	About m=new About();
    	m.setSize(500,500);
    	m.setVisible(true);

    	JFrame.setDefaultLookAndFeelDecorated(true);
    	m.setTitle("About Project");
    }
	
	
	
}
