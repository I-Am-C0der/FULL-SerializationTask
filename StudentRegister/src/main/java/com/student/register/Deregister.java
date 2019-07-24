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
@WebServlet(name = "RemoveStudent", urlPatterns = { "/removeinfo" })
public class Deregister extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -57105462738487746L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Student");
		response.setContentType("text/html");

		PrintWriter print = response.getWriter();
		boolean invalidName = false;
		String removeName = request.getParameter("name");
		invalidName = Helper.checkCharacter(removeName);
		// Filter equalName = new FilterPredicate("Name", FilterOperator.EQUAL, name);
		// query.setFilter(equalName);
		PreparedQuery preparedQuery1 = datastore.prepare(query);
		boolean nameNotFound = true;
		print.println("<html><head>");
		print.println("<title>Student Corner</title>");
		print.println("</head><body>");
		print.println("<h2 align=\"center\">Remove Student Details</h2><br><br>");
		print.println("<form>\r\n");
		print.println("<button type=\"submit\" formaction=\"home\">Back</button></form><br><br>");
		print.println("<form method=\"post\" action=\"removeinfo\">\r\n");
		if (invalidName == false) {

			print.println("<table border=\"1\" align=\"center\"> ");
			print.println("<col width=\"130\">");
			print.println("<col width=\"130\">");
			print.println("<col width=\"130\">");
			print.println("<col width=\"130\">");
			print.println("<tr>" + "<th>Choose</th>" + "<th>ID</th>" + "<th>Name</th> " + "<th>Age</th>" + "</tr>");
			for (Entity entity : preparedQuery1.asIterable()) {
				long id = entity.getKey().getId();
				String name = entity.getProperty("Name").toString();
				int age = Integer.parseInt(entity.getProperty("Age").toString());
				if (name.equals(removeName)) {
					nameNotFound = false;
					print.println("<tr><td>");
					print.print("<input type=\"radio\" name=\"remove\" value=\"" + id + "\">");
					print.println("</td><td>");
					print.print(id);
					print.println("</td><td>");
					print.print(name);
					print.println("</td><td>");
					print.print(age);
					print.println("</td></tr> ");
					print.println("<br>");

				}
			}
			print.println("</table>");
			if (nameNotFound)
				print.println("<center>No Records Founds</center>");
			else
				print.println("<button type=\"submit\">Remove Details</button>");

		} else
			print.println("Enter Valid Name");

		print.println("</form");

		print.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();

		long removeId = Long.parseLong(request.getParameter("remove"));
		QueryHelper.removeByIdQuery(removeId);

		pw.println("<html><body>");

		pw.println("Desired Record is removed.");

		pw.println("<br><br><form method=\"get\" action=\"/home\">\r\n"
				+ "    <button type=\"submit\">Back</button>\r\n" + "</form>");
		pw.println("</body></html>");
		pw.close();

	}
}
