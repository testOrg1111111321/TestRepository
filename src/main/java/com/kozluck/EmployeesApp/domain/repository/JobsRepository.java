package com.kozluck.EmployeesApp.domain.repository;

import com.kozluck.EmployeesApp.domain.Job;
import com.kozluck.EmployeesApp.domain.services.JobsService;
import com.kozluck.EmployeesApp.domain.utils.Ids;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JobsRepository {
    private Map<Integer, Job> jobs = new HashMap<>();

    public void createJob(String jobDescription){
        Job job = new Job(jobDescription);
        job.setId(Ids.getNewId(jobs.keySet()));
        jobs.put(Ids.getNewId(jobs.keySet()),job);
    }
    public Collection<Job> getAllJobs(){
        return jobs.values();
    }

    public Job getJobById(int id){
        return jobs.get(id);
    }

    public void deleteJobById(int id){
        jobs.remove(id);
    }


    public void addJob(Job job) {
        job.setId(Ids.getNewId(jobs.keySet()));
        jobs.put(Ids.getNewId(jobs.keySet()),job);
    }
    @PostConstruct
    public void initJobs(){
        Job job = new Job("Test1");
        Job job1 = new Job("Test2");
        Job job2 = new Job("Test3");
        addJob(job);
        addJob(job1);
        addJob(job2);
    }

}
