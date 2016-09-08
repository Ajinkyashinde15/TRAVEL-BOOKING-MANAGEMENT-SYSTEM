
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JFrame;

public class Wmap extends JFrame implements ActionListener
{

	JButton b1;
	JLabel l1,l2;
	ImageIcon bk;
	Font f;
	
	public Wmap()
	{
		JButton b1=new JButton("Thanks....!!!!!!",new ImageIcon("CANCEL.PNG"));
		add(b1);
		b1.setBounds(20, 47, 120, 20);
		//b1.setBounds(400, 700, 20, 10);
		b1.addActionListener(this);
		
        Font f=new Font("Swis721 Blk BT",Font.BOLD,80);
		setFont(f);

		//JLabel l1=new JLabel(20);
		JLabel l1=new JLabel("World Map");
		add(l1);
		l1.setBounds(200,10,200,80);
		//Font f=new Font("Swis721 Blk BT",Font.BOLD,80);
		//setFont(f);


		l2 = new JLabel();
		l2.setBounds(20,1,5000,550);
		add(l2);
		ImageIcon bk = new ImageIcon("word_map.jpg");
		l2.setIcon(bk);

	 
		setLayout(null);	
		setResizable(false);
		setBounds(230,50,450,450);

		//WA wa=new WA();
		///addWindowListener(wa);
		setResizable(false);			   
		setIconImage(new ImageIcon("INF/Icon.png").getImage());		
		setVisible(true);
		//makeConnection();
	
	}
	public void actionPerformed(java.awt.event.ActionEvent e)
			{
				dispose();

			}
    public static void main(String args[])
    {

		JFrame.setDefaultLookAndFeelDecorated(true);
    	Wmap m=new Wmap();
    	m.setSize(500,500);
    	m.setVisible(true);

    	//setDefaultLookAndFeelDecorated(true);
    	m.setTitle("World Map");
    }
	
	
	
}
