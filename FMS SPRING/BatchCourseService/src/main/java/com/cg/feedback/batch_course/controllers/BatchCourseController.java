package com.cg.feedback.batch_course.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.feedback.batch_course.dto.BatchCourseDTO;
import com.cg.feedback.batch_course.exceptions.CustomException;
import com.cg.feedback.batch_course.services.BatchCourseService;


@RestController
public class BatchCourseController {
	
	@Autowired
	BatchCourseService batchCourseService;
	
	@GetMapping(value = "/batches", produces = "application/json")
    public List<BatchCourseDTO> getBatches() throws CustomException {
        List<BatchCourseDTO> result = batchCourseService.getBatches();
        return result;
    }
	
	@GetMapping(value = "/getBatch", produces = "application/json")
    public BatchCourseDTO getBatch(@RequestParam("batch") String batch) throws CustomException {
		if (batch == null)
            throw new CustomException("Invalid Request");
        return batchCourseService.getBatch(batch);
    }
	
	@PostMapping(value = "/addBatch", produces = "application/json", consumes = "application/json")
    public BatchCourseDTO addBatch(@RequestBody BatchCourseDTO batch) throws CustomException {
        if (batch == null)
            throw new CustomException("Invalid Request");
        BatchCourseDTO result = batchCourseService.addBatch(batch);
        return result;
    }
	
	@DeleteMapping(value = "/removeBatch", produces = "application/json")
    public String removeBatch(@RequestParam("batch") String batch)
            throws CustomException {
        if (batch == null || batch.isEmpty())
            throw new CustomException("Invalid Request");
        batchCourseService.removeBatch(batch);
        return "Batch Deleted";
    }
	
	@DeleteMapping(value = "/removeByCourse", produces = "application/json")
    public String removeByCourse(@RequestParam("courseId") String courseId)
            throws CustomException {
		if(courseId == null || courseId.isEmpty())
			throw new CustomException("Invalid Request");
		batchCourseService.removeByCourse(courseId);
        return "Batch Courses Removed";
    }
	
}
