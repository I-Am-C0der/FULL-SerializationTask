package com.student.details;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class StudentDetails {
	static Scanner sc;

	public static LinkedList<Student> al=new LinkedList<Student>();
	static int id=0;
	
	static void addStudent(){
		Iterator<Student> itr = al.iterator();
		Student sId;
		while(itr.hasNext()) {
			sId=itr.next();
			if(!itr.hasNext()) {
				id=sId.getId();
			}
		}
		sc =  new Scanner(System.in);
		try {
		System.out.println("Enter Student Details");
		id++;
		System.out.println("ID :"+ id);
		System.out.print("Name :");
		String name=sc.next();
		boolean allLetters = name.chars().allMatch(Character::isLetter);
		if(!allLetters) {
			throw new InputMismatchException();
		}
		System.out.print("Age :");
		int age=sc.nextInt();
		System.out.println();
		Student s=new Student(id,name,age);
		al.add(s);
		} catch (InputMismatchException e) {
			System.out.println("Enter Valid Details\n");
			id--;
			addStudent();
		} 
	}

	static void removeStudent(){
		sc =  new Scanner(System.in);
		int rAll;
		try {
			
		System.out.println("Remove Student Details");
		if(al.isEmpty())
			System.out.println("No Records Found\n");
		else {
		System.out.println("1. Remove By ID\n2. Remove All\n");
		rAll=sc.nextInt();
		switch(rAll) {
		case 1 :
			System.out.print("Enter ID :");
			int rid=sc.nextInt();
			al.removeIf(e -> e.getId()==(rid));
			System.out.println("Desired Record Removed");
			break;
		
		case 2:
			al.clear();
			System.out.println("All records are removed.");
			break;
			
		default :
			System.out.println("Enter valid option");
		
		}
		}
		} catch (InputMismatchException e) {
			System.out.println("Enter Valid ID\n");
			removeStudent();
		} 

	}

	static void updateStudent(){
		sc =  new Scanner(System.in);
		try {
		System.out.println("\tUpdate Details");
		if(al.isEmpty())
			System.out.println("No Records Found\n");
		else {
		System.out.print("Enter ID :");
		int ids;
		ids=sc.nextInt();

		for(Student a :al) {
			if(a.getId()==ids) {
				System.out.print("Update Name :");
				String uName=sc.next();
				boolean allLetters =uName.chars().allMatch(Character::isLetter);
				if(!allLetters) {
					throw new InputMismatchException();
				}
				a.setName(uName);
				System.out.print("Update Age :");
				a.setAge(sc.nextInt());
				System.out.println("Record Updated");
			}
		}
		}
		} catch (InputMismatchException e) {
			System.out.println("Enter Valid Details\n");
			updateStudent();
		} 

	}

	static void displayStudents(){
		sc =  new Scanner(System.in);
		System.out.println("\n\t Student Information");
		if(al.isEmpty())
			System.out.println("No Records Found\n");
		else {
			try {
		System.out.println("Filter :");
		System.out.println("1.Name\n2.Age\n3.Display All");
		int filter=sc.nextInt();
		switch(filter) {
		case 1:
			System.out.println("Name :");
			String fName= sc.next();
			boolean allLetters = fName.chars().allMatch(Character::isLetter);
			if(!allLetters) {
				throw new InputMismatchException();
			}
			filterName(fName);
			break;
			
		case 2:
			System.out.println("Age :");
			int fAge= sc.nextInt();
			filterAge(fAge);;
			break;
			
		case 3:
		for(Student a : al) {
			System.out.println(a.getId()+" "+a.getName()+" "+a.getAge());
		}
		System.out.println();
		break;
		
		default:
			System.out.println("Enter Valid Option\n");
			displayStudents();
		}
			} catch (InputMismatchException e) {
				System.out.println("Enter Valid Details\n");
				displayStudents();
			} 
		}
	}

	static void filterName(String fName){
		for(Student filter1 :al) {
			if(filter1.getName().equals(fName)) {
				System.out.println(filter1.getId()+" "+filter1.getName()+" "+filter1.getAge());
			}
		}	
	}
	
	static void filterAge(int fAge) {
		System.out.println("1.Filter age greater than "+fAge+"\n2.Filter age less than "+fAge+"\n3.Filter age equal to "+fAge);
		System.out.print("Select Option :");
		int fOption=sc.nextInt();
		switch(fOption) {
		case 1:
			for(Student fGreater :al) {
				if(fGreater.getAge()>fAge) {
					System.out.println(fGreater.getId()+" "+fGreater.getName()+" "+fGreater.getAge());
				}
			}
			break;
			
		case 2:
			for(Student fLesser :al) {
				if(fLesser.getAge()<fAge) {
					System.out.println(fLesser.getId()+" "+fLesser.getName()+" "+fLesser.getAge());
				}
			}
			break;
			
		case 3:
			for(Student fEqual :al) {
				if(fEqual.getAge()==fAge) {
					System.out.println(fEqual.getId()+" "+fEqual.getName()+" "+fEqual.getAge());
				}
			}
			break;
			
		default :
			System.out.println("Enter Valid Option");
			filterAge(fAge);
		}
			
	}
	
	
	public static void main(String[] args) {
		boolean input=true;
		sc =  new Scanner(System.in);
		try {
            FileInputStream fis = new FileInputStream("StudentDetails.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            al.addAll( (LinkedList<Student>) ois.readObject());
            ois.close();
 
        } catch (FileNotFoundException e) { }
		catch (Exception e) { e.printStackTrace(); }
		while(input==true) {

			System.out.println("\tStudent Corner");
			
			System.out.println();
			System.out.println("1. Add Student Details\n2. Remove Student Details\n3. Update Student Details"
					+ "\n4. Display Details\n5. Exit");
			System.out.println("Select Option :");
			int option;
			try {
				option = sc.nextInt();

				switch(option) {
				case 1 :

					addStudent();
					input=true;
					break;

				case 2 :
					removeStudent();
					input=true;
					break;

				case 3 :
					updateStudent();
					input=true;
					break;

				case 4 :
					displayStudents();
					input=true;
					break;

				case 5:
					input=false;
					try {
			            FileOutputStream fs = new FileOutputStream("StudentDetails.txt");
			            ObjectOutputStream os = new ObjectOutputStream(fs); 
			            os.writeObject(al);                      
			            os.close();
			        } catch (Exception e) 
			            { e.printStackTrace(); }
					break;

				default:
					System.out.println("Enter a valid option");
					input=false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Enter Valid Option");
				main(args);
			} 
		} 
	}
}
