package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com")
public class SpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringDemoApplication.class);
        application.run(args);
    }
}
