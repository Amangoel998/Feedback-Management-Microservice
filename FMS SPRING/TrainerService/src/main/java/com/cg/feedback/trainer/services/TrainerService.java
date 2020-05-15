package com.cg.feedback.trainer.services;

import java.util.List;
import java.util.Optional;

import com.cg.feedback.trainer.dto.TrainerDTO;
import com.cg.feedback.trainer.dto.TrainerSkillDTO;

public interface TrainerService {
	List<TrainerDTO> getAllTrainers();
	Optional<TrainerDTO> getTrainerById(String trainerId);
	boolean removeTrainer(String trainerId);
	TrainerDTO addTrainer(TrainerDTO trainer);
	String updateTrainerSkill(TrainerSkillDTO trainerSkill) ;
	List<String> getTrainerSkills(String trainerId);
	boolean existsById(String trainerId);
}
