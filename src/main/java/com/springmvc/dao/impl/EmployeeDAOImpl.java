package com.springmvc.repositories.impl;

import com.springmvc.entities.Employee;
import com.springmvc.repositories.EmployeeRepository;
import com.springmvc.repositories.mappers.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "employees")
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Cacheable
    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM Employees", new EmployeeMapper());
    }

    @Override
    @Cacheable(key = "#id")
    public Employee findById(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Employees WHERE employeeId = ?", new Object[]{id}, new EmployeeMapper());
    }

    @Override
    @CacheEvict
    public long add(Employee entity) {
        return jdbcTemplate.update("INSERT INTO Employees(firstName, lastName, idCardNumber) VALUES (?,?,?)",
                entity.getFirstName(), entity.getLastName(), entity.getIdCardNumber());
    }

    @Override
    @CacheEvict(key = "#id")
    public long update(long id, Employee entity) {
        return jdbcTemplate.update("UPDATE Employees SET firstName = ?, lastName = ?, idcardNumber = ? WHERE employeeId = ?",
                entity.getFirstName(), entity.getLastName(), entity.getIdCardNumber(), id);
    }

    @Override
    @CacheEvict(key = "#id")
    public long deleteById(long id) {
        return jdbcTemplate.update("DELETE FROM Employees WHERE employeeId = ? ", id);
    }
}