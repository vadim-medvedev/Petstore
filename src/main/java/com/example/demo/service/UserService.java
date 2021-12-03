package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean save(User user){
        if (userRepository.existsByUsername(user.getUsername())){
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public boolean login(String username, String password){
        return userRepository.existsByUsernameAndPassword(username, password);
    }

    public User getByUsername(String username){
        Optional<User> user = userRepository.getByUsername(username);
        return user.orElse(null);
    }



    public boolean delete(String username) {
        if (userRepository.existsByUsername(username)){
            User user = userRepository.getByUsername(username).get();
            userRepository.delete(user);
            return true;
        }else {
            return false;
        }
    }

    public void update(String username, User user) {
        if (userRepository.existsByUsername(username)){
            userRepository.save(user);
        }else {
            throw new RuntimeException("User with this username not found");
        }
    }
}