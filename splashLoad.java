import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
class splashLoad extends JWindow
{
	JLabel l,title,wait;
	ImageIcon ii;

	public splashLoad()
	{	
		
		ii=new ImageIcon("INF\\splash.jpg");	
		wait = new JLabel(ii);		
		add(wait);
		//wait.setForeground(Color.red);
		//wait.setFont(new Font("Times New Roman", Font.BOLD, 18));

		setBounds(220,180,600,300);
		setVisible(true);

		try
		{
				Thread.sleep(3000);
				dispose();
		}
		catch (Exception e) { System.out.println("Cannot add thread"); }
		
	}	

}
class LoadSp
{
	public static void main(String cp[])
	{splashLoad sp=new splashLoad();}
}
