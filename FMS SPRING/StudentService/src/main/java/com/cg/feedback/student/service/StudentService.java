package com.cg.feedback.student.service;

import java.util.List;

import com.cg.feedback.student.dto.StudentDTO;


public interface StudentService {
	List<StudentDTO> getAllStudents();
	StudentDTO getStudentById(String studentId);
	boolean removeStudent(String studentId);
	StudentDTO addStudent(StudentDTO student);

}
