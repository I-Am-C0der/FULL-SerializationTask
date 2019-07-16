package com.student.register;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * Servlet implementation class RemoveStudent
 */
@WebServlet(
		name = "RemoveStudent",
		urlPatterns = {"/removeinfo"}
		)
public class RemoveStudent extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -57105462738487746L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

		PrintWriter print=response.getWriter();

		print.println("<html><head>");
		print.println("<title>Student Corner</title>");
		print.println("</head><body>");
		print.println("<h2 align=\"center\">Remove Student Details</h2><br><br>");
		print.println("<form action=\"removeinfo\" method=\"post\">" +
				"<input type=\"radio\" name=\"remove\" value=\"1\">&nbsp;Remove by ID<br>"+
				"ID: <input type=\"number\" name=\"id\"><br>"+
				"<br> <input type=\"radio\" name=\"remove\" value=\"2\">&nbsp;Remove All<br><br>"+
				"<button type=\"submit\">Remove</button>"+
				"		<button type=\"submit\" formaction=\"/home\">Back</button></form>");
		print.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatastoreService ds= DatastoreServiceFactory.getDatastoreService();

		int removeOption =Integer.parseInt( request.getParameter("remove"));
		if(removeOption==1) {

			int removeId = Integer.parseInt(request.getParameter("id"));
			Query q=new Query("Student");
			PreparedQuery pq=ds.prepare(q);
			for(Entity u1: pq.asIterable()) {
				int getId = Integer.parseInt(u1.getProperty("Id").toString());
				if(removeId==getId)
					ds.delete(u1.getKey());
			}
		}
		else {
			Query q=new Query("Student");
			PreparedQuery pq=ds.prepare(q);
			for(Entity u1: pq.asIterable()) {
				ds.delete(u1.getKey());
			}
		}

		PrintWriter pw=response.getWriter();
		pw.println("<html><body>");


		pw.println("Desired Records are removed.");


		pw.println("<br><br><form method=\"get\" action=\"/home\">\r\n" + 
				"    <button type=\"submit\">Back</button>\r\n" + 
				"</form>");
		pw.println("</body></html>");
		pw.close();

	}
}

