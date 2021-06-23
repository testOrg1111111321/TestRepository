package com.kozluck.EmployeesApp.domain.services;

import com.kozluck.EmployeesApp.domain.Employee;
import com.kozluck.EmployeesApp.domain.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeesRepository employeesRepository;

    public List<Employee> getAllEmployees(){
        return new ArrayList(employeesRepository.getAllEmployees());
    }


}
