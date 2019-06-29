package com.student.details;

import java.io.Serializable;

public class Student implements Serializable {

	private String name;
	private int id;
	private int age;

	public Student(int id, String name, int age) {
		this.id=id;
		this.name= name;
		this.age=age;
	}
	
	public String getName() { 
	        return name; 
	    } 

	    public void setName(String name) { 
	        this.name = name; 
	    } 
	    
	    public int getId() { 
	        return id; 
	    } 

	    public int getAge() { 
	        return age; 
	    } 

	    public void setAge(int age) { 
	        this.age = age; 
	    } 
}