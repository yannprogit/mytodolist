package com.example.mytodolist.Model.Class;

import java.io.Serializable;
import java.util.ArrayList;

public class TodoList implements Serializable {
    public String getTitle() {
        return title;
    }

    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    private String title;
    private String userId;
    private String id;

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private ArrayList<Task> tasks;

    public ArrayList<String> getShared() {
        return shared;
    }

    private ArrayList<String> shared;

    public TodoList() {
        super();
    }

    public TodoList(String id, String title, String userId) {
        super();
        this.title = title;
        this.userId = userId;
        this.id = id;
        this.tasks = new ArrayList<>();
        this.shared = new ArrayList<>();
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }
    public void addShare(String id) {
        shared.add(id);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
