package com.student.register;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class QueryHelperClass {
	static DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	static Query q = new Query("Student");
	static PreparedQuery pq = ds.prepare(q);

	public static int queryOperation() {
		int studentId = 0;

		for (Entity u1 : pq.asIterable()) {
			int id = Integer.parseInt(u1.getProperty("Id").toString());
			if (id > studentId) {
				studentId = id;
			}
		}
		return studentId;
	}

	public static boolean updateQueryOperation(int updateId, int updateAge, String updateName) {
		boolean invalidId = false;
		int check = 0;
		for (Entity u1 : pq.asIterable()) {
			int getId = Integer.parseInt(u1.getProperty("Id").toString());
			if (updateId == getId) {
				check = 1;
				u1.setProperty("Name", updateName);
				u1.setProperty("Age", updateAge);
				ds.put(u1);
				break;
			}
		}
		if (check == 0)
			invalidId = true;
		return invalidId;
	}

	public static boolean removeByIdQuery(int removeId) {
		boolean invalidDetails = false;
		int check = 0;
		for (Entity u1 : pq.asIterable()) {
			int getId = Integer.parseInt(u1.getProperty("Id").toString());
			if (removeId == getId) {
				check = 1;
				ds.delete(u1.getKey());
				break;
			}
		}
		if (check == 0)
			invalidDetails = true;
		return invalidDetails;
	}

	public static boolean removeAllQuery() {
		boolean removeAll = true;
		for (Entity u1 : pq.asIterable()) {
			ds.delete(u1.getKey());
		}
		return removeAll;
	}
}
