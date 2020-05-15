package com.cg.feedback.student_user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.cg.feedback.student_user.dto.*;
import com.cg.feedback.student_user.exceptions.CustomException;
import com.cg.feedback.student_user.feignServices.FeedbackServiceFeign;
import com.cg.feedback.student_user.feignServices.StudentUserService;

import io.swagger.annotations.Api;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/api/student")
@Api("Student-user-Service")
public class StudentUserContoller {
	
    private StudentUserService stdService;
    private FeedbackServiceFeign feedbackService;

    @Autowired
    public StudentUserContoller(StudentUserService stdService, FeedbackServiceFeign feedbackService) throws CustomException {
        this.stdService = stdService;
        this.feedbackService = feedbackService;
    }

    // ------------------Student Methods--------------------
    @GetMapping(value = "/programs", produces = "application/json")
    public ResponseEntity<List<AvailableProgramsDTO>> availablePrograms(@RequestParam("studentId") String studentId)
            throws CustomException {
        if (studentId == null || studentId.isEmpty())
            throw new CustomException("Invalid Request");
        List<AvailableProgramsDTO> result = stdService.getAvailablePrograms(studentId);
        return new ResponseEntity<List<AvailableProgramsDTO>>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/giveFeedback", produces = "application/json", consumes = "application/json")
    public ResponseEntity<FeedbackDTO> giveFeedback(@RequestBody FeedbackDTO feedback) throws CustomException {
        if (feedback == null)
            throw new CustomException("Invalid Request");
        FeedbackDTO result = feedbackService.giveFeedback(feedback);
        return new ResponseEntity<FeedbackDTO>(result, HttpStatus.OK);
    }
}