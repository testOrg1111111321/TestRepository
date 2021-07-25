package com.kozluck.EmployeesApp.domain.services.finances;

import com.kozluck.EmployeesApp.domain.models.finances.Cost;
import com.kozluck.EmployeesApp.domain.repository.finances.CostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CostsService {
    @Autowired
    private CostsRepository costsRepository;

    public List<List<Object>> GetCostsByMonthAndYearToChart(String month, int year){
        List<Cost> costs = costsRepository.findAll();
        List<List<Object>> costChartData = new LinkedList<>();

        costs.forEach(cost ->{
            if(cost.getMonth().equals(month) && cost.getYear()==year) costChartData.add(List.of(cost.getSource(),cost.getAmount()));
        });

        return costChartData;
    }

    public void save(Cost cost){
        costsRepository.save(cost);
    }

}
