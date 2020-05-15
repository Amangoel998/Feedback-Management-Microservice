package com.cg.feedback.student_user.feignServices;

import java.util.List;

import com.cg.feedback.student_user.dto.*;
import com.cg.feedback.student_user.exceptions.CustomException;
import com.cg.feedback.student_user.id.ProgramCourseId;
import com.cg.feedback.student_user.id.ProgramTrainerId;

public interface StudentUserService{

	List<AvailableProgramsDTO> getAvailablePrograms(String studentId);
	
}
