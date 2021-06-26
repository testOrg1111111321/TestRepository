package com.kozluck.EmployeesApp.controllers;

import com.kozluck.EmployeesApp.domain.Employee;
import com.kozluck.EmployeesApp.domain.Task;
import com.kozluck.EmployeesApp.domain.services.EmployeeService;
import com.kozluck.EmployeesApp.domain.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    TasksService tasksService;

    @Autowired
    EmployeeService employeeService;



    @RequestMapping("tasks")
    public String getTasks(Model model){
        List<Task> tasks = tasksService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }
    @RequestMapping("chooseTask/{id}")
    public String chooseTask(@PathVariable("id") int id, Model model, Session session){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);

        List<Task> tasks =  tasksService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "chooseTask";
    }

    @RequestMapping(value = "/task/assign/{taskId}")
    public String assignTask(@PathVariable("taskId")int id, @ModelAttribute Employee employee){
        Task task = tasksService.getTaskById(id);
        tasksService.assing(id);
        employee.setTask(task);
        employeeService.updateEmployee(employee.getId(),employee);
        return "redirect:/employees";
    }

    @RequestMapping("task/delete/{id}")
    public String deleteJob(@PathVariable int id){
        tasksService.deleteTaskById(id);
        return "redirect:/tasks";
    }

    @RequestMapping("taskForm")
    public String createTask(Model model){
        model.addAttribute("task", new Task());
        return "taskForm";
    }

    @RequestMapping(value = "saveTask", method = RequestMethod.POST)
    public String saveTask(@ModelAttribute Task task){
        tasksService.addTask(task);
        return "redirect:/tasks";
    }
}
