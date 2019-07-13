package com.student.register;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayStudent
 */
@WebServlet(
	    name = "DisplayStudent",
	    urlPatterns = {"/display"}
	)
public class DisplayStudent extends HttpServlet {



	/**
	 * 
	 */
	private static final long serialVersionUID = 544049702759084408L;
	public static LinkedList<Student> studentLinkedListObject =new LinkedList<Student>();

	static Student filterName(String filterByName){
		Student filterName = null;
		for(Student filter1 :studentLinkedListObject) {
			if(filter1.getName().equals(filterByName)) {
				filterName=filter1;
				break;
			}
		}
		return filterName;	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			FileInputStream fis = new FileInputStream("StudentDetails.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			studentLinkedListObject.clear();
			studentLinkedListObject.addAll( (LinkedList<Student>) ois.readObject());
			ois.close();

		} catch (FileNotFoundException e) { }
		catch (Exception e) {  }
		
		response.setContentType("text/html");

		PrintWriter print=response.getWriter();
		print.println("<html><body>");  
		if(studentLinkedListObject.isEmpty()) {
			print.println("No Records Found.");
			print.println("<br>");
		}

		else {
			int displayOption=Integer.parseInt(request.getParameter("display"));
			if(displayOption==3) {
				for(Student displayAllObject : studentLinkedListObject) {
					print.println(displayAllObject.getId()+" "+displayAllObject.getName()+" "+displayAllObject.getAge());
					print.println("<br>");
				}
			} else if(displayOption==2) {
				String name=request.getParameter("name");
				Student displayName=filterName(name);
				print.println(displayName.getId()+" "+displayName.getName()+" "+displayName.getAge());
			} else if(displayOption==1) {
				int age=Integer.parseInt(request.getParameter("age"));
				for(Student ageEqualTo :studentLinkedListObject) {

					if(ageEqualTo.getAge()==age) {
						print.println(ageEqualTo.getId()+" "+ageEqualTo.getName()+" "+ageEqualTo.getAge());
						print.println("<br>");
					}
				}
			}
		}
		print.println("<br><br><form method=\"get\" action=\"/index.html\">\r\n" + 
				"    <button type=\"submit\">Back</button>\r\n" + 
				"</form>");
		print.println("</body></html>");  

		print.close(); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
