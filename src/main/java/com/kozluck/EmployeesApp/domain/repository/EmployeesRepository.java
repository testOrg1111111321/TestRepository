package com.kozluck.EmployeesApp.domain.repository;

import com.kozluck.EmployeesApp.domain.models.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;


@Repository
public class EmployeesRepository {

    @PersistenceContext
    EntityManager em;


    public Employee getEmployeeById(int id){
        return em.find(Employee.class,id);
    }

    @Transactional
    public void updateEmployee(Employee employee){
        em.merge(employee);
    }

    @Transactional
    public void addEmployee(Employee employee){
        em.persist(employee);
    }

    @Transactional
    public void deleteEmployee(Employee employee){
        em.remove(employee);
        em.remove(employee.getUser());
    }


    public Collection<Employee> getAllEmployees() {
        return em.createQuery("from Employee", Employee.class).getResultList();
    }


    public boolean isEmailExisting(String email) {
       Query query = em.createQuery("SELECT email FROM User WHERE email = ?1");
       query.setParameter(1,email);
       List list = query.getResultList();
        return list.size() > 0;
    }
}
