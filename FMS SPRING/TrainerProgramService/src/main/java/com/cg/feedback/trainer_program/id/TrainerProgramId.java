package com.cg.feedback.trainer_program.id;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TrainerProgramId implements Serializable {
    private static final long serialVersionUID = -4146238750102421129L;
	private String trainerId;
    private String programId;
    private String batch;

    public TrainerProgramId() {
    }

    public TrainerProgramId(String trainerId, String programId, String batch) {
    	this.trainerId = trainerId;
        this.programId = programId;
        this.batch = batch;
    }
    
    
    public String getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    @Override
    public String toString() {
        return "ProgramTrainerId [batch=" + batch + ", programId=" + programId + "]";
    }

}