package com.cg.feedback.feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.feedback.feedback.dto.FeedbackDTO;
import com.cg.feedback.feedback.exception.CustomException;
import com.cg.feedback.feedback.service.FeedbackService;

@RestController
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;
	
	@GetMapping(value="/feedbacks/programId={programId}")
	public List<FeedbackDTO> getAllFeedbacksForProgram(@PathVariable("programId")String programId)
	{
		List<FeedbackDTO> feedbacks=feedbackService.viewFeedbackByProgram(programId);
		return feedbacks;
	}
	
	@GetMapping(value="/feedbacks/trainerId={trainerId}")
	public List<FeedbackDTO> getAllFeedbacksForTrainer(@PathVariable("trainerId")String trainerId)
	{
		List<FeedbackDTO> feedbacks=feedbackService.viewFeedbackByTrainer(trainerId);
		return feedbacks;
	}
	
	@GetMapping(value="/givenfeedbacks/{studentId}")
	public List<String> geivenFeedbacks(@PathVariable("studentId") String studentId)
	{
		List<String> available=feedbackService.feedbacksGivenForStudnet(studentId);
		return available;
	}
	
	@PostMapping(value = "/giveFeedback", produces = "application/json", consumes = "application/json")
    public FeedbackDTO giveFeedback(@RequestBody FeedbackDTO feedback) throws CustomException {
        if (feedback == null)
            throw new CustomException("Invalid Request");
        FeedbackDTO result = feedbackService.addFeedback(feedback);
        return result;
    }
	@GetMapping(value="/feedbacks")
	public List<FeedbackDTO> getAllFeedbacks()
	{
		List<FeedbackDTO> feedbacks=feedbackService.viewFeedbacks();
		return feedbacks;
	}
	
	
}
