package com.cg.feedback.program_course.services;

import java.util.List;
import java.util.Optional;

import com.cg.feedback.program_course.dto.ProgramCourseDTO;
import com.cg.feedback.program_course.exceptions.CustomException;
import com.cg.feedback.program_course.id.ProgramCourseId;

public interface ProgramCourseService {
	ProgramCourseDTO addCoursePrograms(ProgramCourseDTO list) throws CustomException;
	boolean removeProgramCourse(ProgramCourseId programcourseId) throws CustomException;
	List<ProgramCourseDTO> getCoursePrograms() throws CustomException;
	void removeByCourse(String courseId);
	void removeByProgram(String programId);
	
}
