package com.example.todoapp.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import com.example.todoapp.MyContext;
import com.example.todoapp.R;
import com.example.todoapp.actions.CustomArrayAdapter;
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

        CustomArrayAdapter myAdapter = new CustomArrayAdapter(this, R.layout.activity_todo_item, mAllTodos);

        mTodosListView.setAdapter(myAdapter);
    }
}