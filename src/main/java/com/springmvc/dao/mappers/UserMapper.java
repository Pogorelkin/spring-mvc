package com.springmvc.dao.mappers;

import com.springmvc.entities.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return new User(resultSet.getInt("employeeId"),
                resultSet.getString("login"),
                resultSet.getString("password"));
    }
}
