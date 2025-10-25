package com.example.demo.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CircuitBreakerService {

    private final RestTemplate restTemplate;

    public CircuitBreakerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * This method is protected by a circuit breaker.
     * The name "my-backend-service" matches the name in the application.yml
     * "fallbackMethod" points to the method to call when the circuit is open.
     */
    @CircuitBreaker(name = "my-backend-service", fallbackMethod = "getFallbackData")
    public String callDownstreamServer() {
        // This is the call that might fail
        // For example, if the server at "http://my-downstream-server/api/data" is down,
        // it will throw an exception (e.g., HttpServerErrorException, ConnectException).
        // Resilience4j will catch this exception and count it as a failure.

        return restTemplate.getForObject("http://GREETING-SERVICE/greeting/accept-greetings", String.class);
    }

    /**
     * This is the fallback method.
     * It MUST have the same return type as the original method.
     * It's best practice to add the Exception parameter,
     * so you can log what went wrong.
     */
    public String getFallbackData(Exception ex) {
        // This code runs when the circuit is OPEN.
        // You return a default/cached response instead of failing.
        System.out.println("Circuit Breaker is OPEN. Returning fallback data. Error: " + ex.getMessage());
        return "This is fallback data. The server is currently unavailable.";
    }
}