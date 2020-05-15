package com.cg.feedback.trainer_user.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

public class StudentDTO {

	private String studentId;
	private String studentName;
	private String studentEmail;
	private String batch;

	// Constructor
	public StudentDTO() {
		super();
	}

	public StudentDTO(String studentId, String studentName, String studentEmail, String batch) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.batch = batch;
	}

	// getter and Setters

	@Override
	public String toString() {
		return "StudentDTO [studentId=" + studentId + ", studentName=" + studentName + ", studentPass="
				+ ", studentEmail=" + studentEmail + ", batch=" + batch;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String student_name) {
		this.studentName = student_name;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
}
