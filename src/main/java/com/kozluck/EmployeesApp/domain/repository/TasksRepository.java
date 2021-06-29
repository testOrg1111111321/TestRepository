package com.kozluck.EmployeesApp.domain.repository;

import com.kozluck.EmployeesApp.domain.Task;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TasksRepository {
    private Map<Integer, Task> tasks = new HashMap<>();

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createTask(String taskDescription){
        Task task = new Task(taskDescription);
        em.persist(task);
    }

    public Collection<Task> getAllTasks(){
        return em.createQuery("from Task").getResultList();
    }

    public Task getTaskById(int id){
        return em.find(Task.class,id);
    }

    @Transactional
    public void deleteTaskById(int id){
        em.remove(id);
    }

    @Transactional
    public void addTask(Task task) {
        em.persist(task);
    }


    @Transactional
    public void assign(int id){
        Task task = em.find(Task.class,id);
        if(task.getNumberOfLeftContractors()>1){
            task.setNumberOfLeftContractors(task.getNumberOfLeftContractors() - 1);
        }else{
            tasks.remove(id);
        }


    }

    @PostConstruct
    public void initJobs(){
    }

}
