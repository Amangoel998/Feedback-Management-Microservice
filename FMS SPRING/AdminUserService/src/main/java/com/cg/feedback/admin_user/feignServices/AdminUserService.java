package com.cg.feedback.admin_user.feignServices;

import java.util.List;

import com.cg.feedback.admin_user.dto.*;
import com.cg.feedback.admin_user.exceptions.CustomException;

public interface AdminUserService{
	List<ProgramDefaulters> viewDefaultersByProgram(String program) throws CustomException;
	List<TrainerDefaulters> viewDefaultersByTrainer(String trainer) throws CustomException;	
}
