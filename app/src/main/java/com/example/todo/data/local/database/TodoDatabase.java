package com.example.todo.data.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todo.data.local.dao.TodoDao;
import com.example.todo.data.local.dao.UserDao;
import com.example.todo.data.local.entity.TodoEntity;
import com.example.todo.data.local.entity.UserEntity;
import com.example.todo.utils.Constants;

@Database(entities = {TodoEntity.class, UserEntity.class}, version = 1, exportSchema = false)
public abstract class TodoDatabase extends RoomDatabase {

	private static volatile TodoDatabase INSTANCE;

	public abstract TodoDao todoDao();

	public abstract UserDao userDao();

	public static TodoDatabase getInstance(Context context) {
		if (INSTANCE == null) {
			synchronized (TodoDatabase.class) {
				if (INSTANCE == null) {
					INSTANCE = Room.databaseBuilder(
									context.getApplicationContext(),
									TodoDatabase.class,
									Constants.DATABASE_NAME)
							.fallbackToDestructiveMigration()
							.allowMainThreadQueries()
							.build();
				}
			}
		}
		return INSTANCE;
	}
}
