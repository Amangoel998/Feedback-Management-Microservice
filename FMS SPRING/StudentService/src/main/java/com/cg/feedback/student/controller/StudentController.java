package com.cg.feedback.student.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.feedback.student.dto.StudentDTO;
import com.cg.feedback.student.service.StudentService;
import com.cg.feedback.student.exception.CustomException;


@RestController
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@GetMapping(value="/students")
	public List<StudentDTO> getAllStudents() throws CustomException{
		List<StudentDTO> students=studentService.getAllStudents();
		return students;
	}
	
	
	@GetMapping(value="/students/{studentId}")
	public StudentDTO getStudentById(@PathVariable("studentId") String studentId)throws CustomException{
		StudentDTO student = studentService.getStudentById(studentId);
		return student;
	}
	
	
	@DeleteMapping(value="/removeStudent")
	public String delteStudentById(@RequestBody String studentId)throws CustomException{
		studentService.removeStudent(studentId);
		return "Succesfully Deleted";
	}
	
	
	@PostMapping(value="/addStudent")
	public StudentDTO  addStudent(@RequestBody StudentDTO student)throws CustomException{
		StudentDTO res =studentService.addStudent(student);
		return res;
		
	}
	public StudentDTO  addStudentFail(StudentDTO student)throws CustomException{
		return new StudentDTO();
	}
	
	
}
