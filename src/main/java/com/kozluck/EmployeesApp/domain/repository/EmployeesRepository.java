package com.kozluck.EmployeesApp.domain.repository;

import com.kozluck.EmployeesApp.domain.models.Employee;
import com.kozluck.EmployeesApp.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Integer> {

    Employee getEmployeeById(Integer id);

    Employee getEmployeeByUserId(Integer userId);


    @Transactional
    void delete(Employee employee);


    List<Employee> findAll();

    Employee findByUser(User user);
}
