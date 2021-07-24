package com.kozluck.EmployeesApp.domain.repository;

import com.kozluck.EmployeesApp.domain.models.Task;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class CustomTasksRepository {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void updateTask(Task task) {
        em.merge(task);
    }
}
