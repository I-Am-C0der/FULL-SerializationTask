package com.student.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub

		response.setContentType("text/html");

		PrintWriter print = response.getWriter();

		print.println("<html><head>");
		print.println("<title>Student Corner</title>");
		print.println("</head><body>");
		print.println("<h2 align=\"center\">Update Student Details</h2><br><br>");
		print.println("<form action=\"updateinfo\" method=\"post\">"
				+ "Enter ID: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"number\" name=\"id\"><br><br><br> "
				+ "Update Name: <input type=\"text\" name=\"name\"><br><br><br> "
				+ "Update Age:&nbsp;&nbsp;&nbsp; <input type=\"number\" name=\"age\"><br><br><br>"
				+ "<button type=\"submit\">Update Details</button>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type=\"submit\" formaction=\"/home\">Back</button></form>");
		print.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean invalidDetails = false;
		try {
			int updateId = Integer.parseInt(request.getParameter("id"));
			String updateName = request.getParameter("name");
			invalidDetails = HelperClass.checkCharacter(updateName);
			if (!invalidDetails) {
				int updateAge = Integer.parseInt(request.getParameter("age"));

				invalidDetails = QueryHelperClass.updateQueryOperation(updateId, updateAge, updateName);
			}
		} catch (NumberFormatException e) {
			invalidDetails = true;
		}
		PrintWriter pw = response.getWriter();
		pw.println("<html><body>");
		if (!invalidDetails)
			pw.println("The Desired Record is Updated.");
		else
			pw.println("Enter Valid Details");

		pw.println("<br><br><form method=\"get\" action=\"/home\">\r\n"
				+ "    <button type=\"submit\">Back</button>\r\n" + "</form>");
		pw.println("</body></html>");
		pw.close();

	}

}
