package com.springmvc.repositories.impl;

import com.springmvc.entities.Employee;
import com.springmvc.repositories.EmployeeRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.springmvc.counter.AtomicCounter.employeeCounter;

@Cacheable("employees")
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private List<Employee> employeeList = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    @Cacheable(value = "employees")
    @Override
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    @Cacheable(value = "employees", key = "#id")
    @Override
    public Employee getEmployeeById(int id) {
        return employeeList.get(id - 1);
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeeList.remove(id - 1);
    }


    @Override
    public void updateEmployee(Employee employee) {
        employeeList.set(employee.getEmployeeId(), employee);
    }

    @PostConstruct
    @Override
    public void initEmployees() {
        employeeList.add(new Employee(employeeCounter.getAndIncrement(), "Ivan", "Ivanov", 111));
        employeeList.add(new Employee(employeeCounter.getAndIncrement(), "Stepan", "Stepanov", 222));
        employeeList.add(new Employee(employeeCounter.getAndIncrement(), "Vasily", "Vasiliev", 333));
    }
}
