package com.kozluck.EmployeesApp.domain.services;

import com.kozluck.EmployeesApp.domain.Job;
import com.kozluck.EmployeesApp.domain.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobsService {

    @Autowired
    JobsRepository jobsRepository;

    public List<Job> getAllJobs(){
        return new ArrayList<>(jobsRepository.getAllJobs());
    }

    public void createJob(String jobDescription){
        jobsRepository.createJob(jobDescription);
    }

    public Job getJobById(int id){
        return jobsRepository.getJobById(id);
    }

    public void deleteJobById(int id){
        jobsRepository.deleteJobById(id);
    }

    public void addJob(Job job) {
        jobsRepository.addJob(job);
    }
}
