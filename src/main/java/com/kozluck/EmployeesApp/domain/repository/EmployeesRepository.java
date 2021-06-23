package com.kozluck.EmployeesApp.domain.repository;

import com.kozluck.EmployeesApp.domain.Employee;
import com.kozluck.EmployeesApp.domain.Job;
import com.kozluck.EmployeesApp.domain.utils.Ids;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class EmployeesRepository {

    Map<Integer, Employee> employees = new HashMap<>();

    public void createEmployee(Employee employee){
        employee.setId(Ids.getNewId(employees.keySet()));
        employees.put(Ids.getNewId(employees.keySet()),employee);
    }

    public void createEmployee(String name, String surname){
        Employee newEmployee = new Employee(name,surname);
        newEmployee.setId(Ids.getNewId(employees.keySet()));
        employees.put(Ids.getNewId(employees.keySet()),newEmployee);
    }

    public void deleteEmployeeById(Integer id){
        employees.remove(id);
    }

    public Optional<Employee> getEmployee(String name){
        Optional<Employee> employee = employees.values().stream().filter(employee1 -> employee1.getName().equals(name)).findAny();
        return employee;
    }
    public void updateKnight(int id, Employee employee){
        employees.put(id,employee);
    }

    public Collection<Employee> getAllEmployees() {
        return employees.values();
    }

    @PostConstruct
    public void initEmployees(){
        Job job = new Job("Praca przykładowa");
        Employee employee = new Employee("Sebastian", "Rafael", job);
        Employee employee1 = new Employee("Kamil","Messi");
        createEmployee(employee);
        createEmployee(employee1);
    }

}
