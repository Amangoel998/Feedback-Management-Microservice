package com.cg.feedback.admin_user.feignServices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.feedback.admin_user.dto.StudentDTO;

@FeignClient(name = "student-service")
public interface StudentServiceFeign {

	@GetMapping(value="/students")
	public List<StudentDTO> getAllStudents();
	
	@GetMapping(value="/students/{studentId}")
	public StudentDTO getStudentById(@PathVariable("studentId") String studentId);
	
	@DeleteMapping(value="/removeStudent")
	public String delteStudentById(@RequestBody String studentId);

	@PostMapping(value="/addStudent")
	public StudentDTO addStudent(@RequestBody StudentDTO student);
}
