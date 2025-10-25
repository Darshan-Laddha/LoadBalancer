package com.example.demo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GreetingConfig {

    @Bean // You're telling Spring: "Here's HOW to make a RestTemplate"
    @LoadBalanced // You're telling Spring Cloud: "NOW, make this one load-balanced"
    public RestTemplate restTemplate() {
        // Even this simple creation needs to be defined
        return new RestTemplate();
    }
}
