package com.kozluck.EmployeesApp.domain;

public class Task {
    private int id;
    private String description;

    public Task(){}
    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
