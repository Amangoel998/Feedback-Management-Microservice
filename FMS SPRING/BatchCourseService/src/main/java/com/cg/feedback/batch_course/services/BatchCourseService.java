package com.cg.feedback.batch_course.services;

import java.util.List;

import com.cg.feedback.batch_course.dto.BatchCourseDTO;
import com.cg.feedback.batch_course.exceptions.CustomException;

public interface BatchCourseService {
	BatchCourseDTO addBatch(BatchCourseDTO batches) throws CustomException;
	BatchCourseDTO getBatch(String batch) throws CustomException;
	boolean removeBatch(String batch) throws CustomException;
	List<BatchCourseDTO> getBatches() throws CustomException;
	void removeByCourse(String courseId);
	
}
