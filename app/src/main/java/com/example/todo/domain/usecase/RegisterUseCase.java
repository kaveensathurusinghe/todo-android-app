package com.example.todo.domain.usecase;

import com.example.todo.data.repository.AuthRepository;
import com.example.todo.utils.ValidatorUtils;

public class RegisterUseCase {

	private final AuthRepository authRepository;

	public RegisterUseCase(AuthRepository authRepository) {
		this.authRepository = authRepository;
	}

	public boolean execute(String username, String password) {
		if (!ValidatorUtils.isValidUsername(username) || !ValidatorUtils.isValidPassword(password)) {
			return false;
		}
		return authRepository.register(username.trim(), password);
	}
}
