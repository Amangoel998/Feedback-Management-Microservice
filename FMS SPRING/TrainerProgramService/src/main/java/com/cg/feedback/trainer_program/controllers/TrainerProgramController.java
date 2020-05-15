package com.cg.feedback.trainer_program.controllers;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.feedback.trainer_program.exceptions.CustomException;
import com.cg.feedback.trainer_program.id.TrainerProgramId;
import com.cg.feedback.trainer_program.dto.TrainerProgramDTO;
import com.cg.feedback.trainer_program.services.TrainerProgramService;


@RestController
public class TrainerProgramController {
	
	@Autowired
	TrainerProgramService trainerProgramService;
	
	@GetMapping(value = "/programTrainers", produces = "application/json")
    public List<TrainerProgramDTO> getProgramTrainers() throws CustomException {
        
        List<TrainerProgramDTO> result = trainerProgramService.getTrainerPrograms();
        return result;
    }
	
	@PostMapping(value = "/addTrainerToProgram", produces = "application/json", consumes = "application/json")
    public TrainerProgramDTO assignProgramTrainers(@RequestBody TrainerProgramDTO programtrainer)
            throws CustomException {
        if (programtrainer == null)
            throw new CustomException("Invalid Request");
        TrainerProgramDTO result = trainerProgramService.addTrainerPrograms(programtrainer);
        return result;
    }
	
	@DeleteMapping(value = "/removeTrainerFromProgram", produces = "application/json")
    public String removeTrainerInProgram(@RequestParam("trainerprogramId") String trainerprogramId)
            throws CustomException {
        if (trainerprogramId == null || trainerprogramId.isEmpty())
            throw new CustomException("Invalid Request");
            
        StringTokenizer stkr = new StringTokenizer(trainerprogramId, "-");
        trainerProgramService.removeTrainerProgram(new TrainerProgramId(stkr.nextToken(), stkr.nextToken(), stkr.nextToken()));
        return "Trainer removed from Program";
    }
	
	@GetMapping(value = "/getProgramTrainer", produces = "application/json")
    public TrainerProgramDTO getProgramTrainer(@RequestParam("batchprogram") String batchprogram) throws CustomException {
		if (batchprogram == null || batchprogram.isEmpty())
            throw new CustomException("Invalid Request");
            
        StringTokenizer stkr = new StringTokenizer(batchprogram, "-");
        TrainerProgramDTO result = trainerProgramService.findTrainer(stkr.nextToken(), stkr.nextToken());
        if(result== null)
            throw new CustomException("Trainer Program Not Found");
        return result;
    }
	
	@DeleteMapping(value = "/removeByTrainer", produces = "application/json")
    public String removeByTrainer(@RequestParam("trainerId") String trainerId)
            throws CustomException {
		if(trainerId == null || trainerId.isEmpty())
			throw new CustomException("Invalid Request");
		trainerProgramService.removeByTrainer(trainerId);
        return "Trainer Programs Removed";
    }
	
	@DeleteMapping(value = "/removeByProgram", produces = "application/json")
    public String removeByProgram(@RequestParam("programId") String programId)
            throws CustomException {
		if(programId == null || programId.isEmpty())
			throw new CustomException("Invalid Request");
		trainerProgramService.removeByProgram(programId);
        return "Trainer Programs Removed";
    }
}
