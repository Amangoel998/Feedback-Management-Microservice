package com.cg.feedback.trainer_user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.feedback.trainer_user.dto.*;
import com.cg.feedback.trainer_user.exceptions.CustomException;
import com.cg.feedback.trainer_user.feignServices.TrainerUserService;

import io.swagger.annotations.Api;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/api/trainer")
@Api("Trainer-user-Service")
public class TrainerUserContoller {

    private TrainerUserService trainerUserService;

    @Autowired
    public TrainerUserContoller(TrainerUserService trainerUserService) throws CustomException {
        this.trainerUserService = trainerUserService;
    }

    // ------------------Trainer Methods--------------------

    @GetMapping(value = "/showFeedbackReport", produces = "application/json")
    public ResponseEntity<ReportDTO> showFeedbackReport(@RequestParam("trainerId") final String trainerId)
            throws CustomException {
        if (trainerId == null || trainerId.isEmpty())
            throw new CustomException("Invalid Request");
        final ReportDTO result = trainerUserService.showFeedbackReport(trainerId);
        return new ResponseEntity<ReportDTO>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/showDefaulters", produces = "application/json")
    public ResponseEntity<List<TrainerDefaulters>> showDefaulters(@RequestParam("trainerId") final String trainerId)
            throws CustomException {
        if (trainerId == null || trainerId.isEmpty())
            throw new CustomException("Invalid Request");
        final List<TrainerDefaulters> result = trainerUserService.showDefaulters(trainerId);
        return new ResponseEntity<List<TrainerDefaulters>>(result, HttpStatus.OK);
    }
}