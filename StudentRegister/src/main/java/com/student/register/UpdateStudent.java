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
 * Servlet implementation class UpdateStudent
 */
@WebServlet(
	    name = "UpdateStudent",
	    urlPatterns = {"/update"}
	)
public class UpdateStudent extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6996557647110748832L;
	public static LinkedList<Student> studentLinkedListObject =new LinkedList<Student>();

	@SuppressWarnings("unchecked")
	static boolean updateDetails(int studentIds,String updatedName,int updateAge){
		boolean idExist=false;
		try {
			FileInputStream fis = new FileInputStream("StudentDetails.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			studentLinkedListObject.clear();
			studentLinkedListObject.addAll( (LinkedList<Student>) ois.readObject());
			ois.close();

		} catch (FileNotFoundException e) { }
		catch (Exception e) {  }
		try {
			if(!studentLinkedListObject.isEmpty())
			{
				for(Student updateDetailsObject :studentLinkedListObject) {
					if(updateDetailsObject.getId()==studentIds) {
						idExist=true;
					}
					if(idExist) {	
						boolean checkAllLettersAreCharacter =updatedName.chars().allMatch(Character::isLetter);
						if(!checkAllLettersAreCharacter) {
							throw new InputMismatchException();
						}
						updateDetailsObject.setName(updatedName);
						updateDetailsObject.setAge(updateAge);
						System.out.println("Record Updated");
					}
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("Enter Valid Details\n");
		} 
		try {
			FileOutputStream fos = new FileOutputStream("StudentDetails.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos); 
			oos.writeObject(studentLinkedListObject);                      
			oos.close();
		} catch (Exception e) {  }
		
		return idExist;
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
		boolean idExist=false;
		int updateId=Integer.parseInt(request.getParameter("id"));
		String updateName=request.getParameter("name");
		int updateAge=Integer.parseInt(request.getParameter("age"));
		
		idExist=updateDetails(updateId,updateName,updateAge);

		PrintWriter pw=response.getWriter();
		pw.println("<html><body>");

		if(idExist==false)
			pw.println("ID does not Exist.<br>Enter Valid Id.");
		else
			pw.println("The Desired Record is Updated.");


		pw.println("<br><br><form method=\"get\" action=\"index.html\">\r\n" + 
				"    <button type=\"submit\">Back</button>\r\n" + 
				"</form>");
		pw.println("</body></html>");
		pw.close();

	}

}
