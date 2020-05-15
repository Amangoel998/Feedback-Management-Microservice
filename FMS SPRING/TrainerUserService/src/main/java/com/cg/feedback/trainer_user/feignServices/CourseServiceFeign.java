package com.cg.feedback.trainer_user.feignServices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.feedback.trainer_user.dto.CourseDTO;

@FeignClient(name = "course-service")
public interface CourseServiceFeign {

	@GetMapping(value="/courses")
	public List<CourseDTO> getAllCourses();
	
	@GetMapping(value="/courses/{courseId}")
	public CourseDTO getCourseById(@PathVariable("courseId") String courseId);
	
	@DeleteMapping(value="/removeCourse")
	public String delteCourseById(@RequestBody String courseId);
	
	@PostMapping(value="/addCourse")
	public CourseDTO  addCourse(@RequestBody CourseDTO course);
}
