package com.example.mytodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import com.example.mytodolist.Controller.LoginController;
import com.example.mytodolist.Controller.SignUpController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickCreateAccount(View view) {
        LoginController.goToSignUp(this);
    }
    public void onClickConnexion(View view) {
        SignUpController.goToLogin(this);
    }
}

