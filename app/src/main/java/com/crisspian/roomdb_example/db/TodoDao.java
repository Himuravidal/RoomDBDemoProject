package com.crisspian.roomdb_example.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.crisspian.roomdb_example.model.Todo;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTodo(Todo todo);

    @Update
    void updateTodo(Todo todo);

    @Query("SELECT * from todo_table ORDER BY id ASC")
    LiveData<List<Todo>> getAllTodoByAlphabetizedOrder();

    @Query("SELECT * FROM todo_table")
    List<Todo> getallTodo();

    @Delete
    void deleteOneTodo(Todo todo);

    @Query("DELETE FROM todo_table")
    void deleteAll();
}
