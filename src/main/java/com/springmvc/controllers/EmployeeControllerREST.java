package com.springmvc.controllers;

import com.springmvc.entities.Employee;
import com.springmvc.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeControllerREST {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    private Employee getEmployee(@PathVariable int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    @PutMapping
    private void
    updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{employeeId}")
    private void deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteEmployeeById(employeeId);
    }

    @GetMapping("/all")
    private List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }
}