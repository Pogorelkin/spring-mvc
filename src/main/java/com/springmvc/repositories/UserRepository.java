package com.springmvc.repositories;

import com.springmvc.entities.User;

import java.util.List;

public interface UserRepository {
    void addUser(User user);
    List<User> getAllUsers();
    User getUserById(int id);
    void deleteUserById(int id);
    void initUsers();
}
