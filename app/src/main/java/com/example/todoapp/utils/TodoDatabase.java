package com.example.todoapp.utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.todoapp.dao.TodoDaoRoom;
import com.example.todoapp.model.Todo;

import java.util.concurrent.Executors;

@Database(entities = Todo.class, version = 1, exportSchema = false)
public abstract class TodoDatabase extends RoomDatabase {
    // --- SINGLETON ---

    private static volatile TodoDatabase INSTANCE;

    // --- DAO ---

    public abstract TodoDaoRoom mTodoDaoRoom();

    public static TodoDatabase getInstance(Context context) {

        if (INSTANCE == null) {

            synchronized (TodoDatabase.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),

                                    TodoDatabase.class, "MyDatabase.db")

                            .addCallback(prepopulateDatabase())

                            .build();

                }

            }

        }

        return INSTANCE;

    }

    private static Callback prepopulateDatabase() {

        return new Callback() {

            @Override

            public void onCreate(@NonNull SupportSQLiteDatabase db) {

                super.onCreate(db);

                Executors.newSingleThreadExecutor().execute(
                        () -> INSTANCE.mTodoDaoRoom()
                                .insert(
                                        new Todo("Spring", false)
                                )
                );

            }

        };

    }
}
