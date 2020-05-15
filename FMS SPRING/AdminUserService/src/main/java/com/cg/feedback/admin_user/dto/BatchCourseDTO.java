package com.cg.feedback.admin_user.dto;

public class BatchCourseDTO {
	
	private String batch;
	private String courseId;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public BatchCourseDTO(String batch, String courseId) {
		super();
		this.courseId = courseId;
		this.batch = batch;
	}

	public BatchCourseDTO() {
		super();
	}

	@Override
	public String toString() {
		return "BatchCourseDTO [courseId=" + courseId + ", batch=" + batch + "]";
	}
	
	
}
