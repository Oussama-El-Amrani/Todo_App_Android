package com.example.todoapp;

import android.app.Application;
import android.util.Log;

import androidx.room.Room;

import com.example.todoapp.business.DefaultServices;
import com.example.todoapp.business.Services;
import com.example.todoapp.dao.TodoDaoMemory;
import com.example.todoapp.dao.TodoDaoSQLite;
import com.example.todoapp.model.Todo;
import com.example.todoapp.utils.MyDataBaseHelper;
import com.example.todoapp.utils.TodoDatabase;

public class MyContext extends Application {
    private Services mServices;
    private TodoDatabase mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(this);
        mServices = DefaultServices.getInstance(new TodoDaoSQLite(myDataBaseHelper));

        if (myDataBaseHelper == null) {
            Log.e("todoApp", "null mydatabaseHelper");
        } else {
            Log.e("todoApp", "ok mydatabaseHelper");
        }
    }

    public Services getServices() {
        return mServices;
    }
}
