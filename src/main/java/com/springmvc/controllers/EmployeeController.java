package com.springmvc.controllers;

import com.springmvc.entities.Employee;
import com.springmvc.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    private ModelAndView getEmployees() {
        ModelAndView modelAndView = new ModelAndView("employees");
        modelAndView.addObject("employees", employeeService.getAll());
        return modelAndView;
    }

    @GetMapping("/add")
    private ModelAndView addEmployee() {
        return new ModelAndView("add-employee");
    }

    @GetMapping("/get/${id}")
    private ModelAndView getEmployee(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("get-employee");
        modelAndView.addObject(employeeService.getById(id));
        return modelAndView;
    }

    @GetMapping("/update")
    private ModelAndView updateEmployee() {
        return new ModelAndView("update-employee");
    }

    @GetMapping("/delete")
    private ModelAndView deleteEmployee() {
        return new ModelAndView("delete-employee");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ModelAndView postEmployee(Employee employee) {
        employeeService.add(employee);
        return new ModelAndView("redirect:/employees");
    }
}