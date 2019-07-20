package com.student.register;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class HelperClass {
	public static boolean addStudent(String name, int age) {
		int studentId = 0;
		boolean invalidDetails = false;
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

		studentId = QueryHelperClass.queryOperation();
		invalidDetails = checkCharacter(name);
		if (!invalidDetails) {
			studentId++;
			Entity e = new Entity("Student");
			e.setProperty("Id", studentId);
			e.setProperty("Name", name);
			e.setProperty("Age", age);
			ds.put(e);
		}

		return invalidDetails;

	}

	public static boolean checkCharacter(String name) {
		boolean invalidDetails = false;
		boolean checkAllLettersAreCharacter = name.chars().allMatch(Character::isLetter);
		if (!checkAllLettersAreCharacter) {
			invalidDetails = true;
		}
		return invalidDetails;
	}

}
