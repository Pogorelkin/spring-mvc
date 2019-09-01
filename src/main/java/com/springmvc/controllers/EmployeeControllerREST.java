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
        return employeeService.getById(employeeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void addEmployee(@RequestBody Employee employee) {
        employeeService.add(employee);
    }

    @PutMapping
    private void
    updateEmployee(@RequestBody Employee employee) {
        employeeService.update(employee);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    private void deleteEmployee(@RequestBody Employee employee) {
        employeeService.delete(employee);
    }

    @GetMapping("/all")
    private List<Employee> getEmployees() {
        return employeeService.getAll();
    }
}