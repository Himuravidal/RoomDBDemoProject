package com.crisspian.roomdb_example.presenter;

import com.crisspian.roomdb_example.model.Todo;

import java.util.List;

public interface TodoInterfaceView {
   void showAllTodo(List<Todo> todos);
   void updateDataView(List<Todo> todos);
}
