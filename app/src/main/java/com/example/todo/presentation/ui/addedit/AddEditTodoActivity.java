package com.example.todo.presentation.ui.addedit;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.todo.R;
import com.example.todo.domain.model.Todo;
import com.example.todo.presentation.viewmodel.AddEditTodoViewModel;
import com.example.todo.utils.Constants;
import com.example.todo.utils.DateUtils;

import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import java.text.ParseException;
import java.util.Calendar;

public class AddEditTodoActivity extends AppCompatActivity {

	private AddEditTodoViewModel viewModel;

	private EditText etTitle;
	private EditText etDescription;
	private EditText etDueDate;
	private EditText etTags;
	private Spinner spinnerPriority;

	private long todoId;
	private boolean isCompleted;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_edit_todo);

		viewModel = new ViewModelProvider(this).get(AddEditTodoViewModel.class);

		View root = findViewById(R.id.add_edit_root);
		root.setAlpha(0f);
		root.setTranslationY(40f);
		root.animate()
			.alpha(1f)
			.translationY(0f)
			.setDuration(280)
			.setInterpolator(new DecelerateInterpolator())
			.start();

		etTitle = findViewById(R.id.etTitle);
		etDescription = findViewById(R.id.etDescription);
		etDueDate = findViewById(R.id.etDueDate);
		etTags = findViewById(R.id.etTags);
		spinnerPriority = findViewById(R.id.spinnerPriority);
		Button btnSave = findViewById(R.id.btnSave);

		// Simple priority entries
		spinnerPriority.setAdapter(android.widget.ArrayAdapter.createFromResource(
				this,
				R.array.priority_entries,
				android.R.layout.simple_spinner_item
		));

		spinnerPriority.setSelection(1); // default medium

		etDueDate.setOnClickListener(v -> {
			v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
			showDatePicker();
		});

		todoId = getIntent().getLongExtra(Constants.EXTRA_TODO_ID, 0);
		if (todoId != 0) {
			viewModel.loadTodo(todoId);
		}

		viewModel.getTodo().observe(this, todo -> {
			if (todo != null) {
				etTitle.setText(todo.getTitle());
				etDescription.setText(todo.getDescription());
				etDueDate.setText(DateUtils.formatDate(todo.getDueDateMillis()));
				etTags.setText(todo.getTags());
				isCompleted = todo.isCompleted();
				int priorityIndex = 1;
				if (todo.getPriority() == Constants.PRIORITY_LOW) {
					priorityIndex = 0;
				} else if (todo.getPriority() == Constants.PRIORITY_HIGH) {
					priorityIndex = 2;
				}
				spinnerPriority.setSelection(priorityIndex);
			}
		});

		viewModel.getSaveSuccess().observe(this, success -> {
			View rootView = findViewById(R.id.add_edit_root);
			if (Boolean.TRUE.equals(success)) {
				if (rootView != null) {
					rootView.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
				}
				Toast.makeText(this, "Todo saved", Toast.LENGTH_SHORT).show();
				finish();
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			} else {
				if (rootView != null) {
					rootView.performHapticFeedback(HapticFeedbackConstants.REJECT);
				}
				Toast.makeText(this, "Unable to save todo. Please check inputs.", Toast.LENGTH_SHORT).show();
			}
		});

		btnSave.setOnClickListener(v -> {
			v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
			saveTodo();
		});
	}

	private void showDatePicker() {
		final Calendar calendar = Calendar.getInstance();
		DatePickerDialog dialog = new DatePickerDialog(
				this,
				(view, year, month, dayOfMonth) -> {
					Calendar c = Calendar.getInstance();
					c.set(year, month, dayOfMonth, 0, 0, 0);
					etDueDate.setText(DateUtils.formatDate(c.getTimeInMillis()));
				},
				calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		dialog.show();
	}

	private void saveTodo() {
		String title = etTitle.getText().toString().trim();
		String description = etDescription.getText().toString().trim();
		String dueDateText = etDueDate.getText().toString().trim();
		String tags = etTags.getText().toString().trim();

		long dueDateMillis;
		try {
			if (dueDateText.isEmpty()) {
				Toast.makeText(this, "Please select a due date", Toast.LENGTH_SHORT).show();
				return;
			}
			dueDateMillis = DateUtils.parseDate(dueDateText);
		} catch (ParseException e) {
			Toast.makeText(this, "Invalid date format", Toast.LENGTH_SHORT).show();
			return;
		}

		int priorityIndex = spinnerPriority.getSelectedItemPosition();
		int priority = Constants.PRIORITY_MEDIUM;
		if (priorityIndex == 0) {
			priority = Constants.PRIORITY_LOW;
		} else if (priorityIndex == 2) {
			priority = Constants.PRIORITY_HIGH;
		}

		Todo todo = new Todo(
			todoId,
			title,
			description,
			dueDateMillis,
			tags,
			priority,
			isCompleted
		);

		viewModel.saveTodo(todo);
	}
}
