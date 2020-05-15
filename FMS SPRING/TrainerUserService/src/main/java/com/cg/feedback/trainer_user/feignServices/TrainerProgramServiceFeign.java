package com.cg.feedback.trainer_user.feignServices;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.feedback.trainer_user.dto.TrainerProgramDTO;
import com.cg.feedback.trainer_user.exceptions.CustomException;

@FeignClient(name = "trainer-program-service")
public interface TrainerProgramServiceFeign {

	@GetMapping(value = "/programTrainers", produces = "application/json")
    public List<TrainerProgramDTO> getProgramTrainers() throws CustomException;
	
	@PostMapping(value = "/addTrainerToProgram", produces = "application/json", consumes = "application/json")
    public TrainerProgramDTO assignProgramTrainers(@RequestBody TrainerProgramDTO programtrainer)
            throws CustomException;
	
	@DeleteMapping(value = "/removeTrainerFromProgram", produces = "application/json")
    public String removeTrainerInProgram(@RequestParam("trainerprogramId") String trainerprogramId)
            throws CustomException;
	
	@DeleteMapping(value = "/removeByTrainer", produces = "application/json")
    public String removeByTrainer(@RequestParam("trainerId") String trainerId)
            throws CustomException;
	
	@DeleteMapping(value = "/removeByProgram", produces = "application/json")
    public String removeByProgram(@RequestParam("programId") String programId)
            throws CustomException;
}
