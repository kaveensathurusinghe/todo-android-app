package com.example.todo.data.repository;

import androidx.annotation.Nullable;

import com.example.todo.data.local.dao.UserDao;
import com.example.todo.data.local.entity.UserEntity;
import com.example.todo.data.local.preferences.SharedPrefsManager;
import com.example.todo.utils.EncryptionUtils;

public class AuthRepository {

	private final UserDao userDao;
	private final SharedPrefsManager sharedPrefsManager;

	public AuthRepository(UserDao userDao, SharedPrefsManager sharedPrefsManager) {
		this.userDao = userDao;
		this.sharedPrefsManager = sharedPrefsManager;
	}

	public boolean register(String username, String password) {
		UserEntity existing = userDao.getUserByUsername(username);
		if (existing != null) {
			return false;
		}
		String hashed = EncryptionUtils.hashPassword(password);
		UserEntity entity = new UserEntity(username, hashed);
		long id = userDao.insertUser(entity);
		return id > 0;
	}

	public boolean login(String username, String password) {
		UserEntity userEntity = userDao.getUserByUsername(username);
		if (userEntity == null) {
			return false;
		}
		boolean valid = EncryptionUtils.verifyPassword(password, userEntity.getPasswordHash());
		if (valid) {
			sharedPrefsManager.setLoggedInUsername(username);
		}
		return valid;
	}

	public void logout() {
		sharedPrefsManager.clearSession();
	}

	@Nullable
	public String getLoggedInUsername() {
		return sharedPrefsManager.getLoggedInUsername();
	}
}
