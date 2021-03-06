
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import java.sql.*;

@SuppressWarnings("serial")
public class Searche extends JFrame implements ActionListener
{
	JLabel		name,searchp,photo,lblPerson;
	JTextField		txt_name;
	
	JComboBox cmbo_Person;
	JButton		cmd_back,cmd_ok,cmd_search,cmd_cancel;
    JDialog d;
	Connection con;
	JFrame frm;
	public JTextArea listPane;

	public Searche()
	{
	     setLayout(null);		
		setDefaultLookAndFeelDecorated(true);
		setTitle("Search Employee ");
		

		
		name=new JLabel("Enter Employee Name ");
		name.setBounds(20,130,250,50);
		add(name);
		name.setForeground(Color.black);
		name.setFont(new Font("Times New Roman", Font.BOLD, 15));
	
		
		searchp=new JLabel("Search Employee");
		searchp.setBounds(110,0,200,100);
		add(searchp);
		searchp.setForeground(Color.black);
		searchp.setForeground(Color.red);
		searchp.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		
		
		txt_name=new JTextField(30);
		txt_name.setBounds(220,140,150,30);
		add(txt_name);
		txt_name.addActionListener(this);
		

		
		listPane = new JTextArea();
		listPane.setBounds(280,170,20,20);		

		cmd_search=new JButton("Search",new ImageIcon("SEARCH.PNG"));
		cmd_search.setBounds(40,270,150,30);
		add(cmd_search);
		cmd_search.addActionListener(this);

		cmd_cancel = new JButton("Cancel",new ImageIcon("CANCEL.PNG"));
		cmd_cancel.setBounds(220, 270, 150, 30);
		add(cmd_cancel);
		cmd_cancel.addActionListener(this);


		paintComponents(getGraphics());
		
		setBounds(250,150,400,400);		
		
		//WA wa=new WA();
		///addWindowListener(wa);
		setResizable(false);			   
		setIconImage(new ImageIcon("INF/Icon.png").getImage());		
		setVisible(true);
		//makeConnection();
	}
		public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==cmd_search)
		{
			String str=txt_name.getText();
            
			if(txt_name.getText().equals(""))
		   {
			
			 JOptionPane.showMessageDialog(null, " Enter a Search String !", "Search Result!", JOptionPane.ERROR_MESSAGE); 
			
			}
			else
			{
  
                    new Emp_Search(str);
				
		    }
		}
		else
	{
			
			
		dispose();
		
	}
		
		
	}

	public static void main(String cp[])
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		Searche s=new Searche();
		s.setVisible(true);
	}



	
}
