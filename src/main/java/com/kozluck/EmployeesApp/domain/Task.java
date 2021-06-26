package com.kozluck.EmployeesApp.domain;

public class Task {
    private int id;
    private String description;
    private int numberOfLeftContractors;

    public Task(){}
    public Task(String description) {
        this.description = description;
    }
    public Task(String description, int numberOfLeftContractors) {
        this.description = description;
        this.numberOfLeftContractors = numberOfLeftContractors;
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

    public int getNumberOfLeftContractors() {
        return numberOfLeftContractors;
    }

    public void setNumberOfLeftContractors(int numberOfLeftContractors) {
        this.numberOfLeftContractors = numberOfLeftContractors;
    }
}
