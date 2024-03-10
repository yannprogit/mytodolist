package com.example.mytodolist.Controller;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.Model.Class.User;
import com.example.mytodolist.Model.LocalDB;
import com.example.mytodolist.Model.RemoteDB;
import com.example.mytodolist.R;
import com.example.mytodolist.View.ShareCallback;
import com.example.mytodolist.View.UpdLocalTodoActivity;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShareController {
    public static String shareWithUser(String mail, Context context, TodoList todolist, ShareCallback callback) {
        String mailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (TextUtils.isEmpty(mail)) {
            return context.getString(R.string.missing_fields);
        } else if (!mail.matches(mailPattern)) {
            return context.getString(R.string.wrong_mail_format);
        } else if (RemoteDB.getmAuth().getCurrentUser().getEmail()==mail) {
            return context.getString(R.string.mail_is_your);
        } else {
            ArrayList<User> users = new ArrayList<>();
            final String[] msg = new String[1];
            RemoteDB.getUsers(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        User user = userSnapshot.getValue(User.class);
                        if (user.getUid()!=RemoteDB.getmAuth().getCurrentUser().getUid()) {
                            users.add(user);
                        }
                    }
                    if (users.size()==0) {
                        msg[0] = context.getString(R.string.user_doesnt_exist);
                    }
                    boolean user_exists = false;
                    int i = 0;
                    do {
                        if (users.get(i).getMail().equals(mail)) {
                            user_exists = true;
                            break;
                        }
                        i++;
                    } while (i < users.size());

                    if (!user_exists) {
                        msg[0] = context.getString(R.string.user_doesnt_exist);
                    }
                    else {
                        if (todolist.getShared()==null) {
                            LocalDB localdb = new LocalDB(context);
                            localdb.deleteTodoList(todolist.getId());
                            todolist.addShare(users.get(i).getUid());
                            RemoteDB.saveTodoList(todolist);
                            msg[0] = "forFirstTime";
                        }
                        else {
                            todolist.addShare(users.get(i).getUid());
                            RemoteDB.saveTodoList(todolist);
                            msg[0] = context.getString(R.string.todolist_shared);
                        }
                    }
                    callback.onShareCompleted(msg[0]);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("Firebase", "Error", databaseError.toException());
                }
            });
        }
        return"";
    }
}
