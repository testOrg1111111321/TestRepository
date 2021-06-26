package com.kozluck.EmployeesApp.controllers;

import com.kozluck.EmployeesApp.domain.Employee;
import com.kozluck.EmployeesApp.domain.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class EmployeeController{

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/employees")
    public String getEmployees(Model model){
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees",employees);

        return "employees";
    }

    @RequestMapping("/employee/details/{id}")
    public String employeeDetails(@PathVariable int id, Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee",employee);
        return "employeeDetails";
    }

    @RequestMapping("/employee/delete/{id}")
    public String deleteEmloyee(@PathVariable int id){
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }

    @RequestMapping("/employeeForm")
    public String createEmployee(Model model){
        model.addAttribute("employee",new Employee());
        return "employeeForm";
    }

    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute Employee employee){
        employeeService.addEmployee(employee);
        return "redirect:/employees";
    }





}
