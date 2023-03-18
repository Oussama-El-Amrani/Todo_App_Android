package com.example.todoapp.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import com.example.todoapp.model.Todo;
import com.example.todoapp.utils.MyDataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class TodoDaoSQLite implements TodoDao{
    private MyDataBaseHelper mMyDataBaseHelper;

    public TodoDaoSQLite(MyDataBaseHelper myDataBaseHelper) {
        this.mMyDataBaseHelper = myDataBaseHelper;
    }
    @Override
    public Todo insert(Todo todo) {
        System.out.println(mMyDataBaseHelper.getReadableDatabase());
        SQLiteDatabase db = mMyDataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDataBaseHelper.COLUMN_TITLE, todo.getTitle());
        contentValues.put(MyDataBaseHelper.COLUMN_IS_COMPLETED, todo.isCompleted());

        try {
            long id = db.insertOrThrow(MyDataBaseHelper.TABLE_NAME, null,contentValues);
            todo.setId(id);
            return todo;
        } catch (SQLException e) {
            Log.e("todoApp", "Error inserting todo", e);
            return  null;
        }
    }

    @Override
    public Todo update(Todo todo) {
        SQLiteDatabase db = this.mMyDataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(MyDataBaseHelper.COLUMN_ID, todo.getId());
        contentValues.put(MyDataBaseHelper.COLUMN_TITLE, todo.getTitle());
        contentValues.put(MyDataBaseHelper.COLUMN_IS_COMPLETED, todo.isCompleted());

        db.update(
                MyDataBaseHelper.TABLE_NAME,
                contentValues,
                MyDataBaseHelper.COLUMN_ID + " =?",
                new String[]{todo.getId()+""}
        );
        return get(todo.getId());
    }

    @Override
    public Todo delete(Todo todo) {
        Todo todo_ = get(todo.getId());
        SQLiteDatabase db = mMyDataBaseHelper.getWritableDatabase();
        long result = db.delete(
                MyDataBaseHelper.TABLE_NAME,
                MyDataBaseHelper.COLUMN_ID +" =?",
                new String[]{String.valueOf(todo.getId())}
        );
        return todo_;
    }

    @SuppressLint("Range")
    @Override
    public Todo get(long id) {
        SQLiteDatabase db = mMyDataBaseHelper.getReadableDatabase();
        String query = "SELECT * FROM " +
                MyDataBaseHelper.TABLE_NAME +
                " WHERE " + MyDataBaseHelper.COLUMN_ID +
                " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            Todo todo = new Todo();
            todo.setId(
                    cursor.getInt(cursor.getColumnIndex(MyDataBaseHelper.COLUMN_ID))
            );
            todo.setTitle(
                    cursor.getString(cursor.getColumnIndex(MyDataBaseHelper.COLUMN_TITLE))
            );
            todo.setCompleted(
                    cursor.getInt(cursor.getColumnIndex(MyDataBaseHelper.COLUMN_IS_COMPLETED)) > 0
            );
            return todo;
        }
        return null;
    }

    @Override
    public List<Todo> getAll() {
        List<Todo> todoList = new ArrayList<>();
        SQLiteDatabase db = mMyDataBaseHelper.getReadableDatabase();
        String query = "SELECT * FROM " + MyDataBaseHelper.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        if (!cursor.moveToFirst())
            return todoList;
        do {
            Todo todo = new Todo();
            todo.setId(cursor.getInt(0));
            todo.setTitle(cursor.getString(1));
            todo.setCompleted(cursor.getInt(2)>0);

            todoList.add(todo);
        }while (cursor.moveToNext());
        return todoList;
    }
}
