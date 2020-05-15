package com.cg.feedback.student.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.feedback.student.dto.StudentDTO;

public interface StudentDAO extends JpaRepository<StudentDTO, String>{

}
