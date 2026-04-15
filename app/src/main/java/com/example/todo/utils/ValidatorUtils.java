package com.example.todo.utils;

import android.text.TextUtils;

public final class ValidatorUtils {

	private ValidatorUtils() {
		// Utility class
	}

	public static boolean isValidUsername(String username) {
		return !TextUtils.isEmpty(username) && username.trim().length() >= 3;
	}

	public static boolean isValidPassword(String password) {
		return !TextUtils.isEmpty(password) && password.length() >= 6;
	}

	public static boolean isValidTodoTitle(String title) {
		return !TextUtils.isEmpty(title) && title.trim().length() >= 1;
	}
}
