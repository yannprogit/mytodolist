package com.example.mytodolist.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mytodolist.Model.Class.Task;
import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.Model.Class.User;

import java.util.ArrayList;

public class LocalDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "localdb";
    private static final String TABLE_TODOLIST = "todolists";
    private static final String TABLE_TASK = "tasks";

    public LocalDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table Todolist
        String CREATE_TODOLIST_TABLE = "CREATE TABLE " + TABLE_TODOLIST + " (" +
                "id TEXT PRIMARY KEY," +
                "title TEXT," +
                "userId TEXT);";
        db.execSQL(CREATE_TODOLIST_TABLE);
        //Table Task
        String CREATE_TASK_TABLE = "CREATE TABLE " + TABLE_TASK + " (" +
                "id TEXT PRIMARY KEY," +
                "title TEXT," +
                "validation INTEGER," +
                "todolistId TEXT," +
                "FOREIGN KEY(todolistId) REFERENCES " + TABLE_TODOLIST + "(id));";
        db.execSQL(CREATE_TASK_TABLE);
    }
    public ArrayList<Task> getTasks(String todolistId) {
        ArrayList<Task> tasks = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_TASK + " WHERE todolistId = '" + todolistId + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);//new String[]{todolistId}

        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex("id");
                int titleIndex = cursor.getColumnIndex("title");
                int validationIndex = cursor.getColumnIndex("validation");
                String id = cursor.getString(idIndex);
                String title = cursor.getString(titleIndex);
                int validation = cursor.getInt(validationIndex);

                Task task = new Task(id, title, validation);

                tasks.add(task);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return tasks;
    }

    public ArrayList<TodoList> getTodoLists() {
        ArrayList<TodoList> todoLists = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_TODOLIST;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex("id");
                int titleIndex = cursor.getColumnIndex("title");
                int userIdIndex = cursor.getColumnIndex("userId");
                String id = cursor.getString(idIndex);
                String title = cursor.getString(titleIndex);
                String userId = cursor.getString(userIdIndex);

                TodoList todoList = new TodoList(id, title, userId);
                //ArrayList<task> tasks = getTasks(id);
                //todoList.setTasks(tasks);
                Cursor cursorTask = db.rawQuery("SELECT * FROM " + TABLE_TASK + " WHERE todolistId = '" + id + "'", null);
                if (cursorTask.getCount() > 0 && cursorTask.moveToFirst()) {
                    do {
                        int idTaskIndex = cursorTask.getColumnIndex("id");
                        int titleTaskIndex = cursorTask.getColumnIndex("title");
                        int validationTaskIndex = cursorTask.getColumnIndex("validation");
                        String idTask = cursorTask.getString(idTaskIndex);
                        String titleTask = cursorTask.getString(titleTaskIndex);
                        int validationTask = cursorTask.getInt(validationTaskIndex);

                        Task task = new Task(idTask, titleTask, validationTask);

                        todoList.addTask(task);
                    } while (cursorTask.moveToNext());
                }
                cursorTask.close();
                /*task task = new task("idTask", "titleTask", 0);
                todoList.addTask(task);*/
                todoLists.add(todoList);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return todoLists;
    }

    public TodoList getTodoList(String todoId) {
        ArrayList<TodoList> todoLists = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_TODOLIST + " WHERE id = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{todoId});
        TodoList todoList = null;

        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex("id");
            int titleIndex = cursor.getColumnIndex("title");
            int userIdIndex = cursor.getColumnIndex("userId");
            String id = cursor.getString(idIndex);
            String title = cursor.getString(titleIndex);
            String userId = cursor.getString(userIdIndex);

            todoList = new TodoList(id, title, userId);
            ArrayList<Task> tasks = getTasks(id);

            todoList.setTasks(tasks);
        }

        cursor.close();
        db.close();

        return todoList;
    }

    public void updateTaskValidation(String taskId, int newValidation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("validation", newValidation);

        String whereClause = "id=?";
        String[] whereArgs = new String[]{taskId};

        db.update(TABLE_TASK, values, whereClause, whereArgs);

        db.close();
    }

    public void updateTodoTitle(String todoId, String newTitle) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", newTitle);

        String whereClause = "id=?";
        String[] whereArgs = new String[]{todoId};

        db.update(TABLE_TODOLIST, values, whereClause, whereArgs);

        db.close();
    }

    public void insertLocalTodoLists(ArrayList<TodoList> todoLists) {
        Log.d("OnEstLa","ça insert local fort !");
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        db.delete(TABLE_TASK, null, null);
        db.delete(TABLE_TODOLIST, null, null);

        for (TodoList todoList : todoLists) {
            ContentValues todoListValues = new ContentValues();
            todoListValues.put("id", todoList.getId());
            todoListValues.put("title", todoList.getTitle());
            todoListValues.put("userId", todoList.getUserId());
            db.insert(TABLE_TODOLIST, null, todoListValues);
            if (todoList.getTasks() != null) {
                for (Task task : todoList.getTasks()) {
                    ContentValues taskValues = new ContentValues();
                    taskValues.put("id", task.getId());
                    taskValues.put("title", task.getTitle());
                    taskValues.put("validation", task.getValidation());
                    taskValues.put("todolistId", todoList.getId());
                    db.insert(TABLE_TASK, null, taskValues);
                }
            }
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void insertTodoList(TodoList todoList) {
        Log.d("OnEstLa","ça insert local fort ! : " + todoList.getTitle());
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        ContentValues todoListValues = new ContentValues();
        todoListValues.put("id", todoList.getId());
        todoListValues.put("title", todoList.getTitle());
        todoListValues.put("userId", todoList.getUserId());
        db.insert(TABLE_TODOLIST, null, todoListValues);

        if (todoList.getTasks() != null) {
            for (Task task : todoList.getTasks()) {
                ContentValues taskValues = new ContentValues();
                taskValues.put("id", task.getId());
                taskValues.put("title", task.getTitle());
                taskValues.put("validation", task.getValidation());
                taskValues.put("todolistId", todoList.getId());
                db.insert(TABLE_TASK, null, taskValues);
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void insertTask(String todolistId, Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        ContentValues taskValues = new ContentValues();
        taskValues.put("id", task.getId());
        taskValues.put("title", task.getTitle());
        taskValues.put("validation", task.getValidation());
        taskValues.put("todolistId", todolistId);
        db.insert(TABLE_TASK, null, taskValues);
        db.setTransactionSuccessful();

        db.endTransaction();
    }

    public void resetLocalDB() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_TASK, null, null);
        db.delete(TABLE_TODOLIST, null, null);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) { // Upgrade pas très fin
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODOLIST);
            onCreate(db);
        }
    }
}
