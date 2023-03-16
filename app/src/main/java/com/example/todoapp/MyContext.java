package com.example.todoapp;

import android.app.Application;

import com.example.todoapp.business.DefaultServices;
import com.example.todoapp.business.Services;
import com.example.todoapp.dao.TodoDaoMemory;

public class MyContext extends Application {
    private Services mServices;

    @Override
    public void onCreate() {
        super.onCreate();
        mServices = new DefaultServices(new TodoDaoMemory());
    }

    public Services getServices() {
        return mServices;
    }
}
