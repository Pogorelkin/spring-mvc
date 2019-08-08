package com.springmvc.repositories;

import com.springmvc.entities.Employee;

import java.util.List;

public interface EmployeeRepository {
    void addEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    void deleteEmployeeById(int id);
    void updateEmployee(Employee employee);
    void initEmployees();
}
