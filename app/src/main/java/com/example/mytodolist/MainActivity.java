package com.example.mytodolist;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mytodolist.Controller.LoginController;
import com.example.mytodolist.Controller.SignUpController;
import com.example.mytodolist.Model.RemoteDB;
import com.example.mytodolist.View.MyLocalTodoActivity;

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
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(getString(R.string.quit_confirmation_message))
                        .setTitle(getString(R.string.warning))
                        .setPositiveButton(getString(R.string.yes_button), (dialog, which) -> {
                            finish();
                            dialog.dismiss();
                        })
                        .setNegativeButton(getString(R.string.no_button), (dialog, which) -> dialog.dismiss())
                        .show();
            }
        });
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
    public void clickOnLeave(View view) {
        getOnBackPressedDispatcher().onBackPressed();
    }
}

