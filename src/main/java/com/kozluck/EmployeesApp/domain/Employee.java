package com.kozluck.EmployeesApp.domain;

import com.kozluck.EmployeesApp.domain.utils.ValidEmail;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class Employee {
    @Id
    @GeneratedValue
    private int id;

    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    private String surname;

    @ManyToOne
    private Task task;

    @OneToOne
    private User user;


    public Employee() {}

    public Employee(String name, String surname, Task task){
        this.name = name;
        this.surname = surname;
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
