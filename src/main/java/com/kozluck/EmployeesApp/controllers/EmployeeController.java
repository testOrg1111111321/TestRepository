package com.kozluck.EmployeesApp.controllers;

import com.kozluck.EmployeesApp.domain.Employee;
import com.kozluck.EmployeesApp.domain.services.EmployeeService;
import com.kozluck.EmployeesApp.domain.utils.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController{

    @Autowired
    EmployeeService employeeService;

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public EmployeeController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

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
    public ModelAndView saveEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult){

        try{
            employeeService.addEmployee(employee);
            inMemoryUserDetailsManager.createUser(User.withUsername(employee.getUsername()).password(passwordEncoder().encode(employee.getPassword())).roles("USER").build());
        }catch(UserAlreadyExistException userExists){
            return new ModelAndView("/employeeForm","message","Account with this username/email already exists.");

        }
        return new ModelAndView("redirect:/employees","employees",employeeService.getAllEmployees());

    }





}
