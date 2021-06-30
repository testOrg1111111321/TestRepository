package com.kozluck.EmployeesApp.domain.repository;

import com.kozluck.EmployeesApp.domain.models.Task;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;


@Repository
public class TasksRepository {


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
    public void deleteTask(Task task){
        em.remove(task);
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
            em.remove(id);
        }


    }

    @PostConstruct
    public void initJobs(){
    }

}
