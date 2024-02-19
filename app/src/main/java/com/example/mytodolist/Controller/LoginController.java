package com.example.mytodolist.Controller;

import android.content.Context;
import android.content.Intent;

import com.example.mytodolist.View.SignUpActivity;

public class LoginController {
    public static void goToSignUp(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }
}
