package com.example.mytodolist.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.mytodolist.Controller.Adapter.LocalTodoAdapter;
import com.example.mytodolist.Controller.Adapter.SharedTodoAdapter;
import com.example.mytodolist.Controller.LoginController;
import com.example.mytodolist.Controller.MyLocalTodoController;
import com.example.mytodolist.Controller.SharedTodoController;
import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.R;

import java.util.ArrayList;

public class SharedTodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_todo);

        ListView listView = findViewById(R.id.sharedTodoList);
        ArrayList<TodoList> todoLists = SharedTodoController.getTodoLists();
        SharedTodoAdapter adapter = new SharedTodoAdapter(this, todoLists);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView listView = findViewById(R.id.sharedTodoList);
        ArrayList<TodoList> todoLists = SharedTodoController.getTodoLists();
        SharedTodoAdapter adapter = new SharedTodoAdapter(this, todoLists);
        listView.setAdapter(adapter);
    }

    public void clickOnLogout(View view) {
        LoginController.logoutUser(this);
        finish();
    }
    public void clickOnGoToLocalTodo(View view) {
        finish();
        SharedTodoController.goToLocalTodo(this);
    }

}