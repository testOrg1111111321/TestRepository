package com.kozluck.EmployeesApp.domain.utils;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(final String message) {
        super(message);
    }
}
