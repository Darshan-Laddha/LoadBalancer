package com.example.demo.controller;

import com.example.demo.service.CircuitBreakerService;

import reactor.core.publisher.Mono;

import org.apache.hc.core5.http.HttpStatus;
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
 public Mono<ResponseEntity<String>> provideGreetings(@RequestBody String greeting){
     return circuitBreakerService.callDownstreamServer()
         .map(downstreamResult -> {
             if (downstreamResult.equals("SERVICE_UNAVAILABLE")) {
                 // Return a 503 status if the fallback was executed
                 return ResponseEntity.status(HttpStatus.SC_SERVICE_UNAVAILABLE)
                                      .body("Downstream GREETING-SERVICE is unavailable.");
             }
             // Return a 200 OK with the real data
             return ResponseEntity.ok(greeting + " | Result: " + downstreamResult);
         });
 }

//    @GetMapping("/fallbackRoute")
//    public String fallback() {
//    	circuitBreakerService.getFallbackData(new Exception("Server Unavailable"));
//        return "Service is currently unavailable. Please try again later.";
//    }


}
