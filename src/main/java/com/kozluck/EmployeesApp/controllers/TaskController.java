package com.kozluck.EmployeesApp.controllers;

import com.kozluck.EmployeesApp.domain.models.Employee;
import com.kozluck.EmployeesApp.domain.models.Task;
import com.kozluck.EmployeesApp.domain.services.EmployeeService;
import com.kozluck.EmployeesApp.domain.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public String chooseTask(@PathVariable("id") int id, Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);

        List<Task> tasks =  tasksService.getAllTasks();
        tasks.removeAll(employee.getTasks());
        model.addAttribute("tasks", tasks);

        return "chooseTask";
    }

    @RequestMapping(value = "/{employeeId}/assign/{taskId}")
    public String assignTask(@PathVariable("taskId")Integer taskId, @PathVariable("employeeId")Integer employeeId){
        Task task = tasksService.getTaskById(taskId);
        Employee employee = employeeService.getEmployeeById(employeeId);
        employeeService.addTask(employee,task);
        tasksService.assign(taskId);
        employeeService.updateEmployee(employee);

        return "redirect:/employees";
    }

    @RequestMapping("task/delete/{id}")
    public String deleteTask(@PathVariable int id){
        Task task = tasksService.getTaskById(id);
        tasksService.deleteTask(task);
        return "redirect:/tasks";
    }

    @RequestMapping("taskForm")
    public String createTask(Model model){
        model.addAttribute("task", new Task());
        return "taskForm";
    }

    @RequestMapping(value = "saveTask", method = RequestMethod.POST)
    public String saveTask(@ModelAttribute Task task, Model model){
            tasksService.addTask(task);
            return "redirect:/tasks";
    }
}
