package com.example.mytodolist.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.mytodolist.Controller.Adapter.LocalTodoAdapter;
import com.example.mytodolist.Controller.AddLocalTodoController;
import com.example.mytodolist.Controller.LoginController;
import com.example.mytodolist.Controller.MyLocalTodoController;
import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.R;

import java.util.ArrayList;

public class MyLocalTodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_local_todo);

        ListView listView = findViewById(R.id.localTodoList);
        ArrayList<TodoList> todoLists = MyLocalTodoController.getTodoLists(this);
        LocalTodoAdapter adapter = new LocalTodoAdapter(this, todoLists);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView listView = findViewById(R.id.localTodoList);
        ArrayList<TodoList> todoLists = MyLocalTodoController.getTodoLists(this);
        LocalTodoAdapter adapter = new LocalTodoAdapter(this, todoLists);
        listView.setAdapter(adapter);
    }

    public void clickOnLogout(View view) {
        finish();
        LoginController.logoutUser(this);
    }

    public void clickOnGoToAddLocalTodo(View view) {
        MyLocalTodoController.goToAddLocalTodo(this);
    }

}