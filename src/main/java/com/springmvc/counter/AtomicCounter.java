package com.springmvc.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    public static AtomicInteger userCounter = new AtomicInteger(1);
    public static AtomicInteger employeeCounter = new AtomicInteger(1);

    public static AtomicInteger getUserCounter() {
        return userCounter;
    }

    public static AtomicInteger getEmployeeCounter() {
        return employeeCounter;
    }

    public static void setUserCounter(AtomicInteger userCounter) {
        AtomicCounter.userCounter = userCounter;
    }

    public static void setEmployeeCounter(AtomicInteger employeeCounter) {
        AtomicCounter.employeeCounter = employeeCounter;
    }
}
