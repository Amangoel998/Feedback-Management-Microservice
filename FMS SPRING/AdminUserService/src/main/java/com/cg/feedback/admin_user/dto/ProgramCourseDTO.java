package com.cg.feedback.admin_user.dto;

import java.io.Serializable;
import java.util.Date;

import com.cg.feedback.admin_user.id.ProgramCourseId;

public class ProgramCourseDTO implements Serializable {
	private static final long serialVersionUID = 2932724140469190108L;

	private ProgramCourseId programCourseId;

	private Date startDate;

	private Date endDate;
	
	public ProgramCourseDTO(String courseId, String programId, Date startdate, Date enddate) {
		super();
		this.programCourseId = new ProgramCourseId(courseId, programId);
		this.startDate = startdate;
		this.endDate = enddate;
	}

	public ProgramCourseDTO() {
		super();
	}

	public ProgramCourseId getprogramCourseId() {
		return programCourseId;
	}

	public void setProgramId(ProgramCourseId program) {
		this.programCourseId = program;
	}

	public Date getStartdate() {
		return startDate;
	}

	public void setStartdate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEnddate() {
		return endDate;
	}

	public void setEnddate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "ProgramCourseDTO [ProgramCourseId=" + programCourseId + ", startdate=" + startDate + ", enddate=" + endDate + "]";
	}

}
