package com.example.mytodolist.Controller;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.Model.LocalDB;
import com.example.mytodolist.Model.RemoteDB;
import com.example.mytodolist.R;
import com.example.mytodolist.View.UpdLocalTodoActivity;
import com.google.android.gms.tasks.Tasks;

public class ShareController {
    public static String shareWithUser(String mail, Context context) {
        String mailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (TextUtils.isEmpty(mail)) {
            return context.getString(R.string.missing_fields);
        } else if (!mail.matches(mailPattern)) {
            return context.getString(R.string.wrong_mail_format);
        } else {
            return "oui";
        }
    }
}
