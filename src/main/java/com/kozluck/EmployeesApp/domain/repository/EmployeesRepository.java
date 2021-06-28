package com.kozluck.EmployeesApp.domain.repository;

import com.kozluck.EmployeesApp.domain.Employee;
import com.kozluck.EmployeesApp.domain.Task;
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
    public Employee getEmployeeById(int id){
        return employees.get(id);
    }

    public void updateEmployee(int id, Employee employee){
        employees.put(id,employee);
    }

    public void createEmployee(String name, String surname){
        Employee newEmployee = new Employee(name,surname);
        newEmployee.setId(Ids.getNewId(employees.keySet()));
        employees.put(Ids.getNewId(employees.keySet()),newEmployee);
    }
    public void addEmployee(Employee employee){
        employee.setId(Ids.getNewId(employees.keySet()));
        employees.put(Ids.getNewId(employees.keySet()),employee);
    }

    public void deleteEmployeeById(Integer id){
        employees.remove(id);
    }

    public Optional<Employee> getEmployee(String name){
        Optional<Employee> employee = employees.values().stream().filter(employee1 -> employee1.getName().equals(name)).findAny();
        return employee;
    }

    public Collection<Employee> getAllEmployees() {
        return employees.values();
    }

    @PostConstruct
    public void initEmployees(){
        Task task = new Task("Task1");

        Employee employee = new Employee("Sebastian", "Rafael",task,"username","password","email@email.com");
        Employee employee1 = new Employee("Kamil","Messi",task,"username","password","email1@email.com");
        createEmployee(employee);
        createEmployee(employee1);


    }

    public boolean findByEmail(String email) {
       for (int i = 0; i < employees.size(); i++){
           if(employees.get(i).getEmail().equals(email)) return true;
       }
       return false;
    }
}
