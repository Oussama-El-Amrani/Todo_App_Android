package com.example.todoapp.dao;

import com.example.todoapp.model.Todo;

import java.util.List;
import java.util.Vector;

public class TodoDaoMemory implements TodoDao{

    private List<Todo> todos;
    private int lastIndex=0;
    public TodoDaoMemory() {
        todos=new Vector<Todo>();
        insert(new Todo("rdv dentiste",false));
        insert(new Todo("rdv docteur",true));
    }
    @Override
    public Todo insert(Todo todo) {
        lastIndex++;
        todo.setId(lastIndex);
        todos.add(todo);
        return todo;
    }

    @Override
    public Todo update(Todo todo) {
        Todo element=get(todo.getId());
        if(element==null)
            return null;
        element.setCompleted(todo.isCompleted());
        element.setTitle(todo.getTitle());
        return element;
    }

    @Override
    public Todo delete(Todo todo) {
        Todo element=get(todo.getId());
        if(element==null)
            return null;

        todos.remove(element);
        return element;
    }

    @Override
    public Todo get(long id) {
        for(Todo element:todos)
            if(element.getId() == id)
                return element;
        return null;
    }

    @Override
    public List<Todo> getAll() {
        return todos;
    }

}
