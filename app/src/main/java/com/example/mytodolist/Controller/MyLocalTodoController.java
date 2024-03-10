package com.example.mytodolist.Controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.Model.LocalDB;
import com.example.mytodolist.Model.RemoteDB;
import com.example.mytodolist.View.AddLocalTodoActivity;
import com.example.mytodolist.View.MyLocalTodoActivity;
import com.example.mytodolist.View.SharedTodoActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyLocalTodoController {
    private static LocalDB localdb;

    public static ArrayList<TodoList>  getTodoLists(Context context) {
        localdb = new LocalDB(context);
        RemoteDB.getTodoListsByUserId(RemoteDB.getmAuth().getCurrentUser().getUid(), new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot todoListSnapshot : dataSnapshot.getChildren()) {
                    TodoList todoList = todoListSnapshot.getValue(TodoList.class);
                    if (todoList != null && todoList.getShared() == null) {
                        localdb.insertTodoList(todoList);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Error", databaseError.toException());
            }
        });
        ArrayList<TodoList> todoLists = localdb.getTodoLists();
        return todoLists;
    }

    public static void goToAddLocalTodo(Context context) {
        Intent intent = new Intent(context, AddLocalTodoActivity.class);
        context.startActivity(intent);
    }

    public static void goToSharedTodo(Context context) {
        Intent intent = new Intent(context, SharedTodoActivity.class);
        context.startActivity(intent);
    }
}
