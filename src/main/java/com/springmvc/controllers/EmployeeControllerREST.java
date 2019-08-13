package com.springmvc.controllers;

import com.springmvc.entities.Employee;
import com.springmvc.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeControllerREST {
    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/employee/{employeeId}")
    private @ResponseBody
    Employee getEmployee(@PathVariable int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("/employee")
    private @ResponseBody
    Employee addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);

        return employee;
    }

    @PutMapping("/employee")
    private void
    updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/employee/{employeeId}")
    private void deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteEmployeeById(employeeId);
    }

    @GetMapping("/employee/all")
    private @ResponseBody
    List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }
}
