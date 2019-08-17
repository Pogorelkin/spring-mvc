package com.springmvc.services;

import com.springmvc.repositories.EmployeeRepository;
import com.springmvc.soap.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EmployeesEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8081";

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeesEndpoint(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEmployeesRequest")
    @ResponsePayload
    public GetAllEmployeesResponse getAllEmployees(@RequestPayload GetAllEmployeesRequest getAllEmployeesRequest){
        GetAllEmployeesResponse getAllEmployeesResponse = new GetAllEmployeesResponse();
        getAllEmployeesResponse.setEmployees(employeeRepository.getAllEmployees());
        return getAllEmployeesResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByIdRequest")
    @ResponsePayload
    public GetEmployeeByIdResponse getEmployeeById(@RequestPayload GetEmployeeByIdRequest getEmployeeByIdRequest){
        GetEmployeeByIdResponse getEmployeeByIdResponse = new GetEmployeeByIdResponse();
        getEmployeeByIdResponse.setEmployee(employeeRepository.getEmployeeById(getEmployeeByIdRequest.getEmployeeId()));
        return getEmployeeByIdResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
    public void addEmployee(@RequestPayload AddEmployeeRequest addEmployeeRequest){
        employeeRepository.addEmployee(addEmployeeRequest.getEmployee());
    }
}