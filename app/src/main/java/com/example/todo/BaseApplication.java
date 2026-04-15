package com.example.todo;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Follow system dark/light theme
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }
}