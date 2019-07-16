package com.student.register;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.*;


/**
 * Servlet implementation class AddStudent
 */
@WebServlet(
		name = "AddStudent",
		urlPatterns = {"/register"}
		)
public class AddStudent extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = -286060870575219564L;


	static int studentId=0;
	static void addStudent(String name,int age){
		DatastoreService ds= DatastoreServiceFactory.getDatastoreService();

		Query q=new Query("Student");
		PreparedQuery pq=ds.prepare(q);
		for(Entity u1: pq.asIterable()) {
			int id = Integer.parseInt(u1.getProperty("Id").toString());
			if(id>studentId) {
				studentId=id;
			}
		}

		try {
			studentId++;

			boolean checkAllLettersAreCharacter = name.chars().allMatch(Character::isLetter);
			if(!checkAllLettersAreCharacter) {
				throw new InputMismatchException();
			}

			Entity e = new Entity("Student");
			e.setProperty("Id", studentId);
			e.setProperty("Name", name);
			e.setProperty("Age", age);
			ds.put(e);

		} catch (InputMismatchException e) {
			System.out.println("Enter Valid Details\n");
			studentId--;
		} 
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        response.setContentType("text/html");

		PrintWriter pw=response.getWriter();

		pw.println("<html><head>");
		pw.println("<title>Student Corner</title>");
		pw.println("</head><body>");
		pw.println("<h2 align=\"center\">Add Student Details</h2><br><br>");

		pw.println("<form action=\"register\" method=\"post\">" +
				"Name: <input type=\"text\" name=\"name\"><br><br><br> "
				+ "Age: &nbsp;&nbsp;&nbsp;<input type=\"number\" name=\"age\"><br><br><br> " +
				"<button type=\"submit\">Add Student</button>"+
				"&nbsp;&nbsp;&nbsp;<button type=\"submit\" formaction=\"/home\">Back</button></form>");
		pw.println("</body></html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		addStudent(name,age);
		PrintWriter print=response.getWriter();
		print.println("<html><body>");
		print.println("Record added to Student Database");
		print.println("<br><br><form method=\"get\" action=\"/home\">\r\n" + 
				"    <button type=\"submit\">Back</button>\r\n" + 
				"</form>");
		print.println("</body></html>");
		print.close();
	}
}
