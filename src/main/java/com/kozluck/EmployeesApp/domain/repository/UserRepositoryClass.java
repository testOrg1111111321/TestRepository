//package com.kozluck.EmployeesApp.domain.repository;
//
//import com.kozluck.EmployeesApp.domain.User;
//import com.kozluck.EmployeesApp.domain.utils.UserAlreadyExistException;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Repository
//public class UserRepositoryClass {
//    @PersistenceContext
//    EntityManager em;
//
//    @Transactional
//    public void saveUser(User user) throws UserAlreadyExistException {
//        em.persist(user);
//    }
//
//    @Transactional
//    public void deleteUser(User user){
//        em.remove(user);
//    }
//
//
//    public User findByEmail(String email) {
//
//    }
//}
