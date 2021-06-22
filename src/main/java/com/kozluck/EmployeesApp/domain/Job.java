package com.kozluck.EmployeesApp.domain;

public class Job {
    private String description;

    public Job(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
