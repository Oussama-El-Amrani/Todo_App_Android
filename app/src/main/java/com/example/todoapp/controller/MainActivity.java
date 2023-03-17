package com.example.todoapp.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.todoapp.MyContext;
import com.example.todoapp.R;
import com.example.todoapp.actions.AddTodo;
import com.example.todoapp.business.Services;

public class MainActivity extends AppCompatActivity {
    private TextView mTodoInput;
    private Button mAddTodo;
    private Button mMyTodos;
    private Services mServices;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mServices = ((MyContext) getApplicationContext()).getServices();

        mTodoInput = findViewById(R.id.editTextTextPersonName);
        mAddTodo = findViewById(R.id.button_to_do);
        mMyTodos = findViewById(R.id.button_my_todos);

        mAddTodo.setEnabled(false);
        AddTodo addTodo = new AddTodo(this);

        mTodoInput.setOnClickListener(v -> {
            System.out.println("salam");
        });

        mTodoInput.addTextChangedListener(addTodo);
        mMyTodos.setOnClickListener(addTodo);
        mAddTodo.setOnClickListener(addTodo);
    }

    public TextView getTodoInput() {
        return mTodoInput;
    }

    public Button getAddTodo() {
        return mAddTodo;
    }

    public Button getMyTodos() {
        return mMyTodos;
    }

    public Services getServices() {
        return mServices;
    }
}