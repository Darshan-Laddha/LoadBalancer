package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {
	
	@GetMapping("/greeting/accept-greetings")
	public ResponseEntity<String> getResponse(){
		return ResponseEntity.ok("GREETINGS");
	}

}
