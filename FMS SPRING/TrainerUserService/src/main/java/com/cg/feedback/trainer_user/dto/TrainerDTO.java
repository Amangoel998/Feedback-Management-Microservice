package com.cg.feedback.trainer_user.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

public class TrainerDTO {

	private String trainerId;
	private String trainerName;
	private String trainerSkills;
	private String trainerEmail;

	// constructor
	public TrainerDTO() {
		super();
	}
	public TrainerDTO(String trainerId, String trainerName, String skills, String trainerEmail) {
		super();
		this.trainerId = trainerId;
		this.trainerName = trainerName;
		this.trainerSkills = skills;
		this.trainerEmail = trainerEmail;
	}

	// Getters and Setters
	public String getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public String getTrainerEmail() {
		return trainerEmail;
	}

	public void setTrainerEmail(String trainerEmail) {
		this.trainerEmail = trainerEmail;
	}

	public String getTrainerSkills() {
		return trainerSkills;
	}

	public void setTrainerSkills(String trainerSkills) {
		this.trainerSkills = trainerSkills;
	}

	@Override
	public boolean equals(Object obj) {
		TrainerDTO temp = (TrainerDTO) obj;
		if (temp.getTrainerEmail().equals(this.trainerEmail))
			return true;
		return false;
	}

}
