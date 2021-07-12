package com.kozluck.EmployeesApp.domain.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "tasks")
public class Task{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private int id;
    private String title;
    private String description;
    private int numberOfLeftContractors;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();;


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

}
