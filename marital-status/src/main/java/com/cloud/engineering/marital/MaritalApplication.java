package com.cloud.engineering.marital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MaritalApplication {
    public static void main(String[] args) {
        SpringApplication.run(MaritalApplication.class, args);
    }
}
