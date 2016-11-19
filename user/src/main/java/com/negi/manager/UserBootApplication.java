package com.negi.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.negi.manager.dao")
public class UserBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserBootApplication.class, args);
    }

}
