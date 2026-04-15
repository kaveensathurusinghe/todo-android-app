package com.example.todo.domain.usecase;

import com.example.todo.data.repository.TodoRepository;
import com.example.todo.domain.model.Todo;
import com.example.todo.utils.ValidatorUtils;

public class AddTodoUseCase {

	private final TodoRepository todoRepository;

	public AddTodoUseCase(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public boolean execute(Todo todo) {
		if (!ValidatorUtils.isValidTodoTitle(todo.getTitle())) {
			return false;
		}
		return todoRepository.addTodo(todo) > 0;
	}
}
