package com.cg.feedback.trainer.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.feedback.trainer.dao.TrainerDAO;
import com.cg.feedback.trainer.dto.TrainerDTO;
import com.cg.feedback.trainer.dto.TrainerSkillDTO;
import com.cg.feedback.trainer.exceptions.CustomException;

@Service
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	TrainerDAO trainerDao;

	@Override
	public List<TrainerDTO> getAllTrainers() {
		try {
			List<TrainerDTO> result = trainerDao.findAll();
			if (result == null || result.size() < 1)
				throw new CustomException("Programs Not Found");
			return result;
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public Optional<TrainerDTO> getTrainerById(String trainerId) {

		if (trainerDao.existsById(trainerId))
			return trainerDao.findById(trainerId);
		else
			throw new CustomException("Trainer with id :" + trainerId + " not found");

	}

	@Override
	public boolean removeTrainer(String trainerId) {
		try {
			if (trainerDao.existsById(trainerId)) {
				trainerDao.deleteById(trainerId);
				return true;
			} else
				throw new CustomException("Student Doesn't Exists");
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public TrainerDTO addTrainer(TrainerDTO trainer) {
		try {
			if (trainerDao.existsById(trainer.getTrainerId()))
				throw new CustomException("Trainer with given Id Already Exists");
			TrainerDTO result = trainerDao.save(trainer);
			if (result == null)
				throw new CustomException("Couldn't Add Trainer");
			else
				return result;
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public List<String> getTrainerSkills(String trainerId) {
		if (trainerDao.existsById(trainerId)) {
			TrainerDTO tempTrainer = trainerDao.findById(trainerId).get();
			String skills = tempTrainer.getTrainerSkills();
			List<String> skillList = new ArrayList<String>(Arrays.asList(skills.split(",")));
			return skillList;
		} else
			throw new CustomException("Trainer with ID :" + trainerId + "Not Found");
	}

	@Override
	public String updateTrainerSkill(TrainerSkillDTO trainerSkills) {
		try {
			if (!trainerDao.existsById(trainerSkills.getTrainerId()))
				throw new CustomException("Trainer Not Exists");
			else if (trainerSkills.getSkills().isEmpty())
				throw new CustomException("Skills Provided is Empty");
			else {
				TrainerDTO tempTrainer = trainerDao.findById(trainerSkills.getTrainerId()).get();
				tempTrainer.setTrainerSkills(trainerSkills.getSkills());
				trainerDao.save(tempTrainer);
			}
			return trainerSkills.getSkills();
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public boolean existsById(String trainerId) {
		return trainerDao.existsById(trainerId);
	}

}
