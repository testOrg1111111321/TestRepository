package com.kozluck.EmployeesApp.domain.services;

import com.kozluck.EmployeesApp.domain.models.Finances.Cost;
import com.kozluck.EmployeesApp.domain.repository.CostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostsService {
    @Autowired
    private CostsRepository costsRepository;

    public List<Cost> getCostsByMonth(String month){
        List<Cost> costs = costsRepository.findAll();
        costs.forEach(cost ->{
            if(cost.getMonth() != month) costs.remove(cost);
        });

        return costs;
    }

}
