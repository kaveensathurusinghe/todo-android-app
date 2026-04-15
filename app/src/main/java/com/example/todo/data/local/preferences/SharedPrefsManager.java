package com.example.todo.data.local.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.todo.utils.Constants;

public class SharedPrefsManager {

	private final SharedPreferences sharedPreferences;

	public SharedPrefsManager(Context context) {
		this.sharedPreferences = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
	}

	public void setLoggedInUsername(String username) {
		sharedPreferences.edit().putString(Constants.KEY_LOGGED_IN_USERNAME, username).apply();
	}

	public String getLoggedInUsername() {
		return sharedPreferences.getString(Constants.KEY_LOGGED_IN_USERNAME, null);
	}

	public void clearSession() {
		sharedPreferences.edit().remove(Constants.KEY_LOGGED_IN_USERNAME).apply();
	}
}
