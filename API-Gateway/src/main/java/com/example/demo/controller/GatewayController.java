package com.example.demo.controller;

import com.example.demo.service.CircuitBreakerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GatewayController {

    private final CircuitBreakerService circuitBreakerService;

    public GatewayController(CircuitBreakerService circuitBreakerService) {
        this.circuitBreakerService = circuitBreakerService;
    }

    @PostMapping("/provide-greetings")
    public ResponseEntity<String> provideGreetings(@RequestBody String greeting){
        circuitBreakerService.callDownstreamServer();
        return ResponseEntity.ok(greeting);

    }

    @GetMapping("/fallbackRoute")
    public String fallback() {
        return "Service is currently unavailable. Please try again later.";
    }


}
