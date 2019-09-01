package com.springmvc.repositories.impl;

import com.springmvc.entities.User;
import com.springmvc.repositories.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Cacheable("users")
public class UserRepositoryImpl implements UserRepository {
    private List<User> userList = Collections.synchronizedList(new ArrayList<>());

    @CacheEvict
    @Override
    public void add(User object) {
        userList.add(object);
    }

    @Cacheable
    @Override
    public List<User> getAll() {
        return userList;
    }

    @Cacheable(key = "#id")
    @Override
    public User getById(long id) {
        return userList.get((int) id);
    }

    @CacheEvict
    @Override
    public void update(User object) {
        userList.set((int) object.getUserId(), object);
    }

    @CacheEvict
    @Override
    public void delete(User object) {
        userList.remove(object);
    }
}
