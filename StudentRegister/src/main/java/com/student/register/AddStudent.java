package com.student.register;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddStudent
 */
@WebServlet(
	    name = "AddStudent",
	    urlPatterns = {"/add"}
	)
public class AddStudent extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -286060870575219564L;

	public static LinkedList<Student> studentLinkedListObject =new LinkedList<Student>();

	static int studentId=0;
	@SuppressWarnings("unchecked")
	static void addStudent(String name,int age){
		try {
			
			FileInputStream fis = new FileInputStream("StudentDetails.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			studentLinkedListObject.clear();
			studentLinkedListObject.addAll( (LinkedList<Student>) ois.readObject());
			ois.close();
			
		} catch (FileNotFoundException e) { }
		catch (Exception e) {  }
		
		Iterator<Student> iterator = studentLinkedListObject.iterator();
		Student studId;
		while(iterator.hasNext()) {
			studId=iterator.next();
			if(!iterator.hasNext()) {
				studentId=studId.getId();
			}
		}


		try {
			studentId++;

			boolean checkAllLettersAreCharacter = name.chars().allMatch(Character::isLetter);
			if(!checkAllLettersAreCharacter) {
				throw new InputMismatchException();
			}

			Student studentObject=new Student(studentId,name,age);
			studentLinkedListObject.add(studentObject);
		} catch (InputMismatchException e) {
			System.out.println("Enter Valid Details\n");
			studentId--;

		} 
		try {
			FileOutputStream fos = new FileOutputStream("StudentDetails.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos); 
			oos.writeObject(studentLinkedListObject);                      
			oos.close();
		} catch (Exception e) { }

	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter print=response.getWriter();
		print.println("<html><body>");
		print.println("Record added to Student Database");
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

		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		addStudent(name,age);
		doGet(request, response);		
	}
}
