package com.example.todo.domain.usecase;

import androidx.lifecycle.LiveData;

import com.example.todo.data.repository.TodoRepository;
import com.example.todo.domain.model.Todo;

import java.util.List;

public class GetTodosUseCase {

	private final TodoRepository todoRepository;

	public GetTodosUseCase(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public LiveData<List<Todo>> getTodosSortedByDueDate() {
		return todoRepository.getTodosSortedByDueDate();
	}

	public LiveData<List<Todo>> getTodosSortedByPriority() {
		return todoRepository.getTodosSortedByPriority();
	}
}
