package com.example.todo.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todo.data.local.entity.TodoEntity;

import java.util.List;

@Dao
public interface TodoDao {

	@Query("SELECT * FROM todos WHERE ownerUsername = :username ORDER BY dueDateMillis ASC")
	LiveData<List<TodoEntity>> getTodosForUserOrderByDueDate(String username);

	@Query("SELECT * FROM todos WHERE ownerUsername = :username ORDER BY priority DESC, dueDateMillis ASC")
	LiveData<List<TodoEntity>> getTodosForUserOrderByPriority(String username);

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	long insertTodo(TodoEntity todoEntity);

	@Update
	void updateTodo(TodoEntity todoEntity);

	@Delete
	void deleteTodo(TodoEntity todoEntity);

	@Query("SELECT * FROM todos WHERE id = :id AND ownerUsername = :username LIMIT 1")
	TodoEntity getTodoByIdForUser(long id, String username);
}
