package com.cg.feedback.admin_user.feignServices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.feedback.admin_user.dto.TrainerDTO;
import com.cg.feedback.admin_user.dto.TrainerSkillDTO;
import com.cg.feedback.admin_user.exceptions.CustomException;

@FeignClient(name = "trainer-service")
public interface TrainerServiceFeign {

	@GetMapping(value="/trainers/")
	public List<TrainerDTO> getAlltrainers();
	
	@GetMapping(value="/trainers/{trainerId}")
	public TrainerDTO gettrainerById(@PathVariable("trainerId") String trainerId);
	
	@DeleteMapping(value="/removeTrainer")
	public String deltetrainerById(@RequestBody String trainerId);
	
	@PostMapping(value="/addTrainer")
	public TrainerDTO  addtrainer(@RequestBody TrainerDTO trainer);
	
	@PutMapping(value = "/maintainTrainerSkills", produces = "application/json", consumes = "application/json")
	public String updateTrainerSkill(@RequestBody TrainerSkillDTO trainerSkills) throws CustomException;
	 
	@GetMapping(value="/trainers/{trainerId}/skills/")
	public List<String> getSkills(@PathVariable("trainerId")String trainerId);
}
