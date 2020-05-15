package com.cg.feedback.program_course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProgramCourseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgramCourseServiceApplication.class, args);
	}

}
