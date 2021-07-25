package com.kozluck.EmployeesApp.domain.repository.finances;

import com.kozluck.EmployeesApp.domain.models.finances.Cost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostsRepository extends CrudRepository<Cost,Integer> {
    List<Cost> findAll();

}
