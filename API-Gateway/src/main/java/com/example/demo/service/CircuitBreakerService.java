package com.example.demo.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CircuitBreakerService {

	private final WebClient webClient; // Inject WebClient instead of RestTemplate

    // Constructor to inject WebClient
    public CircuitBreakerService(WebClient webClient) {
        this.webClient = webClient;
    }

    // *** CRITICAL CHANGE: Return type is now Mono<String> ***
    @CircuitBreaker(name = "my-backend-service", fallbackMethod = "getFallbackData")
    public Mono<String> callDownstreamServer() {
        // Use WebClient, which is non-blocking and returns a Mono
        return webClient.get()
                .uri("http://GREETING-SERVICE/greeting/accept-greetings")
                .retrieve()
                .bodyToMono(String.class); // Returns a Mono<String>

        // IMPORTANT: DO NOT use .block() here!
    }

    // *** Fallback method must also return the reactive type ***
    public Mono<String> getFallbackData(Throwable t) {
        System.out.println("--- CIRCUIT OPEN or FAILURE DETECTED ---");
        System.out.println("Error Type: " + t.getClass().getSimpleName());
        
        // Return a Mono containing the fallback string
        return Mono.just("SERVICE_UNAVAILABLE"); 
    }
}