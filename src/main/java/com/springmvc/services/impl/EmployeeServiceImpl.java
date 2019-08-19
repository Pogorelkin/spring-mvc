package com.springmvc.services.impl;

import com.springmvc.entities.Employee;
import com.springmvc.repositories.EmployeeRepository;
import com.springmvc.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.addEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteEmployeeById(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.updateEmployee(employee);
    }
}
