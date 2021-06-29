package com.kozluck.EmployeesApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;

@SpringBootApplication
public class EmployeesAppApplication {

	@Transactional
	public static void main(String[] args) {
		SpringApplication.run(EmployeesAppApplication.class, args);
	}

}
