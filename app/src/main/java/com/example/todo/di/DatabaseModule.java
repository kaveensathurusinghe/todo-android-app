package com.example.todo.di;

import android.content.Context;

import com.example.todo.data.local.dao.TodoDao;
import com.example.todo.data.local.dao.UserDao;
import com.example.todo.data.local.database.TodoDatabase;

public final class DatabaseModule {

	private DatabaseModule() {
		// No instances
	}

	public static TodoDatabase provideDatabase(Context context) {
		return TodoDatabase.getInstance(context);
	}

	public static TodoDao provideTodoDao(TodoDatabase database) {
		return database.todoDao();
	}

	public static UserDao provideUserDao(TodoDatabase database) {
		return database.userDao();
	}
}
