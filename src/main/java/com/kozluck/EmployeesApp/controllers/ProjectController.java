package com.kozluck.EmployeesApp.controllers;

import com.kozluck.EmployeesApp.domain.models.Employee;
import com.kozluck.EmployeesApp.domain.models.Project;
import com.kozluck.EmployeesApp.domain.models.Task;
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
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    TasksService tasksService;

    @RequestMapping("/projects")
    public String projects(Model model){
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "project/projects";
    }

    @RequestMapping("/projectForm")
    public String createProject(Model model){
        model.addAttribute("project", new Project());
        return "forms/projectForm";
    }
    @RequestMapping("addTaskToProject/{projectId}")
    public String chooseTask(@PathVariable("projectId")int id, Model model){
        Project project = projectService.findOneById(id);
        model.addAttribute("project",project);

        List<Task>tasks = tasksService.getAllTasks();
        tasks.removeAll(project.getTasks());
        model.addAttribute("tasks",tasks);
        return "task/addTasksToProject";
    }

    @RequestMapping("/projectDetails/{projectId}")
    public String projectDetails(@PathVariable("projectId") Integer projectId, Model model){
        Project project = projectService.findOneById(projectId);
        model.addAttribute("project",project);
        return "project/projectDetails";
    }

    @RequestMapping(value = "saveProject", method = RequestMethod.POST)
    public String saveProject(@ModelAttribute Project project){
        projectService.save(project);
        return "redirect:/projects";
    }
    @RequestMapping("project/delete/{id}")
    public String deleteProject(@PathVariable int id) {
        Project project = projectService.findOneById(id);
        projectService.delete(project);
        return "redirect:/projects";
    }

}
