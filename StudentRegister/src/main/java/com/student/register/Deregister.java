package com.student.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.setContentType("text/html");

		PrintWriter print = response.getWriter();

		print.println("<html><head>");
		print.println("<title>Student Corner</title>");
		print.println("</head><body>");
		print.println("<h2 align=\"center\">Remove Student Details</h2><br><br>");
		print.println("<form action=\"removeinfo\" method=\"post\">"
				+ "<input type=\"radio\" name=\"remove\" value=\"1\">&nbsp;Remove by ID<br>"
				+ "ID: <input type=\"number\" name=\"id\"><br>"
				+ "<br> <input type=\"radio\" name=\"remove\" value=\"2\">&nbsp;Remove All<br><br>"
				+ "<button type=\"submit\">Remove</button>"
				+ "		<button type=\"submit\" formaction=\"/home\">Back</button></form>");
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
		boolean invalidId = false;
		boolean invalidOption = false;
		boolean removeAll = false;
		if (request.getParameter("remove") == null)
			invalidOption = true;
		else {
			int removeOption = Integer.parseInt(request.getParameter("remove"));
			switch (removeOption) {
			case 1:
				try {
					int removeId = Integer.parseInt(request.getParameter("id"));
					invalidId = QueryHelperClass.removeByIdQuery(removeId);

				} catch (NumberFormatException e) {
					invalidId = true;
				}
				break;
			case 2:

				removeAll = QueryHelperClass.removeAllQuery();
				
			}
		}
		pw.println("<html><body>");
		if (invalidOption)
			pw.println("Choose a valid option.");

		else if (removeAll)
			pw.println("All Records are removed.");

		else if (invalidId)
			pw.println("Enter Valid Id");

		else
			pw.println("Desired Record is removed.");

		pw.println("<br><br><form method=\"get\" action=\"/home\">\r\n"
				+ "    <button type=\"submit\">Back</button>\r\n" + "</form>");
		pw.println("</body></html>");
		pw.close();

	}
}
