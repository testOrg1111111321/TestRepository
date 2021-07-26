package com.kozluck.EmployeesApp.domain.services;

import com.kozluck.EmployeesApp.domain.models.Project;
import com.kozluck.EmployeesApp.domain.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectsRepository projectsRepository;

    public List<Project> findAll(){
        return projectsRepository.findAll();
    }

    public void delete(Project project){
        projectsRepository.delete(project);
    }

    public Project findOneById(Integer id){
        return projectsRepository.findOneById(id);
    }

    public void save(Project project){
        projectsRepository.save(project);
    }


}
