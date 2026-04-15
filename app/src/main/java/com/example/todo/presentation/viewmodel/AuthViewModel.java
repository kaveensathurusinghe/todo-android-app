package com.example.todo.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.todo.di.AppModule;

public class AuthViewModel extends AndroidViewModel {

	private final com.example.todo.domain.usecase.LoginUseCase loginUseCase;
	private final com.example.todo.domain.usecase.RegisterUseCase registerUseCase;

	private final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();
	private final MutableLiveData<Boolean> registerSuccess = new MutableLiveData<>();
	private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

	public AuthViewModel(@NonNull Application application) {
		super(application);
		loginUseCase = AppModule.provideLoginUseCase(application);
		registerUseCase = AppModule.provideRegisterUseCase(application);
	}

	public LiveData<Boolean> getLoginSuccess() {
		return loginSuccess;
	}

	public LiveData<Boolean> getRegisterSuccess() {
		return registerSuccess;
	}

	public LiveData<String> getErrorMessage() {
		return errorMessage;
	}

	public void login(String username, String password) {
		boolean success = loginUseCase.execute(username, password);
		if (success) {
			loginSuccess.setValue(true);
		} else {
			errorMessage.setValue("Invalid username or password");
		}
	}

	public void register(String username, String password) {
		boolean success = registerUseCase.execute(username, password);
		if (success) {
			registerSuccess.setValue(true);
		} else {
			errorMessage.setValue("Registration failed. Username may already exist or data invalid.");
		}
	}
}
