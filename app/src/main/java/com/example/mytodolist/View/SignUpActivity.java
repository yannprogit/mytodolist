package com.example.mytodolist.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mytodolist.Controller.SignUpController;
import com.example.mytodolist.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignUpActivity extends AppCompatActivity {
    private EditText mail;
    private EditText pseudo;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mail = findViewById(R.id.mailSignUp);
        pseudo = findViewById(R.id.pseudoSignUp);
        password = findViewById(R.id.passwordSignUp);
    }

    public void clickOnSignUp(View view) {
        SignUpController.createUser(pseudo.getText().toString().trim(), mail.getText().toString().trim(), password.getText().toString(), this)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, R.string.success_created_account, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}