package com.example.mytodolist.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.mytodolist.Controller.AddLocalTodoController;
import com.example.mytodolist.Model.RemoteDB;
import com.example.mytodolist.R;

public class AddLocalTodoActivity extends AppCompatActivity {
    private EditText titleTodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_local_todo);

        titleTodo = findViewById(R.id.localTodoTitleInput);
    }

    public void clickOnAddLocalTodo(View view) {
        AddLocalTodoController.addLocalTodo(this, titleTodo.getText().toString().trim());
        finish();
    }
}