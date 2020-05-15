package com.cg.feedback.feedback.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.feedback.feedback.dao.FeedbackDAO;
import com.cg.feedback.feedback.dto.FeedbackDTO;
import com.cg.feedback.feedback.exception.CustomException;


@Service
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	FeedbackDAO feedbackDao;

	Logger log=LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<FeedbackDTO> viewFeedbackByProgram(String programId) {

		log.info("feedbacks for programID:"+programId);
		return feedbackDao.findAllByProgramId(programId);
	}

	@Override
	public List<FeedbackDTO> viewFeedbackByTrainer(String trainerId) {
		log.info("feedbacks fortrainerId:"+trainerId);
		return feedbackDao.findAllByTrainerId(trainerId);
	}



	@Override
	public FeedbackDTO addFeedback(FeedbackDTO feedback) {
		
		if(feedbackDao.findByStudentIdAndTrainerIdAndProgramId(feedback.getStudentId(), feedback.getTrainerId(),feedback.getProgramId()).size()==0)
		{	log.info("New feedback : "+feedback);
			FeedbackDTO newFeedback=feedbackDao.save(feedback);
			return newFeedback;
		}
		else
			throw new CustomException("Feedback already exists in DataBase!");
	}

	@Override
	public List<String> feedbacksGivenForStudnet(String studentId) {
		log.info("feedbacks given for studentID :"+studentId);
		return feedbackDao.feedbacksGiven(studentId);
	}

	@Override
	public List<FeedbackDTO> viewFeedbacks() {
		log.info("All feedbacks given");
		return feedbackDao.findAll();
	}

	

}
