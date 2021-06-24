package com.kozluck.EmployeesApp.controllers;

import com.kozluck.EmployeesApp.domain.Job;
import com.kozluck.EmployeesApp.domain.services.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JobController {

    @Autowired
    JobsService jobsService;

    @RequestMapping("jobs")
    public String getJobs(Model model){
        List<Job> jobs = jobsService.getAllJobs();
        model.addAttribute("jobs", jobs);
        return "jobs";
    }

    @RequestMapping("jobs/delete/{id}")
    public String deleteJob(@PathVariable int id){
        jobsService.deleteJobById(id);
        return "redirect:/jobs";
    }
}
