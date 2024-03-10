package com.example.mytodolist.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.mytodolist.Controller.Adapter.LocalTaskAdapter;
import com.example.mytodolist.Controller.LocalTodoDetailController;
import com.example.mytodolist.Model.Class.Task;
import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.R;

import java.util.ArrayList;

public class LocalTodoDetailActivity extends AppCompatActivity {
    private TodoList importSelectedTodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_todo_detail);

        if (importSelectedTodo == null) {
            importSelectedTodo = (TodoList) getIntent().getSerializableExtra("selectedTodo");
        }
        TodoList selectedTodo = LocalTodoDetailController.getTodoList(this, importSelectedTodo);

        String title = selectedTodo.getTitle();
        TextView titleTextView = findViewById(R.id.localTodoTitle);
        titleTextView.setText(title);

        ListView listView = findViewById(R.id.localTasks);
        ArrayList<Task> tasksList = selectedTodo.getTasks();
        LocalTaskAdapter adapter = new LocalTaskAdapter(this, tasksList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (importSelectedTodo == null) {
            importSelectedTodo = (TodoList) getIntent().getSerializableExtra("selectedTodo");
        }
        TodoList selectedTodo = LocalTodoDetailController.getTodoList(this, importSelectedTodo);

        String title = selectedTodo.getTitle();
        TextView titleTextView = findViewById(R.id.localTodoTitle);
        titleTextView.setText(title);

        ListView listView = findViewById(R.id.localTasks);
        ArrayList<Task> tasksList = selectedTodo.getTasks();
        LocalTaskAdapter adapter = new LocalTaskAdapter(this, tasksList);
        listView.setAdapter(adapter);

        ImageButton todoOptionsBtn = findViewById(R.id.btnLocalTodoOptions);
        todoOptionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(todoOptionsBtn);
            }
        });
    }

    public void clickOnGoToAddLocalTask(View view) {
        TodoList importSelectedTodo = (TodoList) getIntent().getSerializableExtra("selectedTodo");
        LocalTodoDetailController.goToAddLocalTask(this, importSelectedTodo);
    }

    public void clickOnGoToUpdLocalTodo(View view) {
        TodoList importSelectedTodo = (TodoList) getIntent().getSerializableExtra("selectedTodo");
        LocalTodoDetailController.goToUpdLocalTask(this, importSelectedTodo);
    }

    @Override
    protected void onStop() {
        super.onStop();
        TodoList importSelectedTodo = (TodoList) getIntent().getSerializableExtra("selectedTodo");
        LocalTodoDetailController.saveTodoList(this, importSelectedTodo);
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.local_todo_options, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.shareLocalTodoOption) {
                    return true;
                } else if (id == R.id.deleteLocalTodoOption) {
                    return true;
                }
                else {
                    return false;
                }
            }
        });

        popupMenu.show();
    }
}