package com.cg.feedback.feedback.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.feedback.feedback.dto.FeedbackDTO;

public interface FeedbackDAO extends JpaRepository<FeedbackDTO,Integer> {
	List<FeedbackDTO> findAllByProgramId(String programId);
	List<FeedbackDTO> findAllByTrainerId(String trainerId);
	public List<FeedbackDTO> findByStudentIdAndTrainerIdAndProgramId(String studentId,String trainerId,String programId);
	@Query("select feedback.programId from FeedbackDTO feedback where feedback.studentId = :studentId")
	public List<String> feedbacksGiven(@Param("studentId")String studentId);

}
