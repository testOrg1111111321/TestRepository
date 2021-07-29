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
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    TasksService tasksService;

    @RequestMapping("/projects")
    public ModelAndView projects(){
        List<Project> projects = projectService.findAll();
        return new ModelAndView("project/projects","projects", projects);
    }

    @RequestMapping("projects/projectForm")
    public ModelAndView createProject(){
        return new ModelAndView("forms/projectForm","project", new Project());
    }
    @RequestMapping("projects/addTaskToProject/{projectId}")
    public ModelAndView chooseTask(@PathVariable("projectId")int id){
        Project project = projectService.findOneById(id);
        List<Task>tasks = tasksService.getAllTasks();
        tasks.removeAll(project.getTasks());
        Map<String, Object> models = new HashMap<>();
        models.put("project",project);
        models.put("tasks",tasks);
        return new ModelAndView("task/addTasksToProject",models);
    }

    @RequestMapping("projects/projectDetails/{projectId}")
    public ModelAndView projectDetails(@PathVariable("projectId") Integer projectId, Model model){
        Project project = projectService.findOneById(projectId);
        return new ModelAndView("project/projectDetails","project",project);
    }

    @RequestMapping(value = "projects/saveProject", method = RequestMethod.POST)
    public String saveProject(@ModelAttribute Project project){
        projectService.save(project);
        return "redirect:/projects";
    }
    @RequestMapping("projects/delete/{id}")
    public String deleteProject(@PathVariable int id) {
        Project project = projectService.findOneById(id);
        projectService.delete(project);
        return "redirect:/projects";
    }

}
