package com.springmvc.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    public static AtomicInteger userCounter = new AtomicInteger(1);
    public static AtomicInteger employeeCounter = new AtomicInteger(1);
}
