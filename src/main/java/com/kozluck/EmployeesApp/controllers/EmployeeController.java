package com.kozluck.EmployeesApp.controllers;

import com.kozluck.EmployeesApp.domain.Employee;
import com.kozluck.EmployeesApp.domain.services.EmployeeService;
import com.kozluck.EmployeesApp.domain.utils.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController{

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/")
    public String mainView(){
        return "mainView";
    }

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
    public ModelAndView saveEmployee(@ModelAttribute @Valid Employee employee, ){

        try{
            employeeService.addEmployee(employee);
        }catch(UserAlreadyExistException userExists){
            return new ModelAndView("/employeeForm","message","Account with this username/email already exists.");

        }
        return new ModelAndView("redirect:/employees","employees",employeeService.getAllEmployees());

    }





}
