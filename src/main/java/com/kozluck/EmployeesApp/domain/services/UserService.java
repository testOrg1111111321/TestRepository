package com.kozluck.EmployeesApp.domain.services;

import com.kozluck.EmployeesApp.domain.User;
import com.kozluck.EmployeesApp.domain.repository.UserRepository;
import com.kozluck.EmployeesApp.domain.utils.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void saveUser(User user) throws UserAlreadyExistException {
        if(emailExist(user.getEmail())){
            throw new UserAlreadyExistException("There is an account with that email address: " + user.getEmail());
        }
        userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    private boolean emailExist(String email){
        return userRepository.findByEmail(email) != null;
    }

}
