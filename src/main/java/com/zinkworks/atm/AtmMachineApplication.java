package com.zinkworks.atm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages = { "com.zinkworks.atm" })
@SpringBootApplication
public class AtmMachineApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmMachineApplication.class, args);
	}

	@RestController
	class HelloController{
		@GetMapping("/hello")
		public String sayHello() {
			return "Hello From The Atm App **********!";
		}
	}
	
}
