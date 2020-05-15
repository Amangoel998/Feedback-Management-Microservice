package com.cg.feedback.trainer_user.dto;

import java.io.Serializable;

import com.cg.feedback.trainer_user.id.ProgramTrainerId;

public class TrainerProgramDTO implements Serializable{
	private static final long serialVersionUID = 4566907954458907989L;
	private ProgramTrainerId trainerProgramId;

	public TrainerProgramDTO() {
	}
	public TrainerProgramDTO(ProgramTrainerId trainerprogramId) {
		this.trainerProgramId = trainerprogramId;
	}

	public ProgramTrainerId getTrainerProgramId() {
		return trainerProgramId;
	}

	public void setTrainerProgramId(String trainerId, String programId, String batch) {
		this.trainerProgramId = new ProgramTrainerId(trainerId, programId,batch);
	}

	public void setTrainerProgramId(ProgramTrainerId trainerProgramId) {
		this.trainerProgramId = trainerProgramId;
	}
	
}
