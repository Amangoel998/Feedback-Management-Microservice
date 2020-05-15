package com.cg.feedback.trainer_program.dto;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.cg.feedback.trainer_program.id.TrainerProgramId;

@Entity
@Table(name="program_trainers")
@NamedNativeQueries({
	@NamedNativeQuery(
		name="TrainerProgramDTO.removeProgram",
		query = "DELETE * FROM program_trainers WHERE program_id=:id"
	),
	@NamedNativeQuery(
		name="TrainerProgramDTO.removeTrainer",
		query = "DELETE * FROM program_trainers WHERE trainer_id=:id"
	)
})
public class TrainerProgramDTO implements Serializable{
	private static final long serialVersionUID = 4566907954458907989L;

	@EmbeddedId
	private TrainerProgramId trainerProgramId;

	public TrainerProgramDTO() {
	}
	public TrainerProgramDTO(TrainerProgramId trainerprogramId) {
		this.trainerProgramId = trainerprogramId;
	}
	
	public TrainerProgramId getTrainerProgramId() {
		return trainerProgramId;
	}

	public void setTrainerProgramId(String trainerId, String programId, String batch) {
		this.trainerProgramId = new TrainerProgramId(trainerId,programId,batch);
	}

	public void setTrainerProgramId(TrainerProgramId trainerProgramId) {
		this.trainerProgramId = trainerProgramId;
	}
	
}
