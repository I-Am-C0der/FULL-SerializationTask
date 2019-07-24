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
 * Servlet implementation class UpdateStudent
 */
@WebServlet(name = "UpdateStudent", urlPatterns = { "/updateinfo" })
public class Modify extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6996557647110748832L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Student");
		PreparedQuery preparedQuery = datastore.prepare(query);

		response.setContentType("text/html");

		PrintWriter print = response.getWriter();
		boolean invalidDetails = false;
		String getName = request.getParameter("name");
		invalidDetails = Helper.checkCharacter(getName);

		boolean nameNotFound = true;
		print.println("<html><head>");
		print.println("<title>Student Corner</title>");
		print.println("</head><body>");
		print.println("<h2 align=\"center\">Update Student Details</h2><br><br>");
		print.println("<form>\r\n");
		print.println("<button type=\"submit\" formaction=\"updateinfo.html\">Back</button></form><br>");

		print.println("<form method=\"post\" action=\"updateinfo\">\r\n");

		if (!invalidDetails) {
			print.println("<table border=\"1\" align=\"center\"> ");
			print.println("<col width=\"130\">");
			print.println("<col width=\"130\">");
			print.println("<col width=\"130\">");
			print.println("<col width=\"130\">");
			print.println("<tr>" + "<th>Choose</th>" + "<th>ID</th>" + "<th>Name</th> " + "<th>Age</th>" + "</tr>");
			for (Entity entity : preparedQuery.asIterable()) {
				long id = entity.getKey().getId();
				String name = entity.getProperty("Name").toString();
				int age = Integer.parseInt(entity.getProperty("Age").toString());
				if (name.equals(getName)) {
					nameNotFound = false;
					print.println("<tr><td>");
					print.print("<input type=\"radio\" name=\"update\" value=\"" + id + "\" required/>");
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
			else {
				print.println("Update Name: <input type=\"text\" name=\"name\" required/><br><br><br> "
						+ "Update Age:&nbsp;&nbsp;&nbsp; <input type=\"number\" name=\"age\" required/><br><br><br>"
						+ "<button type=\"submit\">Update Details</button></form>");

			}
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
		PrintWriter pw = response.getWriter();
		pw.println("<html><body>");
		pw.println("<br><br><form method=\"get\" action=\"/home\">\r\n"
				+ "    <button type=\"submit\">Back</button>\r\n" + "</form>");
		boolean invalidDetails = false;
		try {
			long updateId = Long.parseLong(request.getParameter("update"));

			String updateName = request.getParameter("name");
			invalidDetails = Helper.checkCharacter(updateName);
			if (!invalidDetails) {
				int updateAge = Integer.parseInt(request.getParameter("age"));

				invalidDetails = QueryHelper.updateQueryOperation(updateId, updateAge, updateName);
			}
		} catch (NumberFormatException e) {
			invalidDetails = true;
		}

		if (!invalidDetails)
			pw.println("The Desired Record is Updated.");
		else
			pw.println("Enter Valid Details");

		pw.println("</body></html>");
		pw.close();

	}

}
