import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import java.applet.*;
/*
<applet code="Clock" height=200 width=200>
</applet>
*/
public class Clock extends Applet implements Runnable
	{ 
	final static int midX=180;
        final static int midY=180;
	final static int rad= 90;
        Thread aclock = null;	
	ClockHand seconds, minutes, hours;
        int sec,min,hr;
        Font fnt;
        boolean compulsoryRun = true;
        Calendar clocktime;
        Calendar time;
        String TimeStart;
        String TimStart;
        Date dat;
        String  da;
        String date;

/* Initialises the variables */
	public void init()	
 	{
 	setBackground(Color.green);
 	setForeground(Color.gray);
 	clocktime = Calendar.getInstance();
 	time = Calendar.getInstance();
 	fnt= new Font("Arial", Font.BOLD,24);	
 	}
/* Starts the Applet */	
	public void start()
 	{
    	compulsoryRun=true;
    	aclock = new Thread(this);
	seconds = new ClockHand(clocktime.get(Calendar.SECOND),rad-15,15,Color.black,0);
	minutes = new ClockHand(clocktime.get(Calendar.MINUTE),rad*5/7,Color.red,9);
	hours= new ClockHand(clocktime.get(Calendar.HOUR_OF_DAY),rad-15,15,Color.blue,14);
	sec = time.get(Calendar.SECOND);
 	min = time.get(Calendar.MINUTE);
 	hr = time.get(Calendar.HOUR_OF_DAY);
 	hr=hr%12;
	dat = new Date();
	da = new String();
	date = new String();
	da = dat.toString();
	date = da.substring(0,10);
 	aclock.start();
     }
/*Stops the Applet*/	
	public void stop()
  	{
	aclock=null;
	compulsoryRun= false;
    	TimeStart+= "stopped";
    	TimStart+= "stopped";
  }	
/* Runs the thread */	
	public void run()
   	{
	repaint();
	while(compulsoryRun)
      {	     
	     repaint(midX-rad-5,midY-rad-5,midX+rad+5,midY+rad+5);
	     try
	     {
		     Thread.sleep(1000);		    
	     }
	     catch(InterruptedException ex)
	      {
		      System.out.println("Exception raised is:"+ex); 
		      }	      
	     sec++;
	     if(sec == 60)
	        {
		     sec = 0;
		     min++;
        	  }
           if(min == 60)
	        {
		    	min = 0;
		    	hr++;
        	 }
          if(hr == 13)
	    	{
		    hr = 1;
		    TimStart = hr+":"+min+":"+sec;
	      }      		
	    seconds.value++;
	    if(seconds.value == 60)
	   	{
		    seconds.value = 0;
		    minutes.value++;
        	}
          if(minutes.value == 60)
	    	{
		    minutes.value = 0;
		    hours.value++;
        }
        if(hours.value == 13)
	    {
		hours.value = 1;
TimeStart = (int)hours.value+":"+(int)minutes.value+":"+(int)seconds.value;
	    	}    
        }        
  }   
/* Assigns the values for the angles of the hands in the clock */
private void assignAngles()
{
    	seconds.ang=(seconds.value)*Math.PI/30;
minutes.ang=(minutes.value)*Math.PI/30 + (seconds.value)*Math.PI/1800;
	hours.ang=(hours.value)*Math.PI/6 + (minutes.value)*Math.PI/360;
}	
/* Calls the Update() for the Clock application */	
	public void paint(Graphics g)
	{
		update(g);	 
	}
	
/* Paints the Clock on the Applet */ 
public void update(Graphics g)
 {
   	Graphics2D g2d = (Graphics2D)g;
	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 	g.setColor(Color.yellow);
  	g.fillOval(midX-rad,midY-rad,2*rad,2*rad);
  
 	g.setColor(Color.black);
 	g.drawOval(midX-rad,midY-rad,2*rad,2*rad);
  	g.drawOval(midX-rad-1,midY-rad-1,2*rad+2,2*rad+2);  
  
  	g.setColor(Color.gray);
  	g.drawOval(midX-rad-2,midY-rad-2,2*rad+4,2*rad+4);
  	g.drawOval(midX-rad-3,midY-rad-3,2*rad+6,2*rad+6); 
  
	g.setColor(Color.white);
	g.fillRect(midX+rad/2-28, midY-7,68,14);     
	g.setColor(Color.black);
	g.drawString(date,midX+rad/2-28,midY+4);
		
	for(int i=0;i<60;i++) 
	  {
  		if(i%5==0) 
  		{
		g.setColor(Color.black);  			      g.drawLine(midX+(int)((rad*6/7)*Math.sin(Math.toRadians(i*6))),midY-(int)((rad*6/7)*Math.cos(Math.toRadians(i*6))),midX+(int)(rad*Math.sin(Math.toRadians(i*6))),midY-(int)(rad*Math.cos(Math.toRadians(i*6))));
  		}
  		else 
 		  {
   			g.setColor(Color.gray);
   			    g.drawLine(midX+(int)((rad*6.5/7)*Math.sin(Math.toRadians(i*6))),midY-(int)((rad*6.5/7)*Math.cos(Math.toRadians(i*6))),midX+(int)(rad*Math.sin(Math.toRadians(i*6))),midY-(int)(rad*Math.cos(Math.toRadians(i*6))));
 		      }
		}
		showStatus(TimeStart);
		showStatus(TimStart);
 		assignAngles();

 		hours.disp(g);
 		minutes.disp(g);
 		seconds.disp(g);

 		g.setColor(Color.red);
 		g.fillOval(midX-3,midY-3,6,6);
 		g.setColor(Color.green);
 		g.fillOval(midX-1,midY-1,2,2);
		
 		g.setColor(Color.black);
 		g.setFont(fnt); 		
	showStatus("Current Time  ---- " + hr + ":" + min/10 + min % 10 + ":" + sec/10 + sec%10);
		   	}


class ClockHand
{
   private int leng;
   private int hlength;
   private Color handColour;
   private Shape hand;
   private double rect;
   double ang;
   double value;
   AffineTransform at;
	 
/* Initialises the variables */
ClockHand(double val,Color colour,int ret) 
  {
     leng = 100;
     value = val;
     hlength = 0;
     handColour = colour;
     rect = Math.PI*ret/180;
     initial();
  }
/* Initialises the variables */
ClockHand(double val, int leng,Color colour,int ret) 
   {
 	this.leng = leng;
 	this.value = val;
 	hlength = 0;
 	handColour = colour;
 	rect = Math.PI*ret/180;
 	initial();
   }
	
/* Initialises the variables */
ClockHand(double val, int leng,int ha, Color colour, int ret) 
  {
 	this.leng = leng;
 	this.value = val;
 	hlength = ha;
 	handColour = colour;
 	rect = Math.PI*ret/180;
	initial();
    }
/*Shaping the hands of the analog clock */	
void initial() 
  {
 	int x[] = new int[4];
	int y[] = new int[4];

 	x[0] = Clock.midX - hlength;
 	y[0] = Clock.midY;

x[1] = Clock.midX + (int)((leng/2)*Math.cos(rect));
y[1] = Clock.midY - (int)((leng/2)*Math.sin(rect));

 	x[2] = Clock.midX + leng;
 	y[2] = Clock.midY;

x[3] = Clock.midX + (int)((leng/2)*Math.cos(rect));
y[3] = Clock.midY + (int)((leng/2)*Math.sin(rect));

 	hand = new Polygon(x,y,4);
  }
 	 
/* Displaying the hands of the clock */
void disp(Graphics g) 
  {
	Graphics2D gd = (Graphics2D)g;
	g.setColor(handColour);
at = AffineTransform.getRotateInstance(ang-Math.PI/2,Clock.midX,Clock.midY);
		gd.fill(at.createTransformedShape(hand));
		gd.draw(at.createTransformedShape(hand));
   }
}
}