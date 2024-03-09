package com.example.mytodolist.Controller;

import android.content.Context;

import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.Model.LocalDB;
import com.example.mytodolist.Model.RemoteDB;

public class AddLocalTodoController {

    static public void addLocalTodo(Context context, String title) {
        LocalDB localdb = new LocalDB(context);
        TodoList todolist = new TodoList(RemoteDB.generateId("todoLists"), title, RemoteDB.getmAuth().getCurrentUser().getUid());
        localdb.insertTodoList(todolist);
        RemoteDB.saveTodoList(todolist);
    }
}
