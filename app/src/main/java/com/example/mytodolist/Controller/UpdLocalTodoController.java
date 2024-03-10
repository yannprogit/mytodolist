package com.example.mytodolist.Controller;

import android.content.Context;

import com.example.mytodolist.Model.Class.Task;
import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.Model.LocalDB;

public class UpdLocalTodoController {
    public static void updTodo(Context context, TodoList todolist, String title) {
        LocalDB localdb = new LocalDB(context);
        localdb.updateTodoTitle(todolist.getId(), title);
    }
}
