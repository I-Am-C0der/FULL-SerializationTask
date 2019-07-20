
package com.student.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
@WebServlet(
		name = "Home",
		urlPatterns = {"/home"}
		)
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        response.setContentType("text/html");

		PrintWriter print=response.getWriter();

		print.println("<html><head>");
		print.println("<title>Student Corner</title>");
		print.println("</head><body>");
		print.println("<h1 align=\"center\">Student Corner</h1>");
		print.println("<form align=\"center\" action=\"/register\">" +
		"<button type=\"submit\">Add Student</button></form><br>");
		
		print.println("<form align=\"center\" action=\"/removeinfo\">" +
		"<button type=\"submit\">Remove Student</button></form><br>");
			
		print.println("<form align=\"center\" action=\"/updateinfo\">" +
		"<button type=\"submit\">Update Student</button></form><br>");
			
		print.println("<form align=\"center\" action=\"/displayinfo.html\">" +
		"<button type=\"submit\">Display Details</button></form>");
		print.println("</body></html>");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
