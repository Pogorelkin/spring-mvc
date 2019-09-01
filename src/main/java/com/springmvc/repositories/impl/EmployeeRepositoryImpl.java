package com.springmvc.repositories.impl;

import com.springmvc.entities.Employee;
import com.springmvc.repositories.EmployeeRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Cacheable("employees")
public class EmployeeRepositoryImpl implements EmployeeRepository {
    List<Employee> employeeList = Collections.synchronizedList(new ArrayList<>());

    @CacheEvict
    @Override
    public void add(Employee object) {
        employeeList.add(object);
    }

    @Cacheable
    @Override
    public List<Employee> getAll() {
        return employeeList;
    }

    @Cacheable(key = "#id")
    @Override
    public Employee getById(long id) {
        return employeeList.get((int) id);
    }

    @CacheEvict
    @Override
    public void update(Employee object) {
        employeeList.set((int) object.getEmployeeId(), object);
    }

    @CacheEvict
    @Override
    public void delete(Employee object) {
        employeeList.remove(object);
    }
}
