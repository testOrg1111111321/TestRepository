package com.kozluck.EmployeesApp.domain.services;

import com.kozluck.EmployeesApp.domain.models.Finances.Income;
import com.kozluck.EmployeesApp.domain.repository.IncomesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IncomesService {
    @Autowired
    IncomesRepository incomesRepository;

    public List<Income> getIncomesByMonth(String month){
        List<Income> incomes = incomesRepository.findAll();
        incomes.forEach(income ->{
            if(income.getMonth() != month) incomes.remove(income);
        });

        return incomes;
    }

}
