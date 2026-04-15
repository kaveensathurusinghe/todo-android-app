package com.example.todo.presentation.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.todo.R;
import com.example.todo.presentation.ui.main.MainActivity;
import com.example.todo.presentation.viewmodel.AuthViewModel;

import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class LoginActivity extends AppCompatActivity {

	private AuthViewModel authViewModel;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

		View root = findViewById(R.id.login_root);
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
		Button btnLogin = findViewById(R.id.btnLogin);
		TextView tvRegister = findViewById(R.id.tvRegister);

		btnLogin.setOnClickListener(v -> {
			v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
			String username = etUsername.getText().toString().trim();
			String password = etPassword.getText().toString();
			authViewModel.login(username, password);
		});

		tvRegister.setOnClickListener(v -> {
			v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
			startActivity(new Intent(this, RegisterActivity.class));
			overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
		});

		authViewModel.getLoginSuccess().observe(this, success -> {
			if (Boolean.TRUE.equals(success)) {
				startActivity(new Intent(this, MainActivity.class));
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
				finish();
			}
		});

		authViewModel.getErrorMessage().observe(this, message -> {
			if (message != null && !message.isEmpty()) {
				root.performHapticFeedback(HapticFeedbackConstants.REJECT);
				Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
