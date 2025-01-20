package com.tareq23.prescription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GeneratePrescriptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneratePrescriptionApplication.class, args);
	}

	@GetMapping("/")
	public String check() {
		return "Generate Prescription!";
	}

}
