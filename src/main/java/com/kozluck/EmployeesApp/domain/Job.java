package com.kozluck.EmployeesApp.domain;

public class Job {
    private int id;
    private String description;

    public Job(){}
    public Job(String description) {
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
