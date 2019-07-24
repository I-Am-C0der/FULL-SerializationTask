package com.student.register;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class QueryHelper {
	static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	static Query query = new Query("Student");
	static PreparedQuery preparedQuery = datastore.prepare(query);

	public static boolean updateQueryOperation(long updateId, int updateAge, String updateName) {
		boolean invalidId = false;
		System.out.println(updateName);
		int check = 0;
		for (Entity entity : preparedQuery.asIterable()) {
			long getId = entity.getKey().getId();
			if (updateId == getId) {
				check = 1;
				if(updateName!=null)
				entity.setProperty("Name", updateName);
				entity.setProperty("Age", updateAge);
				datastore.put(entity);
				break;
			}
		}
		if (check == 0)
			invalidId = true;
		return invalidId;
	}

	public static void removeByIdQuery(long removeId) {
		for (Entity entity : preparedQuery.asIterable()) {
			long getId = entity.getKey().getId();
			if (removeId == getId) {
				datastore.delete(entity.getKey());
				break;
			}
		}
		
	}

	public static boolean removeAllQuery() {
		boolean removeAll = true;
		for (Entity entity : preparedQuery.asIterable()) {
			datastore.delete(entity.getKey());
		}
		return removeAll;
	}
}
