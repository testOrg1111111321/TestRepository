package com.kozluck.EmployeesApp.domain.repository;

import com.kozluck.EmployeesApp.domain.Task;
import com.kozluck.EmployeesApp.domain.utils.Ids;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TasksRepository {
    private Map<Integer, Task> tasks = new HashMap<>();

    public void createTask(String taskDescription){
        Task task = new Task(taskDescription);
        task.setId(Ids.getNewId(tasks.keySet()));
        tasks.put(Ids.getNewId(tasks.keySet()),task);
    }
    public Collection<Task> getAllTasks(){
        return tasks.values();
    }

    public Task getTaskById(int id){
        return tasks.get(id);
    }

    public void deleteTaskById(int id){
        tasks.remove(id);
    }


    public void addTask(Task task) {
        task.setId(Ids.getNewId(tasks.keySet()));
        tasks.put(Ids.getNewId(tasks.keySet()),task);
    }

    public void assign(int id){
        Task task = tasks.get(id);
        if(task.getNumberOfLeftContractors()>1){
            task.setNumberOfLeftContractors(task.getNumberOfLeftContractors() - 1);
        }else{
            tasks.remove(id);
        }


    }

    @PostConstruct
    public void initJobs(){
        Task task = new Task("Test1",2);
        Task task1 = new Task("Test2",1);
        Task task2 = new Task("Test3",2);
        addTask(task);
        addTask(task1);
        addTask(task2);
    }

}
