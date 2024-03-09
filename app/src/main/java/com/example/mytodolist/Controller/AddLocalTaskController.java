package com.example.mytodolist.Controller;

import android.content.Context;

import com.example.mytodolist.Model.Class.Task;
import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.Model.LocalDB;
import com.example.mytodolist.Model.RemoteDB;

public class AddLocalTaskController {
    public static void addTask(Context context, TodoList todolist, String title) {
        LocalDB localdb = new LocalDB(context);
        TodoList selectedTodo = localdb.getTodoList(todolist.getId());

        Task newTask = new Task(RemoteDB.generateId("todoLists"), title, 0);
        localdb.insertTask(selectedTodo.getId(), newTask);
    }
}
