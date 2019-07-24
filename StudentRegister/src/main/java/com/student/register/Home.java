
package com.student.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Home
 */
@WebServlet(name = "Home", urlPatterns = { "/home" })
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");

		PrintWriter print = response.getWriter();
		HttpSession session = request.getSession(false);

		if (session != null) {
			

			print.println("<html><head>");
			print.println("<title>Student Corner</title>");
			print.println("</head><body>");
			print.print("<br><form align=\"right\" action=\"/logout\"> " + 
					"    <button type=\"submit\">Logout</button>\r\n" + 
					"</form>");
			print.println("<h1 align=\"center\">Student Corner</h1><br>");
			

			
			print.println("<form align=\"center\" action=\"/register\">"
					+ "<button type=\"submit\">Add Student</button></form><br>");

			print.println("<form align=\"center\" action=\"/removeinfo.html\">"
					+ "<button type=\"submit\">Remove Student</button></form><br>");

			print.println("<form align=\"center\" action=\"/updateinfo.html\">"
					+ "<button type=\"submit\">Update Student</button></form><br>");

			print.println("<form align=\"center\" action=\"/displayinfo.html\">"
					+ "<button type=\"submit\">Display Details</button></form>");
			print.println("</body></html>");
		} else {
			response.sendRedirect("login.html");
		}
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
