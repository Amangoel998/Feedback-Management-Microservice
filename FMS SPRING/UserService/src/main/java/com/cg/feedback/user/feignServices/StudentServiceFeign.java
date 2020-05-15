package com.cg.feedback.user.feignServices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.feedback.user.dto.StudentDTO;

@FeignClient(name = "student-service")
public interface StudentServiceFeign {

	@GetMapping(value="/students/{studentId}")
	public StudentDTO getStudentById(@PathVariable("studentId") String studentId);
	
}
