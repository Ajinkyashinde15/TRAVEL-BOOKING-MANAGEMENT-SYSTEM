import java.util.*;


public class ScheduleHtmlHelper
{
	public ArrayList al=new ArrayList();
	public ScheduleHtmlHelper()
	{
		
	}
	
	public String GetHtml()
	{
		if(al.size()>0)
		{
			StringBuilder hs=new StringBuilder();
			hs.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>");
			hs.append("<html xmlns='http://www.w3.org/1999/xhtml'>");
			hs.append("<head><link href='reports.css' rel='stylesheet' type='text/css'></head>");
			hs.append("<body>");
			hs.append("<div id='container'>");
			hs.append("<h1>Travel Scheduling</h1>");
			hs.append("<table id='withborder' cellspacing='0'><tr>");
			hs.append("<th>Bus No.</th>");
			hs.append("<th>Reg No.</th>");
			hs.append("<th>Route No.</th>");
			hs.append("<th>Route Name</th>");
			hs.append("<th>Driver No.</th>");
			hs.append("<th>Driver</th>");
			hs.append("<th>Trip No.</th>");
			hs.append("<th>Date</th>");
			hs.append("<th>Time</th></tr>");
			
			for(int i=0;i<al.size();i++)
			{
				ScheduleDetail r=(ScheduleDetail) al.get(i);
				hs.append("<tr><td>"+r.BusNo+"</td><td>"+r.RegNo+"</td><td>"+r.RouteNo+"</td><td>"+r.RouteName+"</td><td>"+r.EmployeeId+"</td><td>"+r.EmployeeName+"</td><td>"+r.TripNo+"</td><td>"+r.Date+"</td><td>"+r.Time+"</td></tr>");
			}
			hs.append("</table>");
			hs.append("</div><h2>Have a nice Day.</h2></body></html>");
			
			return hs.toString();
		}
		return null;
	}
}