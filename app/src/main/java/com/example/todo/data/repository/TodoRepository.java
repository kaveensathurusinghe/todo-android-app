package com.example.todo.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.todo.data.local.dao.TodoDao;
import com.example.todo.data.local.entity.TodoEntity;
import com.example.todo.data.local.preferences.SharedPrefsManager;
import com.example.todo.domain.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoRepository {

	private final TodoDao todoDao;
	private final SharedPrefsManager sharedPrefsManager;

	public TodoRepository(TodoDao todoDao, SharedPrefsManager sharedPrefsManager) {
		this.todoDao = todoDao;
		this.sharedPrefsManager = sharedPrefsManager;
	}

	public LiveData<List<Todo>> getTodosSortedByDueDate() {
		String username = getCurrentUsername();
		return Transformations.map(todoDao.getTodosForUserOrderByDueDate(username), this::mapEntitiesToDomain);
	}

	public LiveData<List<Todo>> getTodosSortedByPriority() {
		String username = getCurrentUsername();
		return Transformations.map(todoDao.getTodosForUserOrderByPriority(username), this::mapEntitiesToDomain);
	}

	public long addTodo(Todo todo) {
		String username = getCurrentUsername();
		TodoEntity entity = toEntity(todo, username);
		long id = todoDao.insertTodo(entity);
		todo.setId(id);
		return id;
	}

	public void updateTodo(Todo todo) {
		String username = getCurrentUsername();
		TodoEntity entity = toEntity(todo, username);
		todoDao.updateTodo(entity);
	}

	public void deleteTodo(Todo todo) {
		String username = getCurrentUsername();
		TodoEntity entity = toEntity(todo, username);
		todoDao.deleteTodo(entity);
	}

	public Todo getTodoById(long id) {
		String username = getCurrentUsername();
		TodoEntity entity = todoDao.getTodoByIdForUser(id, username);
		if (entity == null) {
			return null;
		}
		return toDomain(entity);
	}

	private String getCurrentUsername() {
		String username = sharedPrefsManager.getLoggedInUsername();
		return username != null ? username : "";
	}

	private List<Todo> mapEntitiesToDomain(List<TodoEntity> entities) {
		List<Todo> todos = new ArrayList<>();
		if (entities != null) {
			for (TodoEntity entity : entities) {
				todos.add(toDomain(entity));
			}
		}
		return todos;
	}

	private Todo toDomain(TodoEntity entity) {
		return new Todo(
				entity.getId(),
				entity.getTitle(),
				entity.getDescription(),
				entity.getDueDateMillis(),
				entity.getTags(),
				entity.getPriority(),
				entity.isCompleted()
		);
	}

		    private TodoEntity toEntity(Todo todo, String ownerUsername) {
		TodoEntity entity = new TodoEntity(
				todo.getTitle(),
				todo.getDescription(),
				todo.getDueDateMillis(),
				todo.getTags(),
				ownerUsername,
				todo.getPriority(),
				todo.isCompleted()
		);
		entity.setId(todo.getId());
		return entity;
	}
}
