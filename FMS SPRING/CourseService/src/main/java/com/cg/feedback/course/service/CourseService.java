package com.cg.feedback.course.service;

import java.util.List;
import java.util.Optional;

import com.cg.feedback.course.dto.CourseDTO;

public interface CourseService {
	List<CourseDTO> getAllCourses();
	Optional<CourseDTO> getCourseById(String courseId);
	boolean removeCourse(String courseId);
	CourseDTO addCourse(CourseDTO course);

}
