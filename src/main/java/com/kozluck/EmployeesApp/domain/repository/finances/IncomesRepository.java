package com.kozluck.EmployeesApp.domain.repository.finances;

import com.kozluck.EmployeesApp.domain.models.finances.Income;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomesRepository  extends CrudRepository<Income,Integer>{
    List<Income> findAll();

}
