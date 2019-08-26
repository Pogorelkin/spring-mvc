
package com.springmvc.dao.impl;

import com.springmvc.dao.UserDAO;
import com.springmvc.dao.mappers.UserMapper;
import com.springmvc.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "users")

public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Cacheable
    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM Users", new UserMapper());
    }

    @Cacheable(key = "#id")
    @Override
    public User findById(long id) {
        return jdbcTemplate.queryForObject("SELECT FROM Users WHERE userId = ?", new Object[]{id}, new UserMapper());
    }

    @Override
    public long add(User entity) {
        return jdbcTemplate.update("INSERT INTO Users(login, password) VALUES (?,?)", entity.getLogin(), entity.getPassword());
    }

    @CacheEvict(key = "#id")
    @Override
    public long update(long id, User entity) {
        return jdbcTemplate.update("UPDATE Users SET login = ?, password = ? where userId = ?", entity.getLogin(), entity.getPassword(), id);
    }

    @CacheEvict(key = "#id")
    @Override
    public long deleteById(long id) {
        return jdbcTemplate.update("DELETE FROM Users WHERE userId = ?", id);
    }
}