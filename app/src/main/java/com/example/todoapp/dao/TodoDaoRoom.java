package com.example.todoapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoapp.model.Todo;

import java.util.List;

@Dao
public interface TodoDaoRoom  {
    @Insert
    long insert(Todo todo);

    @Update
    int update(Todo todo);

    @Query("SELECT * FROM Todo")
    LiveData<List<Todo>> getAll();
}
