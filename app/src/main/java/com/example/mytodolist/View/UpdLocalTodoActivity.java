package com.example.mytodolist.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.mytodolist.Controller.UpdLocalTaskController;
import com.example.mytodolist.Controller.UpdLocalTodoController;
import com.example.mytodolist.Model.Class.Task;
import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.R;

public class UpdLocalTodoActivity extends AppCompatActivity {
    private EditText titleTodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upd_local_todo);
        titleTodo = findViewById(R.id.localTodoTitleInput);
    }

    public void clickOnUpdLocalTodo(View view) {
        TodoList selectedTodo = (TodoList) getIntent().getSerializableExtra("selectedTodo");
        UpdLocalTodoController.updTodo(this, selectedTodo, titleTodo.getText().toString().trim());
        finish();
    }
}
