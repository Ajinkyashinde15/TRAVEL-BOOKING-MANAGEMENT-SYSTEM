import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




import java.util.Date;

public class SimpleAnalogClock extends JPanel implements ActionListener
{
	int centerPointX;
	int centerPointY;
	
	int xs;
    int ys;
	JButton b;
	Font quartzFont;
	JLabel l;
	Date currentDate;
	Font f;
	
	public SimpleAnalogClock()
	{
		b=new JButton("     OK...........");
		add(b);
		setBackground(new Color(0,0,0));
		b.setBounds(150, 480,120,25);
		b.addActionListener(this);
		b.setForeground(Color.green);
		b.setBackground(Color.black);
		
		l=new JLabel("Analog Clock");
		add(l);
		l.setForeground(Color.pink);
		Font f=new Font("Dutch801 Rm BT", Font.BOLD, 25);
		//setBackground(new Color(225,0,0));
		l.setFont(f);
		l.setBounds(150, 10, 200, 40);
		
		setLayout(new BorderLayout());
		JFrame frame=new JFrame("Simple Analog Clock");
		
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450,550);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		//LOOP THAT WILL UPDATE CLOCK 
		while(true)
		{
			repaint();
			try
			{
				Thread.sleep(900);
			}
			catch(Exception exception)
			{
				exception.printStackTrace();
			}
		}
		//WA wa=new WA();
		///addWindowListener(wa);
		//setResizable(false);			   
		//setIconImage(new ImageIcon("INF/Icon.png").getImage());		
		//setVisible(true);
		//makeConnection();

			
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b)
		{
			
			System.exit(0);
		}
		
	}
	
	public void paint(Graphics g)
	{
		centerPointX=getSize().width/2;
		centerPointY=getSize().height/2;
		
		currentDate=new Date();
		
		//************GET CURRENT SECOND AND ALL INFORMATION TO DRAW SECOND POINTER**************
		/**Information that i mean here is
		 *coordinate for second pointer's corner.
		 *You can try change value 5 in xsm,ysm,xsa and ysa.
		 *After that, try compile and execute this program.
		 **/
		int s=currentDate.getSeconds();
		
		int s_minus_five=s-5;
		int s_add_five=s+5;
		
		xs = (int) (Math.cos(s * Math.PI / 30 - Math.PI / 2) * 170 + centerPointX);
    	ys = (int) (Math.sin(s * Math.PI / 30 - Math.PI / 2) * 170 + centerPointY);
		
		int xsm=(int) (Math.cos(s_minus_five * Math.PI / 30 - Math.PI / 2) * 5 + centerPointX);
		int ysm=(int) (Math.sin(s_minus_five * Math.PI / 30 - Math.PI / 2) * 5 + centerPointY);
		
		int xsa=(int) (Math.cos(s_add_five * Math.PI / 30 - Math.PI / 2) * 5 + centerPointX);
		int ysa=(int) (Math.sin(s_add_five * Math.PI / 30 - Math.PI / 2) * 5 + centerPointY);
		//************GET CURRENT SECOND AND ALL INFORMATION TO DRAW SECOND POINTER**************
		
		//************GET CURRENT MINUTE AND ALL INFORMATION TO DRAW MINUTE POINTER**************
		/**Information that i mean here is
		 *coordinate for minute pointer's corner.
		 *You can try change value 10 in xmm,ymm,xma and yma.
		 *After that, try compile and execute this program.
		 **/
		int m=currentDate.getMinutes();
		
		int m_minus_ten=m-10;
		int m_add_ten=m+10;
		
		int xm = (int) (Math.cos(m * Math.PI / 30 - Math.PI / 2) * 170 + centerPointX);
    	int ym = (int) (Math.sin(m * Math.PI / 30 - Math.PI / 2) * 170 + centerPointY);
		
		int xmm=(int) (Math.cos(m_minus_ten * Math.PI / 30 - Math.PI / 2) * 10 + centerPointX);
		int ymm=(int) (Math.sin(m_minus_ten * Math.PI / 30 - Math.PI / 2) * 10 + centerPointY);
		
		int xma=(int) (Math.cos(m_add_ten * Math.PI / 30 - Math.PI / 2) * 10 + centerPointX);
		int yma=(int) (Math.sin(m_add_ten * Math.PI / 30 - Math.PI / 2) * 10 + centerPointY);
		//************GET CURRENT MINUTE AND ALL INFORMATION TO DRAW MINUTE POINTER**************
		
		//************GET CURRENT HOUR AND ALL INFORMATION TO DRAW HOUR POINTER**************
		/**Information that i mean here is
		 *coordinate for hour pointer's corner.
		 *You can try change value 10 in xhm,yhm,xha and yha.
		 *After that, try compile and execute this program.
		 **/
		int h=currentDate.getHours();
		
		int h_minus_ten=h-10;
		int h_add_ten=h+10;
		
		int xh = (int) (Math.cos(h * Math.PI / 6 - Math.PI / 2) * 100 + centerPointX);
    	int yh = (int) (Math.sin(h * Math.PI / 6 - Math.PI / 2) * 100 + centerPointY);
		
		int xhm=(int) (Math.cos(h_minus_ten * Math.PI / 6 - Math.PI / 2) * 10 + centerPointX);
		int yhm=(int) (Math.sin(h_minus_ten * Math.PI / 6 - Math.PI / 2) * 10 + centerPointY);
		
		int xha=(int) (Math.cos(h_add_ten * Math.PI / 6 - Math.PI / 2) * 10 + centerPointX);
		int yha=(int) (Math.sin(h_add_ten * Math.PI / 6 - Math.PI / 2) * 10 + centerPointY);
		//************GET CURRENT HOUR AND ALL INFORMATION TO DRAW HOUR POINTER**************
		
		//OVAL THAT MAKE CLOCK CIRCLE
		int ovalWidth=400;
		int ovalHeight=400;
		//OVAL THAT MAKE CLOCK CIRCLE
		
		Graphics2D g2d=(Graphics2D)g;
		
		super.paint(g2d);
		
		//PEN COLOR TO DRAW : White
		Color penColor=new Color(255,255,255);
		
		//PEN COLOR TO DRAW : Black
		Color penColor2=new Color(0,0,0);
		
		//Clock circle background color
		g2d.setColor(penColor);
		g2d.fillOval(((getSize().width)/2)-(ovalWidth/2),((getSize().height)/2)-(ovalHeight/2),ovalWidth,ovalHeight);
		
		//Draw "Quartz" text
		g2d.setColor(penColor2);
		quartzFont=new Font("Verdana",Font.BOLD,20);
		g2d.setFont(quartzFont);
		g2d.drawString("Quartz",centerPointX-30,centerPointY-100);
		
		//Draw number 12,3,6 and 9
		quartzFont=new Font("Verdana",Font.BOLD,50);
		g2d.setFont(quartzFont);
		g2d.drawString("12",centerPointX-30,centerPointY-120);
		g2d.drawString("6",centerPointX-18,centerPointY+170);
		g2d.drawString("3",centerPointX+140,centerPointY+13);
		g2d.drawString("9",centerPointX-170,centerPointY+13);
		
		//*************DRAW SECOND POINTER*********
		int[]coordinateXs={centerPointX,xsm,xs,xsa};
		int[]coordinateYs={centerPointY,ysm,ys,ysa};
		g2d.fillPolygon(coordinateXs,coordinateYs,4);
		//*************DRAW SECOND POINTER*********
		
		//*************DRAW MINUTE POINTER*********
		int[]coordinateXm={centerPointX,xmm,xm,xma};
		int[]coordinateYm={centerPointY,ymm,ym,yma};
		g2d.fillPolygon(coordinateXm,coordinateYm,4);
		//*************DRAW MINUTE POINTER*********
		
		//*************DRAW HOUR POINTER*********
		int[]coordinateXh={centerPointX,xhm,xh,xha};
		int[]coordinateYh={centerPointY,yhm,yh,yha};
		g2d.fillPolygon(coordinateXh,coordinateYh,4);
		//*************DRAW HOUR POINTER*********
		
		//*************DRAW LINE TICK*******************
		//Line tick that i mean here is, short line to indicate second.
		//I hope you understand what i mean.
		for(int i=1; i<=360; i++)
		{
			int tickX;
			int tickY;
			
			int tickXb;
			int tickYb;
			
			tickX=(int) (Math.cos(i * Math.PI / 30 - Math.PI / 2)* 180 + centerPointX);
    		tickY=(int) (Math.sin(i * Math.PI / 30 - Math.PI / 2)* 180 + centerPointY);
    		
    		tickXb=(int) (Math.cos(i * Math.PI / 30 - Math.PI / 2) * 300 + centerPointX);
    		tickYb=(int) (Math.sin(i * Math.PI / 30 - Math.PI / 2) * 300 + centerPointY);
    		
    		g2d.drawLine(tickXb,tickYb,tickX,tickY);
		}
		//*************DRAW LINE TICK*******************
	}
	
	public static void main(String[]args)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		SimpleAnalogClock sac=new SimpleAnalogClock();
		sac.setSize(102,200);

	}
}