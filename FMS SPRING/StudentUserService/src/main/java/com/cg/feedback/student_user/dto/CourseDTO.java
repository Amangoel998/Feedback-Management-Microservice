package com.cg.feedback.student_user.dto;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

public class CourseDTO {
	
	private String courseId;
	
	private String courseName;
	//Constructor
	public CourseDTO(String courseId, String courseName){
		super();
		this.courseId = courseId;
		this.courseName = courseName;
	}
	//Getter and Setters

	public CourseDTO() {
		super();
	}

	@Override
	public String toString() {
		return "CourseDTO [courseId=" + courseId + ", courseName=" + courseName + "]";
	}
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
}
