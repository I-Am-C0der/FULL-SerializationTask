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
	static Scanner scanner;

	public static LinkedList<Student> studentLinkedListObject =new LinkedList<Student>();
	static int studentId=0;

	//Method for adding student details
	static void addStudent(){
		Iterator<Student> iterator = studentLinkedListObject.iterator();
		Student studId;
		while(iterator.hasNext()) {
			studId=iterator.next();
			if(!iterator.hasNext()) {
				studentId=studId.getId();
			}
		}

		scanner =  new Scanner(System.in);
		try {
			System.out.println("Enter Student Details");
			studentId++;
			System.out.println("ID :"+ studentId);
			System.out.print("Name :");
			String name=scanner.next();
			boolean checkAllLettersAreCharacter = name.chars().allMatch(Character::isLetter);
			if(!checkAllLettersAreCharacter) {
				throw new InputMismatchException();
			}
			System.out.print("Age :");
			int age=scanner.nextInt();
			System.out.println();
			Student studentObject=new Student(studentId,name,age);
			studentLinkedListObject.add(studentObject);
		} catch (InputMismatchException e) {
			System.out.println("Enter Valid Details\n");
			studentId--;
			addStudent();
		} 
	}

	//Method to remove student details.
	static void removeStudent(){
		scanner =  new Scanner(System.in);
		int removeOption;
		try {

			System.out.println("Remove Student Details");
			if(studentLinkedListObject.isEmpty())
				System.out.println("No Records Found\n");
			else {
				System.out.println("1. Remove By ID\n2. Remove All\n");
				removeOption=scanner.nextInt();
				switch(removeOption) {
				case 1 :
					System.out.print("Enter ID :");
					int removeStudent=scanner.nextInt();
					studentLinkedListObject.removeIf(e -> e.getId()==(removeStudent));
					System.out.println("Desired Record Removed");
					break;

				case 2:
					studentLinkedListObject.clear();
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

	//Method to update student details.
	static void updateStudent(){
		scanner =  new Scanner(System.in);
		try {
			System.out.println("\tUpdate Details");
			if(studentLinkedListObject.isEmpty())
				System.out.println("No Records Found\n");
			else {
				System.out.print("Enter ID :");
				int studentIds;
				studentIds=scanner.nextInt();

				for(Student updateDetailsObject :studentLinkedListObject) {
					if(updateDetailsObject.getId()==studentIds) {
						System.out.print("Update Name :");
						String updatedName=scanner.next();
						boolean checkAllLettersAreCharacter =updatedName.chars().allMatch(Character::isLetter);
						if(!checkAllLettersAreCharacter) {
							throw new InputMismatchException();
						}
						updateDetailsObject.setName(updatedName);
						System.out.print("Update Age :");
						updateDetailsObject.setAge(scanner.nextInt());
						System.out.println("Record Updated");
					}
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("Enter Valid Details\n");
			updateStudent();
		} 

	}

	//Method to display student details
	static void displayStudents(){
		scanner =  new Scanner(System.in);
		System.out.println("\n\t Student Information");
		if(studentLinkedListObject.isEmpty())
			System.out.println("No Records Found\n");
		else {
			try {
				System.out.println("Filter :");
				System.out.println("1.Name\n2.Age\n3.Display All");
				int filterOption=scanner.nextInt();
				switch(filterOption) {
				case 1:
					System.out.println("Name :");
					String filterByName= scanner.next();
					boolean checkAllLettersAreCharacter = filterByName.chars().allMatch(Character::isLetter);
					if(!checkAllLettersAreCharacter) {
						throw new InputMismatchException();
					}
					filterName(filterByName);
					break;

				case 2:
					System.out.println("Age :");
					int filterByAge= scanner.nextInt();
					filterAge(filterByAge);
					break;

				case 3:
					for(Student displayAllObject : studentLinkedListObject) {
						System.out.println(displayAllObject.getId()+" "+displayAllObject.getName()+" "+displayAllObject.getAge());
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

	//Method to filter by Name
	static void filterName(String filterByName){
		for(Student filter1 :studentLinkedListObject) {
			if(filter1.getName().equals(filterByName)) {
				System.out.println(filter1.getId()+" "+filter1.getName()+" "+filter1.getAge());
			}
		}	
	}

	//Method to filter by Age
	static void filterAge(int filterByAge) {
		System.out.println("1.Filter age greater than "+filterByAge+"\n2.Filter age less than "+filterByAge+"\n3.Filter age equal to "+filterByAge);
		System.out.print("Select Option :");
		int fOption=scanner.nextInt();
		switch(fOption) {
		case 1:
			for(Student ageGreaterThan :studentLinkedListObject) {
				if(ageGreaterThan.getAge()>filterByAge) {
					System.out.println(ageGreaterThan.getId()+" "+ageGreaterThan.getName()+" "+ageGreaterThan.getAge());
				}
			}
			break;

		case 2:
			for(Student ageLesserThan :studentLinkedListObject) {
				if(ageLesserThan.getAge()<filterByAge) {
					System.out.println(ageLesserThan.getId()+" "+ageLesserThan.getName()+" "+ageLesserThan.getAge());
				}
			}
			break;

		case 3:
			for(Student ageEqualTo :studentLinkedListObject) {
				if(ageEqualTo.getAge()==filterByAge) {
					System.out.println(ageEqualTo.getId()+" "+ageEqualTo.getName()+" "+ageEqualTo.getAge());
				}
			}
			break;

		default :
			System.out.println("Enter Valid Option");
			filterAge(filterByAge);
		}

	}


	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		boolean input=true;
		scanner =  new Scanner(System.in);

		//To read the file and add objects from file to the collection
		try {
			FileInputStream fis = new FileInputStream("StudentDetails.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			studentLinkedListObject.addAll( (LinkedList<Student>) ois.readObject());
			ois.close();

		} catch (FileNotFoundException e) { }
		catch (Exception e) { e.printStackTrace(); }

		//Main menu 
		while(input==true) {

			System.out.println("\tStudent Corner");

			System.out.println();
			System.out.println("1. Add Student Details\n2. Remove Student Details\n3. Update Student Details"
					+ "\n4. Display Details\n5. Exit");
			System.out.println("Select Option :");
			int option;
			try {
				option = scanner.nextInt();

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

					//To write objects into the file
					try {
						FileOutputStream fos = new FileOutputStream("StudentDetails.txt");
						ObjectOutputStream oos = new ObjectOutputStream(fos); 
						oos.writeObject(studentLinkedListObject);                      
						oos.close();
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
