package com.example.todo.di;

import android.content.Context;

import com.example.todo.data.local.preferences.SharedPrefsManager;
import com.example.todo.data.repository.AuthRepository;
import com.example.todo.data.repository.TodoRepository;

public final class RepositoryModule {

	private RepositoryModule() {
		// No instances
	}

	public static AuthRepository provideAuthRepository(Context context) {
		SharedPrefsManager sharedPrefsManager = new SharedPrefsManager(context);
		return new AuthRepository(
				DatabaseModule.provideUserDao(DatabaseModule.provideDatabase(context)),
				sharedPrefsManager
		);
	}

	public static TodoRepository provideTodoRepository(Context context) {
		SharedPrefsManager sharedPrefsManager = new SharedPrefsManager(context);
		return new TodoRepository(
				DatabaseModule.provideTodoDao(DatabaseModule.provideDatabase(context)),
				sharedPrefsManager
		);
	}
}
