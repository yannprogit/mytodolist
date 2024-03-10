package com.example.mytodolist.Controller;

import android.content.Context;

import com.example.mytodolist.Model.Class.Task;
import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.Model.LocalDB;
import com.example.mytodolist.Model.RemoteDB;

public class UpdLocalTaskController {
    public static void updTask(Context context, Task task, String title) {
        LocalDB localdb = new LocalDB(context);
        localdb.updateTaskTitle(task.getId(), title);
    }
}
