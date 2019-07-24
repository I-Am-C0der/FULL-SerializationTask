package com.student.register;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class Helper {
	public static boolean addStudent(String name, int age) {
		boolean invalidDetails = false;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		invalidDetails = checkCharacter(name);
		if (!invalidDetails) {
			Entity entity = new Entity("Student");
			entity.setProperty("Name", name);
			entity.setProperty("Age", age);
			datastore.put(entity);
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
