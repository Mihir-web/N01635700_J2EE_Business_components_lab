package com.mihir.Lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GreetingPrinter {
    private final GreetingService greetingService;

    @Autowired
    public GreetingPrinter(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public void printGreeting() {
        greetingService.greeting();
    }
}

