package com.kozluck.EmployeesApp.controllers;

import com.kozluck.EmployeesApp.domain.models.Project;
import com.kozluck.EmployeesApp.domain.services.ProjectService;
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

    @RequestMapping("/projects")
    public String projects(Model model){
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @RequestMapping("/projectForm")
    public String createProject(Model model){
        model.addAttribute("project", new Project());
        return "projectForm";
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
