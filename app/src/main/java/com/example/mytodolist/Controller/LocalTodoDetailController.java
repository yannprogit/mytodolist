package com.example.mytodolist.Controller;

import android.content.Context;
import android.content.Intent;

import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.Model.LocalDB;
import com.example.mytodolist.Model.RemoteDB;
import com.example.mytodolist.View.AddLocalTaskActivity;
import com.example.mytodolist.View.AddLocalTodoActivity;

public class LocalTodoDetailController {

    public static void goToAddLocalTask(Context context, TodoList todolist) {
        LocalDB localdb = new LocalDB(context);
        Intent intent = new Intent(context, AddLocalTaskActivity.class);

        TodoList selectedTodo = localdb.getTodoList(todolist.getId());
        intent.putExtra("selectedTodo", selectedTodo);
        context.startActivity(intent);
    }

    public static TodoList getTodoList(Context context, TodoList todolist) {
        LocalDB localdb = new LocalDB(context);
        TodoList selectedTodo = localdb.getTodoList(todolist.getId());
        return selectedTodo;
    }

    public static void saveTodoList(Context context, TodoList todolist) {
        LocalDB localdb = new LocalDB(context);
        TodoList selectedTodo = localdb.getTodoList(todolist.getId());
        RemoteDB.saveTodoList(selectedTodo);
    }
}
