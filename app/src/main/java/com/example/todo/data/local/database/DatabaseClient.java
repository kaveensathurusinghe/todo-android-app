package com.example.todo.data.local.database;

import android.content.Context;

public class DatabaseClient {

	private static DatabaseClient instance;

	private final TodoDatabase todoDatabase;

	private DatabaseClient(Context context) {
		this.todoDatabase = TodoDatabase.getInstance(context);
	}

	public static synchronized DatabaseClient getInstance(Context context) {
		if (instance == null) {
			instance = new DatabaseClient(context.getApplicationContext());
		}
		return instance;
	}

	public TodoDatabase getTodoDatabase() {
		return todoDatabase;
	}
}
