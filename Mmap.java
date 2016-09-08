
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JFrame;


public class Mmap extends JFrame implements ActionListener
{

	JButton b1;
	JLabel l1,l2;
	ImageIcon bk;
	Font f;
	
	public Mmap()
	{
		JButton b1=new JButton("Thanks....!!!!!!");
		add(b1);
		b1.setBounds(20, 47, 120, 20);
		//b1.setBounds(400, 700, 20, 10);
		b1.addActionListener(this);
		
        Font f=new Font("Swis721 Blk BT",Font.BOLD,80);
		setFont(f);

		//JLabel l1=new JLabel(20);
		JLabel l1=new JLabel("Maharashtra Map");
		add(l1);
		l1.setBounds(200,10,200,80);
		//Font f=new Font("Swis721 Blk BT",Font.BOLD,80);
		//setFont(f);


		l2 = new JLabel();
		l2.setBounds(1,1,5000,550);
		add(l2);
		ImageIcon bk = new ImageIcon("Majha-Maharashtra.jpg");
		l2.setIcon(bk);

	 
		setLayout(null);	
		setResizable(false);
		setBounds(250,50,450,450);
	
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			System.exit(0);
			
		}else
		{
			System.exit(0);
		    
		}
		String s=b1.getActionCommand();
		if(s==("Thanks....!!!!!!"))
		{
			System.exit(0);
			
		}
		
	}
    public static void main(String args[])
    {
    	Mmap m=new Mmap();
    	m.setSize(500,500);
    	m.setVisible(true);

    	//setDefaultLookAndFeelDecorated(true);
    	m.setTitle("Maharashtra Map");
    }
	
	
	
}
