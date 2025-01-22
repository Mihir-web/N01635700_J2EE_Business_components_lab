package com.mihir.Lab2;

import com.mihir.Lab2.config.AppConfig;
import com.mihir.Lab2.service.GreetingPrinter;
import com.mihir.Lab2.service.GreetingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Lab2Application {

	public static void main(String[] args) {
		// Initialize the context using AppConfig
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);


//		GreetingService greetingService = context.getBean(GreetingService.class);
//		greetingService.greeting();


		GreetingPrinter printer = context.getBean(GreetingPrinter.class);
		printer.printGreeting();

		GreetingService service1 = context.getBean(GreetingService.class);
		GreetingService service2 = context.getBean(GreetingService.class);

		System.out.println("Are the GreetingService beans the same?" + (service1 == service2));

//		context.close();
	}
}
