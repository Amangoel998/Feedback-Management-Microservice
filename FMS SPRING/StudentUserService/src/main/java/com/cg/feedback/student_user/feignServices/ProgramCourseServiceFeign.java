package com.cg.feedback.student_user.feignServices;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.feedback.student_user.dto.ProgramCourseDTO;
import com.cg.feedback.student_user.exceptions.CustomException;

@FeignClient(name = "program-course-service")
public interface ProgramCourseServiceFeign {

	@GetMapping(value = "/coursePrograms", produces = "application/json")
    public List<ProgramCourseDTO> getCoursePrograms() throws CustomException;
	
	@PostMapping(value = "/addProgramToCourse", produces = "application/json", consumes = "application/json")
    public ProgramCourseDTO assignCoursePrograms(@RequestBody ProgramCourseDTO courseprogram)
            throws CustomException ;
	
	@DeleteMapping(value = "/removeProgramFromCourse", produces = "application/json")
    public String removeProgramInCourse(@RequestParam("programcourseId") String programcourseId)
            throws CustomException ;
	
	@DeleteMapping(value = "/removeByCourse", produces = "application/json")
    public String removeByCourse(@RequestParam("courseId") String courseId)
            throws CustomException;
	
	@DeleteMapping(value = "/removeByProgram", produces = "application/json")
    public String removeByProgram(@RequestParam("programId") String programId)
            throws CustomException;
}
