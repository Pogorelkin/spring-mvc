package com.springmvc.services;

import com.springmvc.entities.Employee;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface EmployeeService {
    @WebMethod
    void addEmployee(Employee employee);

    @WebMethod
    List<Employee> getAllEmployees();

    @WebMethod
    Employee getEmployeeById(int id);

    @WebMethod
    void updateEmployee(Employee employee);

    @WebMethod
    void deleteEmployeeById(int id);
}