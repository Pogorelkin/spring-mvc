package com.springmvc.controllers;

import com.springmvc.entities.Employee;
import com.springmvc.services.EmployeeService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static com.springmvc.counter.AtomicCounter.employeeCounter;

public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    private ModelAndView getEmployees() {
        ModelAndView modelAndView = new ModelAndView("employees");
        modelAndView.addObject(employeeService.getAllEmployees());

        return modelAndView;
    }

    @GetMapping("/add-employee")
    private ModelAndView addEmloyee() {
        return new ModelAndView("add-employee");
    }

    @PostMapping("/add-employee")
    private ModelAndView addEmployee(@RequestParam("firstName") String firstName,
                                     @RequestParam("lastName") String lastName, @RequestParam("idCardNumber") long idCardNumber) {
        Employee employee = new Employee(employeeCounter.getAndIncrement(), firstName, lastName, idCardNumber);

        employeeService.addEmployee(employee);
        return new ModelAndView("redirect:/employees");
    }

    @GetMapping("/get-employee")
    private ModelAndView getEmployee() {
        return new ModelAndView("get-employee");
    }

    @PostMapping("/get-employee")
    private ModelAndView getEmployeeById(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView("employees");
        modelAndView.addObject(employeeService.getEmployeeById(id));

        return modelAndView;
    }

    @GetMapping("/update-employee/{employeeId}")
    private ModelAndView updateEmployee(@PathVariable int employeeId) {
        ModelAndView modelAndView = new ModelAndView("update-employee");
        modelAndView.addObject(employeeService.getEmployeeById(employeeId));

        return modelAndView;
    }

    @PostMapping("/update-employee")
    private ModelAndView updateEmployee(Employee employee) {
        employeeService.updateEmployee(employee);

        return new ModelAndView("update-employee");

    }

    @GetMapping("/delete-employee/{employeeId}")
    private ModelAndView deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteEmployeeById(employeeId);

        return new ModelAndView("redirect:employees");

    }
}
