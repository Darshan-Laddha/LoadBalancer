package com.example.demo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GreetingConfig {

    @Bean
    @LoadBalanced // Essential for Eureka lookup
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }
    
    // Inject and build the final WebClient in the service
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }
}
