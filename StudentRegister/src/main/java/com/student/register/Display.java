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
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;

/**
 * Servlet implementation class DisplayStudent
 */
@WebServlet(name = "DisplayStudent", urlPatterns = { "/displayinfo" })
public class Display extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 544049702759084408L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

		response.setContentType("text/html");

		PrintWriter print = response.getWriter();
		print.println("<html><body>");
		print.println("<h1 align=\"center\">Student Records</h1>");

		if (request.getParameter("display") == null) {
			print.println("Choose a valid option.");

		} else {
			int displayOption = Integer.parseInt(request.getParameter("display"));
			switch (displayOption) {
			case 3:
				print.println("<table border=\"1\" align=\"center\"> ");
				print.println("<col width=\"130\">");
				print.println("<col width=\"130\">");
				print.println("<col width=\"130\">");
				print.println("<tr>" + "<th>ID</th>" + "<th>Name</th> " + "<th>Age</th>" + "</tr>");
				Query q = new Query("Student").addSort("Id", SortDirection.ASCENDING);
				PreparedQuery pq = ds.prepare(q);
				for (Entity u1 : pq.asIterable()) {
					int id = Integer.parseInt(u1.getProperty("Id").toString());
					String name = u1.getProperty("Name").toString();
					int age = Integer.parseInt(u1.getProperty("Age").toString());
					print.println("<tr><td>");
					print.print(id);
					print.println("</td><td>");
					print.print(name);
					print.println("</td><td>");
					print.print(age);
					print.println("</td></tr> ");
					print.println("<br>");
				}
				print.println("</table>");
				break;
			case 2:
				boolean invalidDetails = false;
				String getName = request.getParameter("name");
				invalidDetails = HelperClass.checkCharacter(getName);
				if (!invalidDetails) {
					print.println("<table border=\"1\" align=\"center\"> ");
					print.println("<col width=\"130\">");
					print.println("<col width=\"130\">");
					print.println("<col width=\"130\">");
					print.println("<tr>" + "<th>ID</th>" + "<th>Name</th> " + "<th>Age</th>" + "</tr>");
					Query q1 = new Query("Student").addSort("Id", SortDirection.ASCENDING).addFilter("Name",
							FilterOperator.EQUAL, getName);
					PreparedQuery pq1 = ds.prepare(q1);
					for (Entity u1 : pq1.asIterable()) {
						int id = Integer.parseInt(u1.getProperty("Id").toString());
						String name = u1.getProperty("Name").toString();
						int age = Integer.parseInt(u1.getProperty("Age").toString());
						print.println("<tr><td>");
						print.print(id);
						print.println("</td><td>");
						print.print(name);
						print.println("</td><td>");
						print.print(age);
						print.println("</td></tr> ");
						print.println("<br>");
					}
					print.println("</table>");
				}

				break;
			case 1:
				try {
					int getAge = Integer.parseInt(request.getParameter("age"));
					print.println("<table border=\"1\" align=\"center\"> ");
					print.println("<col width=\"130\">");
					print.println("<col width=\"130\">");
					print.println("<col width=\"130\">");
					print.println("<tr>" + "<th>ID</th>" + "<th>Name</th> " + "<th>Age</th>" + "</tr>");
					Query q2 = new Query("Student").addSort("Id", SortDirection.ASCENDING).addFilter("Age",
							FilterOperator.EQUAL, getAge);
					PreparedQuery pq2 = ds.prepare(q2);
					for (Entity u1 : pq2.asIterable()) {
						int id = Integer.parseInt(u1.getProperty("Id").toString());
						String name = u1.getProperty("Name").toString();
						int age = Integer.parseInt(u1.getProperty("Age").toString());
						print.println("<tr><td>");
						print.print(id);
						print.println("</td><td>");
						print.print(name);
						print.println("</td><td>");
						print.print(age);
						print.println("</td></tr> ");
						print.println("<br>");
					}
					print.println("</table>");
				} catch (NumberFormatException e) {
					print.println("Enter Valid Age");
				}

			}
		}
		print.println("<br><br><form method=\"get\" action=\"/home\">\r\n"
				+ "    <button type=\"submit\">Back</button>\r\n" + "</form>");
		print.println("</body></html>");

		print.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
