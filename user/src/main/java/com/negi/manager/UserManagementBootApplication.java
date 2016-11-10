package com.negi.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserManagementBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementBootApplication.class, args);
    }

}
