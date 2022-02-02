package com.example.qikserve.service;

import com.example.qikserve.model.User;
import com.example.qikserve.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@Component
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findOrSave(User user) {
        if(user==null)
            throw new EntityNotFoundException("user must not be null");
        User userDB = userRepository.findByEmail(user.getEmail());
        if (userDB != null)
            return userDB;
        else {
            userRepository.save(user);
            return user;
        }
    }

}
