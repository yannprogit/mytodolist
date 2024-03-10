package com.example.mytodolist.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.mytodolist.Controller.AddLocalTaskController;
import com.example.mytodolist.Controller.UpdLocalTaskController;
import com.example.mytodolist.Model.Class.Task;
import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.R;

public class UpdLocalTaskActivity extends AppCompatActivity {
    private EditText titleTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upd_local_task);
        titleTask = findViewById(R.id.localTaskTitleInput);
    }

    public void clickOnUpdLocalTask(View view) {
        Task selectedTask = (Task) getIntent().getSerializableExtra("selectedTask");
        UpdLocalTaskController.updTask(this, selectedTask, titleTask.getText().toString().trim());
        finish();
    }
}