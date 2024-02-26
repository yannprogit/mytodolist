package com.example.mytodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.mytodolist.Controller.LoginController;
import com.example.mytodolist.Controller.SignUpController;
import com.example.mytodolist.Model.RemoteDB;
import com.example.mytodolist.View.MyTodoLocalActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (RemoteDB.getmAuth().getCurrentUser()!=null) {
            LoginController.goToToDoLocal(this);
            finish();
            return;
        }
        setContentView(R.layout.activity_main);

    }

    public void clickOnCreateAccount(View view) {
        LoginController.goToSignUp(this);
    }
    public void clickOnConnexion(View view) {
        SignUpController.goToLogin(this);
    }
}

