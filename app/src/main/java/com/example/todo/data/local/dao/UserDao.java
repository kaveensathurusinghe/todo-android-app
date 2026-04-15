package com.example.todo.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.todo.data.local.entity.UserEntity;

@Dao
public interface UserDao {

	@Insert(onConflict = OnConflictStrategy.ABORT)
	long insertUser(UserEntity userEntity);

	@Query("SELECT * FROM users WHERE username = :username LIMIT 1")
	UserEntity getUserByUsername(String username);
}
