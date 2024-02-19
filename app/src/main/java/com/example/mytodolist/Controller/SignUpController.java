package com.example.mytodolist.Controller;

import static android.provider.Settings.System.getString;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mytodolist.Model.RemoteDB;
import com.example.mytodolist.Model.User;
import com.example.mytodolist.R;
import com.example.mytodolist.View.LoginActivity;
import com.example.mytodolist.View.SignUpActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

public class SignUpController {
    public static Task<Void> createUser(String pseudo, String mail, String password, Context context) {
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
            /*return RemoteDB.userExists(mail, pseudo).continueWithTask(new Continuation<Boolean, Task<Void>>() {
                @Override
                public Task<Void> then(@NonNull Task<Boolean> task) throws Exception {
                    if (task.isSuccessful()) {
                        boolean userExists = task.getResult();

                        if (userExists) {
                            return Tasks.forException(new Exception(context.getString(R.string.user_already_exists)));
                        } else {
                            User user = new User(pseudo, mail, password);
                            return RemoteDB.saveUser(user);
                        }
                    } else {
                        return Tasks.forException(task.getException());
                    }
                }
            });*/
        }
    }

    public static void goToLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
