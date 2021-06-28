package com.kozluck.EmployeesApp.domain;


import com.kozluck.EmployeesApp.domain.utils.PasswordMatches;
import com.kozluck.EmployeesApp.domain.utils.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatches
public class Employee {
    private int id;

    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    private String surname;

    private Task task;

    @NotEmpty
    @NotNull
    private String username;

    @NotEmpty
    @NotNull
    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

    @NotEmpty
    @NotNull
    @ValidEmail
    private String email;


    public Employee() {}

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Employee(String name, String surname, Task task , String  username, String  password){
        this.name = name;
        this.surname = surname;
        this.task = task;
        this.username = username;
        this.password = password;
    }
    public Employee(String name, String surname, Task task , String  username, String  password, String email){
        this.name = name;
        this.surname = surname;
        this.task = task;
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMatchingPassword(final String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

}
