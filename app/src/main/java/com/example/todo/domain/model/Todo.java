package com.example.todo.domain.model;

public class Todo {

	private long id;
	private String title;
	private String description;
	private long dueDateMillis;
	private String tags;
	private int priority;
	private boolean completed;

	public Todo(long id,
				String title,
				String description,
				long dueDateMillis,
				String tags,
				int priority,
				boolean completed) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDateMillis = dueDateMillis;
		this.tags = tags;
		this.priority = priority;
		this.completed = completed;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
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
