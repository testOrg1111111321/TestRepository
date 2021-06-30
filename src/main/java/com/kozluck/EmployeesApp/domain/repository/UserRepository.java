package com.kozluck.EmployeesApp.domain.repository;

import com.kozluck.EmployeesApp.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);

    Optional<User> findByUsername(String username);

}
