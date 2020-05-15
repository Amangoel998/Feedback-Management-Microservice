package com.cg.feedback.trainer_program;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TrainerProgramServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainerProgramServiceApplication.class, args);
	}

}
