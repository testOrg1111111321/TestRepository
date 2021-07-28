package com.kozluck.EmployeesApp.controllers;

import com.kozluck.EmployeesApp.domain.models.Employee;
import com.kozluck.EmployeesApp.domain.models.Project;
import com.kozluck.EmployeesApp.domain.models.Task;
import com.kozluck.EmployeesApp.domain.services.EmployeeService;
import com.kozluck.EmployeesApp.domain.services.MailService;
import com.kozluck.EmployeesApp.domain.services.ProjectService;
import com.kozluck.EmployeesApp.domain.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    TasksService tasksService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ProjectService projectService;

    @Autowired
    MailService mailService;

    @RequestMapping("/tasks")
    public String getTasks(Model model) {
        List<Task> tasks = tasksService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "task/tasks";
    }

    @RequestMapping("addTasksToEmployee/{employeeId}")
    public String chooseTask(@PathVariable("employeeId") int id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);

        List<Task> tasks = tasksService.getAllTasks();
        tasks.removeAll(employee.getTasks());
        model.addAttribute("tasks", tasks);

        return "task/addTasksToEmployee";
    }

    @RequestMapping(value = "/{employeeId}/assignTaskToEmployee/{taskId}")
    public String assignTaskToEmplyoee(@PathVariable("taskId") Integer taskId,
                             @PathVariable("employeeId") Integer employeeId) {
        Task task = tasksService.getTaskById(taskId);
        Employee employee = employeeService.getEmployeeById(employeeId);
        employeeService.addTask(employee, task);
        tasksService.assign(taskId);
        employeeService.updateEmployee(employee);

        mailService.sendMail(employee.getUser().getId(),"New assigned task.","Hi " + employee.getName() + ".\nYou have a new task to do.");
        return "redirect:/employees";
    }

    @RequestMapping(value = "/{employeeId}/unassignTaskFromEmployee/{taskId}")
    public String unassignTaskFromEmployee(@PathVariable("employeeId")Integer employeeId,
                               @PathVariable("taskId") Integer taskId){
        Task task = tasksService.getTaskById(taskId);
        Employee employee = employeeService.getEmployeeById(employeeId);
        employeeService.removeTask(employee,task);
        task.setNumberOfLeftContractors(task.getNumberOfLeftContractors() + 1);
        tasksService.update(task);
        employeeService.updateEmployee(employee);

        mailService.sendMail(employee.getUser().getId(),"New canceled task.","Hi " + employee.getName() + ".\nOne of yours tasks was cancelled.");
        return "redirect:/employees";
    }
    @RequestMapping("/{projectId}/assignTaskToProject/{taskId}")
    public String assignTaskToProject(@PathVariable("projectId") Integer projectId,
                                      @PathVariable("taskId") Integer taskId){
        Task task = tasksService.getTaskById(taskId);
        Project project = projectService.findOneById(projectId);
        task.setProject(project);
        project.getTasks().add(task);
        tasksService.update(task);
        projectService.save(project);

        return "redirect:/projects";
    }
    @RequestMapping("/{projectId}/unassignTaskFromProject/{taskId}")
    public String unassignTaskFromProject(@PathVariable("projectId") Integer projectId,
                                          @PathVariable("taskId") Integer taskId){
        Task task = tasksService.getTaskById(taskId);
        Project project = projectService.findOneById(projectId);
        project.getTasks().remove(task);
        task.setProject(null);
        tasksService.update(task);
        projectService.save(project);
        return "redirect:/";
    }
    @RequestMapping(value = "/doneTask/{employeeId}/{taskId}")
    public String doneTask(@PathVariable("employeeId")Integer emplyoeeId,
                           @PathVariable("taskId")Integer taskId,
                           Model model){
        Task task = tasksService.getTaskById(taskId);
        Employee employee = employeeService.getEmployeeById(emplyoeeId);

        employeeService.removeTask(employee,task);
        employee.setNumberOfDoneTasks(employee.getNumberOfDoneTasks() + 1);
        employeeService.updateEmployee(employee);

        model.addAttribute("employee",employee);
        return "redirect:/";
    }

    @RequestMapping("task/delete/{id}")
    public String deleteTask(@PathVariable int id) {
        Task task = tasksService.getTaskById(id);
        tasksService.deleteTask(task);
        return "redirect:/tasks";
    }

    @RequestMapping("taskForm")
    public String createTask(Model model) {
        model.addAttribute("task", new Task());
        return "forms/taskForm";
    }

    @RequestMapping(value = "saveTask", method = RequestMethod.POST)
    public String saveTask(@ModelAttribute Task task) {
        tasksService.addTask(task);
        return "redirect:/tasks";
    }
}
