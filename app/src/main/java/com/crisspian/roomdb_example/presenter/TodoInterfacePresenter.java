package com.crisspian.roomdb_example.presenter;

import com.crisspian.roomdb_example.model.Todo;

import java.util.List;

interface TodoInterfacePresenter {

    void insertTodo(Todo todo);
    void updateTodo(Todo todo);
    void getAllTodoFromRepository();
    void deleteAllTodo();

}
