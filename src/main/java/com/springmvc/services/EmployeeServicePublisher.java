package com.springmvc.services;

import com.springmvc.services.impl.EmployeeServiceImpl;

import javax.xml.ws.Endpoint;

public class EmployeeServicePublisher {
    public static void main(String[] args) {
        Endpoint.publish(  "http://localhost:8081/EmployeeService", new EmployeeServiceImpl());
    }
}
