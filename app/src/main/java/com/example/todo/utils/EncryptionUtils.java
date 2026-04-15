package com.example.todo.utils;

import org.mindrot.jbcrypt.BCrypt;

public final class EncryptionUtils {

	private static final int LOG_ROUNDS = 10;

	private EncryptionUtils() {
		// Utility class
	}

	public static String hashPassword(String plainTextPassword) {
		if (plainTextPassword == null) {
			throw new IllegalArgumentException("Password cannot be null");
		}
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(LOG_ROUNDS));
	}

	public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
		if (plainTextPassword == null || hashedPassword == null || hashedPassword.isEmpty()) {
			return false;
		}
		return BCrypt.checkpw(plainTextPassword, hashedPassword);
	}
}
