package com.cg.feedback.batch_course.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.cg.feedback.batch_course.dto.BatchCourseDTO;

public interface BatchCourseDAO extends JpaRepository<BatchCourseDTO, String>{
    @Modifying
    void removeCourse(String id) throws Exception;
}