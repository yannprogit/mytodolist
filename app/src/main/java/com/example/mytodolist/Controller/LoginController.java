package com.example.mytodolist.Controller;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.example.mytodolist.MainActivity;
import com.example.mytodolist.Model.LocalDB;
import com.example.mytodolist.Model.RemoteDB;
import com.example.mytodolist.Model.Class.User;
import com.example.mytodolist.R;
import com.example.mytodolist.View.MyLocalTodoActivity;
import com.example.mytodolist.View.SignUpActivity;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;

public class LoginController {
    private static LocalDB localdb;

    public static void goToSignUp(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    public static void goToToDoLocal(Context context) {
        Intent intent = new Intent(context, MyLocalTodoActivity.class);
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
            return RemoteDB.loginUser(mail, password);
        }
    }

    static public void logoutUser(Context context) {
        RemoteDB.getmAuth().signOut();
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        localdb = new LocalDB(context);
        localdb.resetLocalDB();
    }
}
