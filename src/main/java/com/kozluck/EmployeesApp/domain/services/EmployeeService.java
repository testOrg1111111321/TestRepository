package com.kozluck.EmployeesApp.domain.services;

import com.kozluck.EmployeesApp.domain.models.Employee;
import com.kozluck.EmployeesApp.domain.models.Task;
import com.kozluck.EmployeesApp.domain.models.User;
import com.kozluck.EmployeesApp.domain.repository.CustomEmployeesRepository;
import com.kozluck.EmployeesApp.domain.repository.EmployeesRepository;
import com.kozluck.EmployeesApp.domain.repository.TasksRepository;
import com.kozluck.EmployeesApp.domain.utils.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private TasksService tasksService;

    @Autowired
    private CustomEmployeesRepository customEmployeesRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Scheduled(fixedDelay = 60000)
    public void checkTasks() {

        List<Employee> employees = employeesRepository.findAll();
        List<Task> tasks = tasksService.findAll();
        LocalDateTime now = LocalDateTime.now();

        employees.forEach(employee -> {
            employee.getTasks().forEach(task -> {
                LocalDateTime taskTime = task.getConvertedDeadlineDateToLocalDateTime();
                if (taskTime.isBefore(now)) {
                    Employee employee1 = getEmployeeById(employee.getId());
                    Task task1 = tasksService.getTaskById(task.getId());

                    removeTask(employee1,task1);
                    removeTask(employee1, task1);
                    addNotDoneTask(employee);
                    customEmployeesRepository.updateEmployee(employee);
                    tasksService.update(task);
                    mailService.sendMail(employee.getUser().getId(),"Task not completed.","Hi " + employee.getName() + ".\nYour task was deleted because it was not completed.");
                }
            });
        });

        tasks.forEach(task -> {
            if(task.getEmployees().size()==0 && task.getConvertedDeadlineDateToLocalDateTime().isBefore(now)){
                tasksService.deleteTask(task);
            }
        });
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList(employeesRepository.findAll());
    }

    public void deleteEmployee(Employee employee) {
        customEmployeesRepository.delete(employee);
        userService.deleteUser(employee.getUser());
    }

    public void addEmployee(Employee employee) throws UserAlreadyExistException {
        employeesRepository.saveAndFlush(employee);
    }

    public void updateEmployee(Employee employee) {
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

        return employees.size() < 1;
    }

    public void addTask(Employee employee, Task task) {
        employee.getTasks().add(task);
        task.getEmployees().add(employee);
    }

    public void addNotDoneTask(Employee employee) {
        employee.setNumberOfNotDoneTasks(employee.getNumberOfNotDoneTasks() + 1);
    }

    public void removeTask(Employee employee, Task task) {
        employee.getTasks().remove(task);
        task.getEmployees().remove(employee);
    }

    public Employee findByUser(User user) {
        return employeesRepository.findByUser(user);
    }
}
