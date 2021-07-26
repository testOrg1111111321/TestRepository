package com.kozluck.EmployeesApp.domain.services.finances;

import com.kozluck.EmployeesApp.domain.models.finances.Income;
import com.kozluck.EmployeesApp.domain.repository.finances.IncomesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class IncomesService {
    @Autowired
    IncomesRepository incomesRepository;

    public List<List<Object>> getIncomesByMonthAndYearToChart(String month, int year){
        List<Income> incomes = incomesRepository.findAll();
        List<List<Object>> incomeChartData = new LinkedList<>();
        incomes.forEach(income ->{
            if(income.getMonth().equals(month) && income.getYear()== year) incomeChartData.add(List.of(income.getSource(),income.getAmount()));
        });

        return incomeChartData;
    }

    public List<Income> findAll(){
        return incomesRepository.findAll();
    }

    public void delete(int id){
        incomesRepository.deleteById(id);
    }

    public void save(Income income){
        incomesRepository.save(income);
    }
}
