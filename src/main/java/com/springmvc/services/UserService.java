package com.springmvc.services;

import com.springmvc.entities.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    List<User> getAllUsers();
    User getUserById(long id);
    void deleteUserById(long id);
}
