package com.cloud.engineering.citizen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GovApplication {
    public static void main(String[] args) {
        SpringApplication.run(GovApplication.class, args);
    }
}