package com.example.mytodolist.Controller;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.example.mytodolist.MainActivity;
import com.example.mytodolist.Model.RemoteDB;
import com.example.mytodolist.Model.User;
import com.example.mytodolist.R;
import com.example.mytodolist.View.MyTodoLocalActivity;
import com.example.mytodolist.View.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;

public class LoginController {
    public static void goToSignUp(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    public static void goToToDoLocal(Context context) {
        Intent intent = new Intent(context, MyTodoLocalActivity.class);
        context.startActivity(intent);
    }

    public static Task<AuthResult> loginUser(String mail, String password, Context context) {
        String mailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(password)) {
            return Tasks.forException(new Exception(context.getString(R.string.missing_fields)));
        }
        else if (!mail.matches(mailPattern)) {
            return Tasks.forException(new Exception(context.getString(R.string.wrong_mail_format)));
        }
        else {
            User user = new User(mail, password);
            return RemoteDB.loginUser(user);
        }
    }

    static public void logoutUser(Context context) {
        RemoteDB.getmAuth().signOut();
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
