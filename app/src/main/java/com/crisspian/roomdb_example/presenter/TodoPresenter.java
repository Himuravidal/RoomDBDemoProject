package com.crisspian.roomdb_example.presenter;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.crisspian.roomdb_example.model.Todo;
import com.crisspian.roomdb_example.repository.Repository;

import java.util.List;

public class TodoPresenter implements TodoInterfacePresenter {

    private Repository repository;
    private TodoInterfaceView todoInterfaceView;
    private LiveData<List<Todo>> listLiveData;

    public TodoPresenter(Application application, TodoInterfaceView todoInterfaceView) {
        repository = new Repository(application);
        this.todoInterfaceView = todoInterfaceView;
        listLiveData = repository.getAlphabeticTodo();
    }

    @Override
    public void insertTodo(Todo todo) {
        repository.insertTodo(todo);
    }

    @Override
    public void updateTodo(Todo todo) {
        repository.updateTodo(todo);
    }

    @Override
    public void getAllTodoFromRepository() {
        todoInterfaceView.showAllTodo(repository.getAllTodo());
    }

    @Override
    public void deleteAllTodo() {
        repository.deleteAllTodo();
    }

    public LiveData<List<Todo>> getAllTodoLiveData() {
        return listLiveData;
    }


}
