package com.springmvc.services;

import com.springmvc.entities.Employee;

import java.util.List;

public interface EmployeeService {
    void addEmployee(Employee  employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(long id);
    void updateEmployee(Employee employee);
    void deleteEmployeeById(long id);
}
