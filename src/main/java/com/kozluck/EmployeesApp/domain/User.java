package com.kozluck.EmployeesApp.domain;

import com.kozluck.EmployeesApp.domain.utils.ValidEmail;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class User  {
    @Id
    @GeneratedValue
    private int id;

    private String username;
    private String password;

    @NotEmpty
    @NotNull
    @ValidEmail
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
