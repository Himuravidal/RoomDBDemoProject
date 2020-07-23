package com.crisspian.roomdb_example.repository;

import androidx.lifecycle.LiveData;

import com.crisspian.roomdb_example.model.Todo;

import java.util.List;

public interface LocalData {

    List<Todo> getAllTodo();
    LiveData<List<Todo>> getAlphabeticTodo();
    void insertTodo(Todo todo);
    void updateTodo(Todo todo);
    void deleteAllTodo();
}
