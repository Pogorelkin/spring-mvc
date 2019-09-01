package com.springmvc.services.impl;

import com.springmvc.entities.User;
import com.springmvc.repositories.UserRepository;
import com.springmvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void add(User user) {
        userRepository.add(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public void update(User object) {
        userRepository.update(object);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}