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

import javax.validation.Valid;
import java.util.List;

@Controller
public class FinanceController {

    @Autowired
    CostsService costsService;

    @Autowired
    IncomesService incomesService;

    @RequestMapping("/finances")
    public String financesMainView(){
        return "finances";
    }
    @RequestMapping("/finances/{year}/{month}")
    public String financesWithYearAndMonth(@PathVariable("year") int year,
                                           @PathVariable("month") String month,
                                           Model model){
        List<List<Object>> costs = costsService.GetCostsByMonthAndYearToChart(month,year);
        List<List<Object>> incomes = incomesService.getIncomesByMonthAndYearToChart(month,year);

        model.addAttribute("costChartData",costs);
        model.addAttribute("incomeChartData",incomes);

        return "finances";
    }

    @RequestMapping("/costs")
    public String costsList(Model model){
        List<Cost> costs = costsService.findAll();
        model.addAttribute("costs",costs);
        return "costs";
    }

    @RequestMapping("/incomes")
    public String incomesList(Model model){
        List<Income> incomes = incomesService.findAll();
        model.addAttribute("incomes",incomes);
        return "incomes";
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
    public String createCost(Model model){
        model.addAttribute("cost",new Cost());
        return "costForm";
    }

    @RequestMapping("/incomeForm")
    public String createIncome(Model model){
        model.addAttribute("income",new Income());
        return "incomeForm";
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