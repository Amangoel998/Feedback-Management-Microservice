package com.cg.feedback.course.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.feedback.course.dto.CourseDTO;
import com.cg.feedback.course.service.CourseService;


@RestController
public class CourseController {

	@Autowired
	CourseService courseService;
	
	@GetMapping(value="/courses")
	public List<CourseDTO> getAllCourses()
	{
		List<CourseDTO> courses=courseService.getAllCourses();
		return courses;
	}
	
	
	@GetMapping(value="/courses/{courseId}")
	public CourseDTO getCourseById(@PathVariable("courseId") String courseId)
	{
		CourseDTO course =courseService.getCourseById(courseId).get();
		return course;
	}
	
	
	@DeleteMapping(value="/removeCourse")
	public String delteCourseById(@RequestBody String courseId)
	{
		courseService.removeCourse(courseId);
		return "Succesfully Deleted";
	}
	
	
	@PostMapping(value="/addCourse")
	public CourseDTO  addCourse(@RequestBody CourseDTO course)
	{
		CourseDTO res =courseService.addCourse(course);
		return res;
		
	}
	
	
}
