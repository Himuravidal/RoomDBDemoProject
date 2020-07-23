package com.crisspian.roomdb_example.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.crisspian.roomdb_example.db.TodoDao;
import com.crisspian.roomdb_example.db.TodoRoomDatabase;
import com.crisspian.roomdb_example.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class Repository implements LocalData {
    private TodoDao todoDao;
    private List<Todo> allTodo = new ArrayList<>();
    private LiveData<List<Todo>> listLiveData;

    public Repository(Application application) {
        TodoRoomDatabase db = TodoRoomDatabase.getDatabase(application);
        todoDao = db.todoDao();
        listLiveData = todoDao.getAllTodoByAlphabetizedOrder();
    }

    @Override
    public List<Todo> getAllTodo() {
        final List<Todo> todos = new ArrayList<>();
        TodoRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                allTodo.addAll(todoDao.getallTodo());
            }
        });
        return allTodo;
    }

    @Override
    public LiveData<List<Todo>> getAlphabeticTodo() {
        return todoDao.getAllTodoByAlphabetizedOrder();
    }

    @Override
    public void insertTodo(final Todo todo) {
        TodoRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                todoDao.insertTodo(todo);
            }
        });
    }

    @Override
    public void updateTodo(final Todo todo) {
        TodoRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                todoDao.updateTodo(todo);
            }
        });
    }

    @Override
    public void deleteAllTodo() {
        TodoRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                todoDao.deleteAll();
            }
        });
    }
}
