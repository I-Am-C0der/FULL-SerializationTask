package com.student.register;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveStudent
 */
@WebServlet(
	    name = "RemoveStudent",
	    urlPatterns = {"/remove"}
	)
public class RemoveStudent extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4942312224163506173L;
	public static LinkedList<Student> studentLinkedListObject =new LinkedList<Student>();


	@SuppressWarnings("unchecked")
	static boolean removeById(int removeId){
		boolean idExist=false;
		try {
			FileInputStream fis = new FileInputStream("StudentDetails.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			studentLinkedListObject.clear();
			studentLinkedListObject.addAll( (LinkedList<Student>) ois.readObject());
			ois.close();

		} catch (FileNotFoundException e) { }
		catch (Exception e) { e.printStackTrace(); }
		try {

			if(!studentLinkedListObject.isEmpty())
			{ for(Student idCheck :studentLinkedListObject) {

				if(idCheck.getId()==removeId) {
					idExist=true;
					break;
				}
			}
			}
			System.out.println(idExist);
			if(idExist==true)
				studentLinkedListObject.removeIf(e -> e.getId()==(removeId));


		} catch (InputMismatchException e) {
			System.out.println("Enter Valid ID\n");

		} 
		try {
			FileOutputStream fos = new FileOutputStream("StudentDetails.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos); 
			oos.writeObject(studentLinkedListObject);                      
			oos.close();
		} catch (Exception e) {  }

		return idExist;
	}
	
	@SuppressWarnings("unchecked")
	static void removeAll(){
		try {
			FileInputStream fis = new FileInputStream("StudentDetails.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			studentLinkedListObject.clear();
			studentLinkedListObject.addAll( (LinkedList<Student>) ois.readObject());
			ois.close();

		} catch (FileNotFoundException e) { }
		catch (Exception e) { e.printStackTrace(); }

		studentLinkedListObject.clear();


		try {
			FileOutputStream fos = new FileOutputStream("StudentDetails.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos); 
			oos.writeObject(studentLinkedListObject);                      
			oos.close();
		} catch (Exception e) {  }

	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int removeOption =Integer.parseInt( request.getParameter("remove"));
		boolean idExist=false;
		if(removeOption==1) {

			int removeId = Integer.parseInt(request.getParameter("id"));
			idExist=removeById(removeId);
		}
		else {
			idExist=true;
			removeAll();
		}

		PrintWriter pw=response.getWriter();
		pw.println("<html><body>");

		if(idExist==false)
			pw.println("ID does not Exist.<br>Enter Valid Id.");
		else
			pw.println("Desired Records are removed.");


		pw.println("<br><br><form method=\"get\" action=\"/index.html\">\r\n" + 
				"    <button type=\"submit\">Back</button>\r\n" + 
				"</form>");
		pw.println("</body></html>");
		pw.close();

	}
}

