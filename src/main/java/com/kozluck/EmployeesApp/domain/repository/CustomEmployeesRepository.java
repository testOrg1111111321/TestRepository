package com.kozluck.EmployeesApp.domain.repository;

import com.kozluck.EmployeesApp.domain.models.Employee;
import com.kozluck.EmployeesApp.domain.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class CustomEmployeesRepository {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void updateEmployee(Employee employee){
        em.merge(employee);
    }

    @Transactional
    public void delete(Employee employee){
        for (Task task : employee.getTasks()){
            task.getEmployees().remove(employee);
        }
        em.remove(employee);

    }
}
