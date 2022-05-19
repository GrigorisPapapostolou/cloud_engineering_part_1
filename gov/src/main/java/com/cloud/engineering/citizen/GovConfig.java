package com.cloud.engineering.citizen;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GovConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
