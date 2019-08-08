package com.springmvc.services;

import com.springmvc.entities.Employee;

import java.util.List;

public interface EmployeeService {
    void addEmployee(Employee  employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    void updateEmployee(Employee employee);
    void deleteEmployeeById(int id);
}
