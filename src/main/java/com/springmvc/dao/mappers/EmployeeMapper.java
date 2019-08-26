package com.springmvc.dao.mappers;

import com.springmvc.entities.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Employee(resultSet.getInt("employeeId"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getLong("idCardNumber"));
    }
}
