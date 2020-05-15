package com.cg.feedback.student_user.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

public class ProgramDTO {

	private String programId;
	private String programName;

	// Constructor
	public ProgramDTO(String programId, String programName) {
		super();
		this.programName = programName;
		this.programId = programId;
	}

	public ProgramDTO() {
		super();
	}

	// Getters and Setters
	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	@Override
	public boolean equals(Object obj) {
		ProgramDTO temp = (ProgramDTO) obj;
		if (temp.getProgramId().equals(this.programId))
			return true;
		return false;
	}

}
