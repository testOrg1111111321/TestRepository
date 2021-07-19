package com.kozluck.EmployeesApp.domain.services;

import com.kozluck.EmployeesApp.domain.models.Task;
import com.kozluck.EmployeesApp.domain.repository.CustomTasksRepository;
import com.kozluck.EmployeesApp.domain.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TasksService {

    @Autowired
    TasksRepository tasksRepository;

    @Autowired
    CustomTasksRepository customTasksRepository;

    public List<Task> getAllTasks() {
        return new ArrayList(tasksRepository.findAll());
    }


    public Task getTaskById(int id) {
        return tasksRepository.getTaskById(id);
    }


    public void deleteTask(Task task) {
        tasksRepository.delete(task);
    }

    public void addTask(Task task) {
        if (LocalDateTime.now().isBefore(task.getConvertedDeadlineDateToLocalDateTime())) {
            tasksRepository.save(task);
        }
    }

    public void deleteTaskById(Integer id) {
        tasksRepository.delete(getTaskById(id));
    }

    public List<Task> getAll() {
        return tasksRepository.findAll();
    }

    public void assign(int id) {
        Task task = getTaskById(id);
        if (task.getNumberOfLeftContractors() > 1) {
            task.setNumberOfLeftContractors(task.getNumberOfLeftContractors() - 1);
        } else {
            deleteTaskById(id);
        }
    }

    public void update(Task task){
        customTasksRepository.updateTask(task);
    }

}
