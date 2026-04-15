package com.example.todo.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.todo.data.repository.TodoRepository;
import com.example.todo.di.RepositoryModule;
import com.example.todo.domain.model.Todo;
import com.example.todo.domain.usecase.AddTodoUseCase;
import com.example.todo.domain.usecase.UpdateTodoUseCase;

public class AddEditTodoViewModel extends AndroidViewModel {

	private final AddTodoUseCase addTodoUseCase;
	private final UpdateTodoUseCase updateTodoUseCase;
	private final TodoRepository todoRepository;

	private final MutableLiveData<Todo> todoLiveData = new MutableLiveData<>();
	private final MutableLiveData<Boolean> saveSuccess = new MutableLiveData<>();

	public AddEditTodoViewModel(@NonNull Application application) {
		super(application);
		todoRepository = RepositoryModule.provideTodoRepository(application);
		addTodoUseCase = new AddTodoUseCase(todoRepository);
		updateTodoUseCase = new UpdateTodoUseCase(todoRepository);
	}

	public LiveData<Todo> getTodo() {
		return todoLiveData;
	}

	public LiveData<Boolean> getSaveSuccess() {
		return saveSuccess;
	}

	public void loadTodo(long id) {
		Todo todo = todoRepository.getTodoById(id);
		todoLiveData.setValue(todo);
	}

	public void saveTodo(Todo todo) {
		boolean success;
		if (todo.getId() == 0) {
			success = addTodoUseCase.execute(todo);
		} else {
			success = updateTodoUseCase.execute(todo);
		}
		saveSuccess.setValue(success);
	}
}
