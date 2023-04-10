package com.cloud.engineering.gov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.cloud.engineering.clients")
@PropertySources({@PropertySource("classpath:clients-${spring.profiles.active}.properties")})
public class GovApplication {
    public static void main(String[] args) {
        SpringApplication.run(GovApplication.class, args);
    }
}