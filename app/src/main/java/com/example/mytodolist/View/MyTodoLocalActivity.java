package com.example.mytodolist.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.mytodolist.Controller.LoginController;
import com.example.mytodolist.R;

public class MyTodoLocalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_todo_local);
    }

    public void clickOnLogout(View view) {
        LoginController.logoutUser(this);
    }
}