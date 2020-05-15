package com.cg.feedback.feedback.service;

import java.util.List;
import com.cg.feedback.feedback.dto.FeedbackDTO;

public interface FeedbackService {

	List<FeedbackDTO> viewFeedbackByProgram(String programId) ;
	 List<FeedbackDTO> viewFeedbackByTrainer(String trainerId);
	 FeedbackDTO addFeedback(FeedbackDTO feedback);
	 List<String> feedbacksGivenForStudnet(String studentId);
	List<FeedbackDTO> viewFeedbacks();
}
