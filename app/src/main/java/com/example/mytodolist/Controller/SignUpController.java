package com.example.mytodolist.Controller;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.example.mytodolist.Model.RemoteDB;
import com.example.mytodolist.Model.Class.User;
import com.example.mytodolist.R;
import com.example.mytodolist.View.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;

public class SignUpController {
    public static Task<AuthResult> createUser(String pseudo, String mail, String password, Context context) {
        String mailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (TextUtils.isEmpty(pseudo) || TextUtils.isEmpty(mail) || TextUtils.isEmpty(password)) {
            return Tasks.forException(new Exception(context.getString(R.string.missing_fields)));
        }
        else if (!mail.matches(mailPattern)) {
            return Tasks.forException(new Exception(context.getString(R.string.wrong_mail_format)));
        }
        else {
            User user = new User(pseudo, mail, password);
            return RemoteDB.saveUser(user);
        }
    }

    public static void goToLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
