package com.kozluck.EmployeesApp.domain.utils;

import com.kozluck.EmployeesApp.domain.Employee;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Employee employee = (Employee)o;
        return employee.getPassword().equals(employee.getMatchingPassword());
    }
}
