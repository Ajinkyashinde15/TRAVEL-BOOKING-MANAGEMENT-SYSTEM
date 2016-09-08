public class HtmlHelper
{
	public HtmlHelper()
	{}
	
	public String GetHtml(ReciptDetail r)
	{
		if(r!=null)
		{
			String hs=new String();
			hs="<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>";
			hs+="<html xmlns='http://www.w3.org/1999/xhtml'>";
			hs+="<head><link href='reports.css' rel='stylesheet' type='text/css'></head>";
			hs+="<body>";
			hs+="<div id='container'>";
			hs+="<font Color='Black'>";
			//hs+="<img src='C:\Documents and Settings\All Users\Documents\My Pictures\Sample Pictures\Sunset.jpg'> </src>";
			hs+="<h2><u><b><i> <center> $$$   V.I.P Travels    $$$   </center></h2></u></b></i>";
			hs+="<h1><u>Payment Receipt</u></h1>";
			hs+="<table cellspacing='0'>";
			hs+="<tr><td>Payment No:-</td><td>"+r.paymentno+"</td></tr>";
			hs+="<tr><td>Passenger No:-</td><td>"+r.passengerno+"</td></tr>";
			hs+="<tr><td>Passenger Name:-</td><td>"+r.passengername+"</td></tr>";
			hs+="<tr><td>Mode of Pay:-</td><td>"+r.modeofpay+"</td></tr>";
			hs+="<tr><td>Received By:-</td><td>"+r.recivedby+"</td></tr>";
			hs+="<tr><td>Date of Pay:-</td><td>"+r.dateofpay+"</td></tr>";
			hs+="<tr><td>Amount Paid:-</td><td>"+r.amtpaid+"</td></tr>";
			hs+="</table>";
			hs+="</div><h2><marquee>*****Happy Journey*****</h2></body></html>";
			return hs;
		}
		return null;
	}
}