package com.kozluck.EmployeesApp.domain.repository;

import com.kozluck.EmployeesApp.domain.models.Finances.Cost;
import com.kozluck.EmployeesApp.domain.models.Finances.Income;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomesRepository  extends CrudRepository<Income,Integer>{
    List<Income> findAll();

}
