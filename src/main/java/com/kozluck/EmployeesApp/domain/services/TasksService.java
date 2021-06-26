package com.kozluck.EmployeesApp.domain.services;

import com.kozluck.EmployeesApp.domain.Task;
import com.kozluck.EmployeesApp.domain.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TasksService {

    @Autowired
    TasksRepository tasksRepository;

    public List<Task> getAllTasks(){
        return new ArrayList<>(tasksRepository.getAllTasks());
    }

    public void createTask(String taskDescription){
        tasksRepository.createTask(taskDescription);
    }

    public Task getTaskById(int id){
        return tasksRepository.getTaskById(id);
    }

    public void deleteTaskById(int id){
        tasksRepository.deleteTaskById(id);
    }

    public void addTask(Task task) {
        tasksRepository.addTask(task);
    }
}
