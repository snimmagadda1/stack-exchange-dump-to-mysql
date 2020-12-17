package com.snimma1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/** @author snimmagadda1 Init Spring-Boot application */
@SpringBootApplication
@ComponentScan("com.snimma1.config")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
