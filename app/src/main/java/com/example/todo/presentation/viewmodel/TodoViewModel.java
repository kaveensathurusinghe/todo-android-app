package com.example.todo.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.todo.di.AppModule;
import com.example.todo.domain.model.Todo;
import com.example.todo.domain.usecase.DeleteTodoUseCase;
import com.example.todo.domain.usecase.GetTodosUseCase;
import com.example.todo.domain.usecase.UpdateTodoUseCase;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {

	public enum SortType {
		DUE_DATE,
		PRIORITY
	}

	private final GetTodosUseCase getTodosUseCase;
	private final UpdateTodoUseCase updateTodoUseCase;
	private final DeleteTodoUseCase deleteTodoUseCase;

	private final MutableLiveData<SortType> sortType = new MutableLiveData<>(SortType.DUE_DATE);

	public TodoViewModel(@NonNull Application application) {
		super(application);
		getTodosUseCase = AppModule.provideGetTodosUseCase(application);
		updateTodoUseCase = AppModule.provideUpdateTodoUseCase(application);
		deleteTodoUseCase = AppModule.provideDeleteTodoUseCase(application);
	}

	public LiveData<List<Todo>> getTodos() {
		if (sortType.getValue() == SortType.PRIORITY) {
			return getTodosUseCase.getTodosSortedByPriority();
		} else {
			return getTodosUseCase.getTodosSortedByDueDate();
		}
	}

	public void setSortType(SortType sortType) {
		this.sortType.setValue(sortType);
	}

	public void toggleTodoCompleted(Todo todo, boolean completed) {
		todo.setCompleted(completed);
		updateTodoUseCase.execute(todo);
	}

	public void deleteTodo(Todo todo) {
		deleteTodoUseCase.execute(todo);
	}
}
