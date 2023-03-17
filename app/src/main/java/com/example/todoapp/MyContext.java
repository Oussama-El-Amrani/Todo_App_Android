package com.example.todoapp;

import android.app.Application;

import androidx.room.Room;

import com.example.todoapp.business.DefaultServices;
import com.example.todoapp.business.Services;
import com.example.todoapp.dao.TodoDaoMemory;
import com.example.todoapp.model.Todo;
import com.example.todoapp.utils.TodoDatabase;

public class MyContext extends Application {
    private Services mServices;
    private TodoDatabase mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
//        mDatabase = Room.inMemoryDatabaseBuilder()
        mServices = new DefaultServices(new TodoDaoMemory());
    }

    public Services getServices() {
        return mServices;
    }
}
