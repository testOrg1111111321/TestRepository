package com.kozluck.EmployeesApp.domain.services;

import com.kozluck.EmployeesApp.domain.Employee;
import com.kozluck.EmployeesApp.domain.repository.EmployeesRepository;
import com.kozluck.EmployeesApp.domain.utils.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeesRepository employeesRepository;

    public List<Employee> getAllEmployees(){
        return new ArrayList(employeesRepository.getAllEmployees());
    }
    public void deleteEmployee(Employee employee){
        employeesRepository.deleteEmployee(employee);
    }

    public void addEmployee(Employee employee) throws UserAlreadyExistException {
        if(isEmailExisting(employee.getEmail())){
            throw new UserAlreadyExistException("There is an account with that email address: " + employee.getEmail());
        }
        employeesRepository.addEmployee(employee);
    }
    public void updateEmployee( Employee employee){
        employeesRepository.updateEmployee(employee);
    }

    private boolean isEmailExisting(String email){
        return employeesRepository.isEmailExisting(email);
    }

    public Employee getEmployeeById(int id) {
        return employeesRepository.getEmployeeById(id);
    }
}
