package com.cg.feedback.admin_user.feignServices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.feedback.admin_user.dto.FeedbackDTO;
import com.cg.feedback.admin_user.exceptions.CustomException;

@FeignClient(name = "feedback-service")
public interface FeedbackServiceFeign {
	@GetMapping(value="/feedbacks/programId={programId}")
	public List<FeedbackDTO> getAllFeedbacksForProgram(@PathVariable("programId")String programId);
	
	@GetMapping(value="/feedbacks/trainerId={trainerId}")
	public List<FeedbackDTO> getAllFeedbacksForTrainer(@PathVariable("trainerId")String trainerId);
	
	@GetMapping(value="/givenfeedbacks/{studentId}")
	public List<String> givenFeedbacks(@PathVariable("studentId") String studentId);
	
	@PostMapping(value = "/giveFeedback", produces = "application/json", consumes = "application/json")
    public FeedbackDTO giveFeedback(@RequestBody FeedbackDTO feedback) throws CustomException;
}
