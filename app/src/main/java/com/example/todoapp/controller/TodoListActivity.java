package com.example.todoapp.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.todoapp.MyContext;
import com.example.todoapp.R;
import com.example.todoapp.business.DefaultServices;
import com.example.todoapp.business.Services;
import com.example.todoapp.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoListActivity extends AppCompatActivity {
    private Services mServices;
    private ListView mTodosListView;
    private List<Todo> mAllTodos;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        mServices = ((MyContext)getApplicationContext()).getServices();
        mAllTodos = mServices.getAllTodos();

        mTodosListView = findViewById(R.id._todo_list_listview);

        List<String> todosTitle = new ArrayList<>();
        for (Todo todo: mAllTodos) {
            todosTitle.add(todo.getTitle());
        }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_checked, todosTitle
        );
        mTodosListView.setAdapter(myAdapter);

    }
}