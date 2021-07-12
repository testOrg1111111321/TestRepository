package com.kozluck.EmployeesApp.domain.services;

import com.kozluck.EmployeesApp.domain.models.Employee;
import com.kozluck.EmployeesApp.domain.models.User;
import com.kozluck.EmployeesApp.domain.repository.CustomEmployeesRepository;
import com.kozluck.EmployeesApp.domain.repository.EmployeesRepository;
import com.kozluck.EmployeesApp.domain.utils.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private CustomEmployeesRepository customEmployeesRepository;

    @Autowired
    private UserService userService;

    public List<Employee> getAllEmployees(){
        return new ArrayList(employeesRepository.findAll());
    }
    public void deleteEmployee(Employee employee){
        userService.deleteUser(employee.getUser());
        employeesRepository.delete(employee);
    }

    public void addEmployee(Employee employee) throws UserAlreadyExistException {
        employeesRepository.saveAndFlush(employee);
    }
    public void updateEmployee( Employee employee){
        customEmployeesRepository.updateEmployee(employee);
    }

    public Employee getEmployeeById(int id) {
        return employeesRepository.getEmployeeById(id);
    }

    public Employee getEmployeeByUserId(Integer id) {
        return employeesRepository.getEmployeeByUserId(id);
    }

    public boolean isEmailExisting(String email) {
        Collection<Employee> employees = employeesRepository.findAll();
        employees.stream().filter(employee ->
                employee.getUser().getEmail().equals(email)).collect(Collectors.toList());

        if(employees.size()<1)
            return true;
        else
            return false;
    }

    public Employee findByUser(User user){
        return employeesRepository.findByUser(user);
    }
}
