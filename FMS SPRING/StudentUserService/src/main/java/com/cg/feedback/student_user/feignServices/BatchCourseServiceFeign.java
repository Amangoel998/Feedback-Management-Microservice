package com.cg.feedback.student_user.feignServices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.feedback.student_user.dto.BatchCourseDTO;
import com.cg.feedback.student_user.exceptions.CustomException;

@FeignClient(name = "batch-course-service")
public interface BatchCourseServiceFeign {

	@GetMapping(value = "/batches", produces = "application/json")
    public List<BatchCourseDTO> getBatches() throws CustomException;
	
	@PostMapping(value = "/addBatch", produces = "application/json", consumes = "application/json")
    public BatchCourseDTO addBatch(@RequestBody BatchCourseDTO batch) throws CustomException;
	
	@DeleteMapping(value = "/removeBatch", produces = "application/json")
    public String removeBatch(@RequestParam("batch") String batch)
            throws CustomException;
	
	@DeleteMapping(value = "/removeByCourse", produces = "application/json")
    public String removeByCourse(@RequestParam("courseId") String courseId)
            throws CustomException;
	
	@GetMapping(value = "/getBatch", produces = "application/json")
    public BatchCourseDTO getBatch(@RequestParam("batch") String batch) throws CustomException;
}
