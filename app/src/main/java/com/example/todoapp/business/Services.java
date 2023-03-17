package com.example.todoapp.business;


import com.example.todoapp.model.Todo;

import java.util.List;

public interface Services {
    //gestion des Todos
    Todo addTodo(Todo todo);
    Todo searchTodoById(long id);
    List<Todo> getAllTodos();
    Todo updateTodo(Todo todo);
    Todo deleteTodo(Todo todo);
}
