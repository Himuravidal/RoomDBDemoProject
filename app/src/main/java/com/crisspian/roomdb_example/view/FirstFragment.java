package com.crisspian.roomdb_example.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crisspian.roomdb_example.R;
import com.crisspian.roomdb_example.adapter.TodoAdapter;
import com.crisspian.roomdb_example.databinding.FragmentFirstBinding;
import com.crisspian.roomdb_example.model.Todo;
import com.crisspian.roomdb_example.presenter.TodoInterfaceView;
import com.crisspian.roomdb_example.presenter.TodoPresenter;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements TodoInterfaceView {

    private FragmentFirstBinding binding;
    private TodoPresenter presenter;
    private RecyclerView recyclerView;
    private TodoAdapter adapter;
    private String newTodo;

    private List<Todo> todoList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TodoPresenter(getActivity().getApplication(), this);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            newTodo = getArguments().getString("newTodo");
            Todo todo = new Todo();
            todo.setReminder(newTodo);
            presenter.insertTodo(todo);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);

        presenter.getAllTodoFromRepository();
        recyclerView = binding.rvFragment;
        adapter = new TodoAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });


        presenter.getAllTodoLiveData().observe(getViewLifecycleOwner(), new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                adapter.updateData(todos);
                recyclerView.smoothScrollToPosition(todos.size());
            }
        });

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void showAllTodo(List<Todo> todos) {
        Log.d("TODO", String.valueOf(todos));
        todoList = todos;
    }

    @Override
    public void updateDataView(List<Todo> todos) {
        adapter.updateData(todos);
        adapter.notifyDataSetChanged();
    }

    //This override the button menu implementation, don`t forget to include setHasOptionsMenu(true);
    //in onCreate()
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(getContext(), "Borrando", Toast.LENGTH_SHORT).show();
            presenter.deleteAllTodo();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}