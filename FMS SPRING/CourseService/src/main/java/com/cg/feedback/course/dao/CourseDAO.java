package com.cg.feedback.course.dao;

import com.cg.feedback.course.dto.CourseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDAO extends JpaRepository<CourseDTO, String>{

}
