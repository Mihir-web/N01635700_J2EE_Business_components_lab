package com.mihir.Lab2.config;

import com.mihir.Lab2.service.GreetingPrinter;
import com.mihir.Lab2.service.GreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "com.mihir.Lab2")
public class AppConfig {
    @Bean
    @Scope("prototype")
    public GreetingService GreetingService(){
        return new GreetingService();
    }

    @Bean
    public GreetingPrinter greetingPrinter(GreetingService greetingService) {
        return new GreetingPrinter(greetingService);
    }
}
