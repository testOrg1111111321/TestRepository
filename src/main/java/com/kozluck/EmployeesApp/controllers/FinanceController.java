package com.kozluck.EmployeesApp.controllers;

import com.kozluck.EmployeesApp.domain.models.finances.Cost;
import com.kozluck.EmployeesApp.domain.models.finances.Income;
import com.kozluck.EmployeesApp.domain.services.finances.CostsService;
import com.kozluck.EmployeesApp.domain.services.finances.IncomesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FinanceController {

    @Autowired
    CostsService costsService;

    @Autowired
    IncomesService incomesService;

    @RequestMapping("/finances")
    public String financesMainView(){
        return "finances/finances";
    }

    @RequestMapping("/finances/{year}/{month}")
    public ModelAndView financesWithYearAndMonth(@PathVariable("year") int year,
                                                 @PathVariable("month") String month){
        List<List<Object>> costs = costsService.GetCostsByMonthAndYearToChart(month,year);
        List<List<Object>> incomes = incomesService.getIncomesByMonthAndYearToChart(month,year);

        Map<String, Object> models = new HashMap<>();
        models.put("incomeChartData",incomes);
        models.put("costChartData",costs);

        return new ModelAndView("finances/finances",models);
    }

    @RequestMapping("/costs")
    public ModelAndView costsList(){
        List<Cost> costs = costsService.findAll();
        return new ModelAndView("finances/costs","costs",costs);
    }

    @RequestMapping("/incomes")
    public ModelAndView incomesList(){
        List<Income> incomes = incomesService.findAll();
        return new ModelAndView("finances/incomes","incomes",incomes);
    }

    @RequestMapping("/cost/delete/{id}")
    public String deleteCost(@PathVariable("id")int costId){
        costsService.delete(costId);
        return "redirect:/finances";
    }

    @RequestMapping("/income/delete/{id}")
    public String deleteIncome(@PathVariable("id")int incomeId){
        incomesService.delete(incomeId);
        return "redirect:/finances";
    }

    @RequestMapping("/costForm")
    public ModelAndView createCost(){
        return new ModelAndView("forms/costForm","cost",new Cost());
    }

    @RequestMapping("/incomeForm")
    public ModelAndView createIncome(){
        return new ModelAndView("forms/incomeForm","income",new Income());
    }

    @PostMapping("/saveCost")
    public String saveCost(@Valid @ModelAttribute Cost cost){
        costsService.save(cost);
        return "redirect:/finances";
    }

    @PostMapping("/saveIncome")
    public String saveIncome(@Valid @ModelAttribute Income income){
        incomesService.save(income);
        return "redirect:/finances";
    }
}
