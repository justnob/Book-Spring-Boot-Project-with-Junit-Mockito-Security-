package com.amarnath.restapiproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
	
	@GetMapping("hello")
	public String GetHelloMassage() {
		
		return "Hello There!";
		
	}
	
	

}
