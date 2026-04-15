package com.example.todo.presentation.ui.auth;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.todo.R;
import com.example.todo.presentation.viewmodel.AuthViewModel;

import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class RegisterActivity extends AppCompatActivity {

	private AuthViewModel authViewModel;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

		View root = findViewById(R.id.register_root);
		root.setAlpha(0f);
		root.setTranslationY(40f);
		root.animate()
			.alpha(1f)
			.translationY(0f)
			.setDuration(280)
			.setInterpolator(new DecelerateInterpolator())
			.start();

		EditText etUsername = findViewById(R.id.etUsername);
		EditText etPassword = findViewById(R.id.etPassword);
		EditText etConfirmPassword = findViewById(R.id.etConfirmPassword);
		Button btnRegister = findViewById(R.id.btnRegister);

		btnRegister.setOnClickListener(v -> {
			v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
			String username = etUsername.getText().toString().trim();
			String password = etPassword.getText().toString();
			String confirmPassword = etConfirmPassword.getText().toString();

			if (!password.equals(confirmPassword)) {
				root.performHapticFeedback(HapticFeedbackConstants.REJECT);
				Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
				return;
			}

			authViewModel.register(username, password);
		});

		authViewModel.getRegisterSuccess().observe(this, success -> {
			if (Boolean.TRUE.equals(success)) {
				Toast.makeText(this, "Registration successful. You can now log in.", Toast.LENGTH_SHORT).show();
				finish();
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			}
		});

		authViewModel.getErrorMessage().observe(this, message -> {
			if (message != null && !message.isEmpty()) {
				Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
