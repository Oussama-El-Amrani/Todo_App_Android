package com.example.todoapp.business;



import com.example.todoapp.dao.TodoDao;
import com.example.todoapp.dao.TodoDaoMemory;
import com.example.todoapp.model.Todo;

import java.sql.Connection;
import java.util.List;

public class DefaultServices implements Services{
    private TodoDao todoDao;
    public static DefaultServices instance = null;
    public static DefaultServices getInstanceWithMemory() {
        if (instance == null)
            instance = new DefaultServices(new TodoDaoMemory());
        return instance;
    }

    public DefaultServices(TodoDao todoDao) {
        this.todoDao = todoDao;
    }
    @Override
    public Todo addTodo(Todo todo) {
        return todoDao.insert(todo);
    }

    @Override
    public Todo searchTodoById(long id) {
        return todoDao.get(id);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoDao.getAll();
    }

    @Override
    public Todo updateTodo(Todo todo) {
        return todoDao.update(todo);
    }

    @Override
    public Todo deleteTodo(Todo todo) {
        return todoDao.delete(todo);
    }
}
