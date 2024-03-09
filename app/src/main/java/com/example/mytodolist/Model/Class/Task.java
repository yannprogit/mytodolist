package com.example.mytodolist.Model.Class;

import java.io.Serializable;

public class Task implements Serializable {
    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public int getValidation() {
        return validation;
    }

    private String title;
    private String id;
    private int validation;

    public Task() {
        super();
    }

    public Task(String id, String title, int validation) {
        super();
        this.title = title;
        this.validation = validation;
        this.id = id;
    }
}
