package com.example.mytodolist.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mytodolist.Controller.ShareController;
import com.example.mytodolist.R;

public class ShareActivity extends AppCompatActivity {
    private EditText mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        mail = findViewById(R.id.shareMailInput);
    }

    public void clickOnShare(View view) {
        String msg = ShareController.shareWithUser(mail.getText().toString().trim(), this);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        if (msg=="oui") {
            finish();
        }
    }
}