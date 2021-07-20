package com.kozluck.EmployeesApp.controllers;

import com.kozluck.EmployeesApp.domain.models.Employee;
import com.kozluck.EmployeesApp.domain.models.MyUserDetails;
import com.kozluck.EmployeesApp.domain.models.Task;
import com.kozluck.EmployeesApp.domain.models.User;
import com.kozluck.EmployeesApp.domain.services.EmployeeService;
import com.kozluck.EmployeesApp.domain.services.TasksService;
import com.kozluck.EmployeesApp.domain.services.UserService;
import com.kozluck.EmployeesApp.domain.utils.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    UserService userService;

    @Autowired
    TasksService tasksService;


    @RequestMapping("/")
    public String mainView(Model model) {
        List<? extends GrantedAuthority> userAuth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().collect(Collectors.toList());

        if (userAuth.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/employees";
        } else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
            User user = userService.findByUsernameIs(userDetails.getUsername());
            Employee employee = employeeService.findByUser(user);
            model.addAttribute("employee", employee);
            return "redirect:/employeeView";
        }
    }

    @RequestMapping("/employees")
    public String getEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);

        return "employees";
    }

    @RequestMapping("/employee/details/{id}")
    public String employeeDetails(@PathVariable int id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employeeDetails";
    }

    @RequestMapping("/employee/delete/{id}")
    public String deleteEmloyee(@PathVariable Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        employeeService.deleteEmployee(employee);
        return "redirect:/employees";
    }

    @RequestMapping("/employeeForm")
    public String createEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employeeForm";
    }

    @PostMapping(value = "/saveEmployee")
    public ModelAndView saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult errors) {

        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + "" + error.getDefaultMessage());
            });

            return new ModelAndView("employeeForm", "employee", employee);

        } else {
            try {
                userService.saveUser(employee.getUser());
                employeeService.addEmployee(employee);

            } catch (UserAlreadyExistException userExists) {
                return new ModelAndView("/employeeForm", "message", "Account with this username/email already exists.");
            }

            return new ModelAndView("redirect:/employees", "employees", employeeService.getAllEmployees());
        }
    }


}
