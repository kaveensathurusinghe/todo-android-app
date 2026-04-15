package com.example.todo.domain.usecase;

import com.example.todo.data.repository.TodoRepository;
import com.example.todo.domain.model.Todo;

public class DeleteTodoUseCase {

	private final TodoRepository todoRepository;

	public DeleteTodoUseCase(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public void execute(Todo todo) {
		todoRepository.deleteTodo(todo);
	}
}
