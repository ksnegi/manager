package com.negi.manager;

import com.negi.manager.bean.CassandraHealthCheckHandler;
import com.netflix.appinfo.HealthCheckHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableSidecar
public class CassandraSideCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(CassandraSideCarApplication.class, args);
    }

    @Bean
    public HealthCheckHandler healthCheckHandler() {
        return new CassandraHealthCheckHandler();
    }

}
