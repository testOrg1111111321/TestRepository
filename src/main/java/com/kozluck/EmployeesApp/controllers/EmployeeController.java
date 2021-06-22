package com.kozluck.EmployeesApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

public class EmployeeController {

    @RequestMapping
    public String getEmployees(Model model){

        return "employees";
    }




}
