package com.cloud.engineering.gov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.cloud.engineering.clients")
public class GovApplication {
    public static void main(String[] args) {
        SpringApplication.run(GovApplication.class, args);
    }
}