package com.cg.feedback.student.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class StudentDTO {

	@Id
	@Column(name = "student_id", updatable = false)
	private String studentId;

	@Column(name = "student_name")
	private String studentName;

	@Column(name = "student_email")
	private String studentEmail;

	@Column(name = "batch")
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
