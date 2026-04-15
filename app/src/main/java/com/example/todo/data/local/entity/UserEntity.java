package com.example.todo.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users", indices = {@Index(value = "username", unique = true)})
public class UserEntity {

	@PrimaryKey(autoGenerate = true)
	private long id;

	@NonNull
	private String username;

	@NonNull
	private String passwordHash;

	public UserEntity(@NonNull String username, @NonNull String passwordHash) {
		this.username = username;
		this.passwordHash = passwordHash;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@NonNull
	public String getUsername() {
		return username;
	}

	public void setUsername(@NonNull String username) {
		this.username = username;
	}

	@NonNull
	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(@NonNull String passwordHash) {
		this.passwordHash = passwordHash;
	}
}
