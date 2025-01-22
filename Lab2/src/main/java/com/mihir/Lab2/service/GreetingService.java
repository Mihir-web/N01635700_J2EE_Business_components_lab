package com.mihir.Lab2.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class GreetingService {

    public void greeting() {
        System.out.println("Hello, Spring Beans!");
    }

//    @PostConstruct
//    public void init() {
//        System.out.println("GreetingService bean has initialized.");
//    }
//
//    @PreDestroy
//    public void destroy() {
//        System.out.println("GreetingService has destroyed.");
//    }
}
