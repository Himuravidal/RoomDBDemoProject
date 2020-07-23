package com.crisspian.roomdb_example.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crisspian.roomdb_example.databinding.TodoItemListBinding;
import com.crisspian.roomdb_example.model.Todo;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder>{
    private List<Todo> todoList;

    public TodoAdapter(Context context) {
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TodoItemListBinding binding = TodoItemListBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new TodoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        String item = todoList.get(position).getReminder();
        holder.textView.setText(item);
    }

    @Override
    public int getItemCount() {
        if (todoList != null){
            return todoList.size();
        } else {
            return 0;
        }

    }


    public class TodoViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;

        public TodoViewHolder(@NonNull TodoItemListBinding itemView) {
            super(itemView.getRoot());
            textView = itemView.tvTodoItem;
        }
    }

    public void updateData(List<Todo> todos) {
        todoList = todos;
        notifyDataSetChanged();
    }

}
