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

public class SignUpActivity extends AppCompatActivity {
    private EditText mail;
    private EditText pseudo;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mail = findViewById(R.id.mailInput);
        pseudo = findViewById(R.id.pseudoInput);
        password = findViewById(R.id.passwordInput);
    }

    public void clickOnSignUp(View view) {
        SignUpController.createUser(pseudo.getText().toString(), mail.getText().toString(), password.getText().toString(), this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, R.string.success_created_account, Toast.LENGTH_SHORT).show();
                    //SignUpController.goToLogin(this);
                } else {
                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}