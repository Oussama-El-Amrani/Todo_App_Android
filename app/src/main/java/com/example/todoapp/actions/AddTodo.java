package com.example.todoapp.actions;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoapp.business.Services;
import com.example.todoapp.controller.MainActivity;
import com.example.todoapp.controller.TodoListActivity;
import com.example.todoapp.model.Todo;

public class AddTodo implements View.OnClickListener , TextWatcher {
    private Button mAddTodo;
    private Button mMyTodos;
    private TextView mTodoInput;
    private MainActivity mMainActivity;
    private Services mServices;

    public AddTodo(MainActivity mainActivity) {
        mMainActivity = mainActivity;
        mServices = mainActivity.getServices();
        mAddTodo = mainActivity.getAddTodo();
        mMyTodos = mainActivity.getMyTodos();
        mTodoInput = mainActivity.getTodoInput();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == mMyTodos.getId()){
            Intent myTodosIntent = new Intent(mMainActivity, TodoListActivity.class);
            mMainActivity.startActivity(myTodosIntent);
        }
        if (v.getId() == mAddTodo.getId()) {
            if (!mTodoInput.getText().toString().equals("")) {
                Todo todo = mServices.addTodo(new Todo(mTodoInput.getText().toString(), false));
                if (todo != null){
                    Intent intent = new Intent(mMainActivity, TodoListActivity.class);
                    mMainActivity.startActivity(intent);
                }
                else {
                    Toast.makeText(mMainActivity, "error", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        mAddTodo.setEnabled(!s.toString().isEmpty());
        System.out.println(s);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }


}
