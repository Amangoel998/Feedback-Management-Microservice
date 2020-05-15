package com.cg.feedback.admin_user.dto;

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

	@Override
	public String toString() {
		return "ProgramDTO [programId=" + programId + ", programName=" + programName;
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
		if (temp.getProgramName().equals(this.programName))
			return true;
		return false;
	}

}
