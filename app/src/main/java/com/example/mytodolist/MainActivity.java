package com.example.mytodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mytodolist.Controller.LoginController;
import com.example.mytodolist.Controller.SignUpController;
import com.example.mytodolist.Model.RemoteDB;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (RemoteDB.getmAuth().getCurrentUser()!=null) {
            LoginController.goToToDoLocal(this);
            finish();
        }
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (RemoteDB.getmAuth().getCurrentUser()!=null) {
            LoginController.goToToDoLocal(this);
            finish();
        }
    }

    public void clickOnCreateAccount(View view) {
        LoginController.goToSignUp(this);
    }
    public void clickOnConnexion(View view) {
        SignUpController.goToLogin(this);
    }
}

