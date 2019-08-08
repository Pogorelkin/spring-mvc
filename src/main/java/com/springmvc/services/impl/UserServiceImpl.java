package com.springmvc.services.impl;

import com.springmvc.entities.User;
import com.springmvc.repositories.UserRepository;
import com.springmvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteUserById(id);
    }


}
