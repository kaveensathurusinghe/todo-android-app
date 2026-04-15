package com.example.todo.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todos")
public class TodoEntity {

	@PrimaryKey(autoGenerate = true)
	private long id;

	@NonNull
	private String title;

	private String description;

	private long dueDateMillis;

	private String tags;

	private String ownerUsername;

	/**
	 * Higher value = higher priority.
	 */
	private int priority;

	private boolean completed;

	public TodoEntity(@NonNull String title,
					  String description,
					  long dueDateMillis,
					  String tags,
					  String ownerUsername,
					  int priority,
					  boolean completed) {
		this.title = title;
		this.description = description;
		this.dueDateMillis = dueDateMillis;
		this.tags = tags;
		this.ownerUsername = ownerUsername;
		this.priority = priority;
		this.completed = completed;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@NonNull
	public String getTitle() {
		return title;
	}

	public void setTitle(@NonNull String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getDueDateMillis() {
		return dueDateMillis;
	}

	public void setDueDateMillis(long dueDateMillis) {
		this.dueDateMillis = dueDateMillis;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getOwnerUsername() {
		return ownerUsername;
	}

	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
