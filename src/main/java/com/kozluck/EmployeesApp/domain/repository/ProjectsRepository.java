package com.kozluck.EmployeesApp.domain.repository;

import com.kozluck.EmployeesApp.domain.models.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsRepository extends CrudRepository<Project ,Integer> {

    void delete(Project project);

    List<Project> findAll();

    Project findOneById(Integer id);

}
