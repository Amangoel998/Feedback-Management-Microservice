package com.cg.feedback.trainer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.feedback.trainer.dto.TrainerDTO;
import com.cg.feedback.trainer.dto.TrainerSkillDTO;
import com.cg.feedback.trainer.exceptions.CustomException;
import com.cg.feedback.trainer.services.TrainerService;


@RestController
public class TrainerController {
	
	@Autowired
	TrainerService trainerService;
	
	
	@GetMapping(value="/trainers/")
	public List<TrainerDTO> getAlltrainers()
	{
		List<TrainerDTO> trainers=trainerService.getAllTrainers();
		return trainers;
	}
	
	
	
	@GetMapping(value="/trainers/{trainerId}")
	public TrainerDTO gettrainerById(@PathVariable("trainerId") String trainerId)
	{
		TrainerDTO trainer =trainerService.getTrainerById(trainerId).get();
		return trainer;
	}
	
	
	@DeleteMapping(value="/removeTrainer")
	public String deltetrainerById(@RequestBody String trainerId)
	{
		trainerService.removeTrainer(trainerId);
		return "Succesfully Deleted";
	}
	
	@PostMapping(value="/addTrainer")
	public TrainerDTO  addtrainer(@RequestBody TrainerDTO trainer)
	{
		TrainerDTO res =trainerService.addTrainer(trainer);
		return res;
		
	}
		
	 @PutMapping(value = "/maintainTrainerSkills")
	    public ResponseEntity<String> updateTrainerSkill(@RequestBody TrainerSkillDTO trainerSkills)
	            throws CustomException {
	        if (trainerSkills == null)
	            throw new CustomException("Invalid Request");
	        String result = trainerService.updateTrainerSkill(trainerSkills);
	        return new ResponseEntity<String>(result, HttpStatus.OK);
	        }
	
	
	@GetMapping(value="/trainers/{trainerId}/skills/")
	public List<String> getSkills(@PathVariable("trainerId")String trainerId)
	{
		List<String> response=trainerService.getTrainerSkills(trainerId);
		return response;
	}
	
	@GetMapping(value="/trainerExists/{trainerId}")
	public boolean existsById(@PathVariable("trainerId") String trainerId) {
		return trainerService.existsById(trainerId);
	}
}
