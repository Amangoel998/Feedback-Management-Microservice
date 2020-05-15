package com.cg.feedback.program_course.controllers;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.feedback.program_course.dto.ProgramCourseDTO;
import com.cg.feedback.program_course.exceptions.CustomException;
import com.cg.feedback.program_course.id.ProgramCourseId;
import com.cg.feedback.program_course.services.ProgramCourseService;


@RestController
public class ProgramCourseController {
	
	@Autowired
	ProgramCourseService programCourseService;
	
	@GetMapping(value = "/coursePrograms", produces = "application/json")
    public List<ProgramCourseDTO> getCoursePrograms() throws CustomException {

        List<ProgramCourseDTO> result = programCourseService.getCoursePrograms();
        return result;
    }
	
	@PostMapping(value = "/addProgramToCourse", produces = "application/json", consumes = "application/json")
    public ProgramCourseDTO assignCoursePrograms(@RequestBody ProgramCourseDTO courseprogram)
            throws CustomException {
        if (courseprogram == null)
            throw new CustomException("Invalid Request");
        ProgramCourseDTO result = programCourseService.addCoursePrograms(courseprogram);
        return result;
    }
	
	@DeleteMapping(value = "/removeProgramFromCourse", produces = "application/json")
    public String removeProgramInCourse(@RequestParam("programcourseId") String programcourseId)
            throws CustomException {
        if (programcourseId == null || programcourseId.isEmpty())
            throw new CustomException("Invalid Request");
        StringTokenizer stkr = new StringTokenizer(programcourseId, "-");
        programCourseService.removeProgramCourse(new ProgramCourseId(stkr.nextToken(), stkr.nextToken()));
        return "Program removed from Course";
    }
	
	@DeleteMapping(value = "/removeByCourse", produces = "application/json")
    public String removeByCourse(@RequestParam("courseId") String courseId)
            throws CustomException {
		if(courseId == null || courseId.isEmpty())
			throw new CustomException("Invalid Request");
		programCourseService.removeByCourse(courseId);
        return "Course Programs Removed";
    }
	
	@DeleteMapping(value = "/removeByProgram", produces = "application/json")
    public String removeByProgram(@RequestParam("programId") String programId)
            throws CustomException {
		if(programId == null || programId.isEmpty())
			throw new CustomException("Invalid Request");
		programCourseService.removeByProgram(programId);
        return "Course Programs Removed";
    }
}
