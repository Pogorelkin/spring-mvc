package com.springmvc.repositories.impl;

import com.springmvc.entities.User;
import com.springmvc.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.springmvc.counter.AtomicCounter.userCounter;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private List<User> userList = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void addUser(User user) {
        userList.add(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userList;
    }

    @Override
    public User getUserById(int id) {
        return userList.get(id);
    }

    @Override
    public void deleteUserById(int id) {
        userList.remove(id);
    }

    @Override
    @PostConstruct
    public void initUsers() {
        userList.add(new User(userCounter.getAndIncrement(), "login1", "password1"));
        userList.add(new User(userCounter.getAndIncrement(), "maLoGiN", "P@ssW0rD"));
        userList.add(new User(userCounter.getAndIncrement(), "1ogin", "123123"));
    }
}
