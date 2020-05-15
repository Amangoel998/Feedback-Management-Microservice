package com.cg.feedback.program_course.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.cg.feedback.program_course.dto.ProgramCourseDTO;
import com.cg.feedback.program_course.id.ProgramCourseId;


@Repository
public interface ProgramCourseDAO extends JpaRepository<ProgramCourseDTO, ProgramCourseId> {
    @Modifying
    void removeProgram(String id) throws Exception;
    @Modifying
    void removeCourse(String id) throws Exception;
}