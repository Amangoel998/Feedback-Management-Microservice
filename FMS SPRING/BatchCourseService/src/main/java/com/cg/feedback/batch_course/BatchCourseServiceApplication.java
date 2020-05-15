package com.cg.feedback.batch_course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BatchCourseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchCourseServiceApplication.class, args);
	}

}
