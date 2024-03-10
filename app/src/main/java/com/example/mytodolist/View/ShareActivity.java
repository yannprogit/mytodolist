package com.example.mytodolist.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mytodolist.Controller.ShareController;
import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.R;

public class ShareActivity extends AppCompatActivity implements ShareCallback {
    private EditText mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        mail = findViewById(R.id.shareMailInput);
    }

    public void clickOnShare(View view) {
        TodoList selectedTodo = (TodoList) getIntent().getSerializableExtra("selectedTodo");

        ShareController.shareWithUser(mail.getText().toString().trim(), this, selectedTodo, this);
    }

    @Override
    public void onShareCompleted(String message) {
        if ("forFirstTime".equals(message)) {
            Toast.makeText(this, this.getString(R.string.todolist_shared), Toast.LENGTH_SHORT).show();
            finish();
        } else if (this.getString(R.string.todolist_shared).equals(message)) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}