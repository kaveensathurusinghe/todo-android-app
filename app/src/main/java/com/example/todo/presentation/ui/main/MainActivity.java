package com.example.todo.presentation.ui.main;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.todo.R;
import com.example.todo.domain.model.Todo;
import com.example.todo.presentation.ui.addedit.AddEditTodoActivity;
import com.example.todo.presentation.viewmodel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TodoAdapter.TodoInteractionListener {

    private TodoViewModel todoViewModel;
    private TodoAdapter todoAdapter;
    private LiveData<List<Todo>> currentTodosLiveData;
    private View emptyStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.rvTodos);
        FloatingActionButton fabAdd = findViewById(R.id.fabAddTodo);
        View root = findViewById(R.id.main);
        emptyStateView = findViewById(R.id.tvEmptyState);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        todoAdapter = new TodoAdapter(this);
        recyclerView.setAdapter(todoAdapter);
        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.layout_fade_in));

        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        currentTodosLiveData = todoViewModel.getTodos();
        currentTodosLiveData.observe(this, this::renderTodos);

        fabAdd.setOnClickListener(v -> {
            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            Intent intent = new Intent(this, AddEditTodoActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    private void renderTodos(List<Todo> todos) {
        todoAdapter.submitList(todos);

        if (emptyStateView != null) {
            boolean isEmpty = todos == null || todos.isEmpty();
            emptyStateView.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onTodoClicked(Todo todo) {
        Intent intent = new Intent(this, AddEditTodoActivity.class);
        intent.putExtra(com.example.todo.utils.Constants.EXTRA_TODO_ID, todo.getId());
        startActivity(intent);
    }

    @Override
    public void onTodoCheckedChanged(Todo todo, boolean isChecked) {
        // Light haptic feedback for toggle
        View root = findViewById(R.id.main);
        if (root != null) {
            root.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
        }
        todoViewModel.toggleTodoCompleted(todo, isChecked);
    }

    @Override
    public void onTodoDeleteClicked(Todo todo) {
        View root = findViewById(R.id.main);
        if (root != null) {
            root.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
        }
        todoViewModel.deleteTodo(todo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sort_due_date) {
            todoViewModel.setSortType(TodoViewModel.SortType.DUE_DATE);
            reloadTodos();
            return true;
        } else if (id == R.id.action_sort_priority) {
            todoViewModel.setSortType(TodoViewModel.SortType.PRIORITY);
            reloadTodos();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void reloadTodos() {
        if (currentTodosLiveData != null) {
            currentTodosLiveData.removeObservers(this);
        }
        currentTodosLiveData = todoViewModel.getTodos();
        currentTodosLiveData.observe(this, this::renderTodos);
    }
}