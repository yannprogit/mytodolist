package com.example.mytodolist.Controller;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;

import androidx.core.content.ContextCompat;

import com.example.mytodolist.MainActivity;
import com.example.mytodolist.View.signUpActivity;

public class loginController {
    public static void goToSignUp(Context context) {
        Intent intent = new Intent(context, signUpActivity.class);
        context.startActivity(intent);
    }
}
