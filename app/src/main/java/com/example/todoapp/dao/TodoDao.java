package com.example.todoapp.dao;


import com.example.todoapp.model.Todo;

import java.util.List;

public interface TodoDao {
    public Todo insert(Todo todo);
    public Todo update(Todo todo);
    public Todo delete(Todo todo);
    public Todo get(long id);
    public List<Todo> getAll();

}

