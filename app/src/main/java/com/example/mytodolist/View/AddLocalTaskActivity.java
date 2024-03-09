package com.example.mytodolist.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.mytodolist.Controller.AddLocalTaskController;
import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.R;

public class AddLocalTaskActivity extends AppCompatActivity {
    private EditText titleTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_local_task);

        titleTask = findViewById(R.id.localTaskTitleInput);
    }

    public void clickOnAddLocalTask(View view) {
        TodoList importSelectedTodo = (TodoList) getIntent().getSerializableExtra("selectedTodo");
        AddLocalTaskController.addTask(this, importSelectedTodo, titleTask.getText().toString().trim());
        finish();
    }
}