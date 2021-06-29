package com.kozluck.EmployeesApp.domain.repository;

import com.kozluck.EmployeesApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);

}
