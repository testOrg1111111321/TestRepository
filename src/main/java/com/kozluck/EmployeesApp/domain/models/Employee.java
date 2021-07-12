package com.kozluck.EmployeesApp.domain.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;


@Entity(name = "employees")
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private int id;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 30)
    private String name;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 30)
    private String surname;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "employees_tasks",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<Task> tasks = new HashSet<>();

    @OneToOne
    private User user;


    public Employee() {}

    public Employee(String name, String surname, Set<Task> tasks){
        this.name = name;
        this.surname = surname;
        this.tasks = tasks;
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

    public Set<Task> getTasks(){
        return tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        task.getEmployees().add(this);
    }
    public void removeTask(Task task){
        this.tasks.remove(task);
        task.getEmployees().remove(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
