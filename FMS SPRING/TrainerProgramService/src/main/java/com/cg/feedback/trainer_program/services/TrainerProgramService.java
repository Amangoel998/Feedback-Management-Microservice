package com.cg.feedback.trainer_program.services;

import java.util.List;
import com.cg.feedback.trainer_program.dto.TrainerProgramDTO;
import com.cg.feedback.trainer_program.exceptions.CustomException;
import com.cg.feedback.trainer_program.id.TrainerProgramId;

public interface TrainerProgramService {
	TrainerProgramDTO addTrainerPrograms(TrainerProgramDTO trainer);
	boolean removeTrainerProgram(TrainerProgramId trainerprogramId) throws CustomException;
	List<TrainerProgramDTO> getTrainerPrograms();
	void removeByTrainer(String trainerId);
	void removeByProgram(String programId);
	TrainerProgramDTO findTrainer(String id, String batch);
}
