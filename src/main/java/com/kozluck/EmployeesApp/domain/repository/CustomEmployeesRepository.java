package com.kozluck.EmployeesApp.domain.repository;

import com.kozluck.EmployeesApp.domain.models.Employee;
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
}
