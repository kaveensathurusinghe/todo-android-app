package com.example.todo.domain.usecase;

import com.example.todo.data.repository.TodoRepository;
import com.example.todo.domain.model.Todo;
import com.example.todo.utils.ValidatorUtils;

public class UpdateTodoUseCase {

	private final TodoRepository todoRepository;

	public UpdateTodoUseCase(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public boolean execute(Todo todo) {
		if (!ValidatorUtils.isValidTodoTitle(todo.getTitle())) {
			return false;
		}
		todoRepository.updateTodo(todo);
		return true;
	}
}
