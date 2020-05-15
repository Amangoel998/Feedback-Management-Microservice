package com.cg.feedback.trainer_user.feignServices;

import java.util.List;

import com.cg.feedback.trainer_user.dto.*;
import com.cg.feedback.trainer_user.exceptions.CustomException;
import com.cg.feedback.trainer_user.id.ProgramCourseId;
import com.cg.feedback.trainer_user.id.ProgramTrainerId;

public interface TrainerUserService{

	ReportDTO showFeedbackReport(String trainerId) throws CustomException;

	List<TrainerDefaulters> showDefaulters(String trainerId) throws CustomException;

	List<TrainerDefaulters> viewDefaultersByTrainer(String trainer) throws CustomException;

	List<ProgramDefaulters> viewDefaultersByProgram(String program) throws CustomException;

}
