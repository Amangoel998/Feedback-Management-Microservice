package com.cg.feedback.program.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.feedback.program.dto.ProgramDTO;
import com.cg.feedback.program.services.ProgramService;

@RestController
public class ProgramServiceController {
	@Autowired
	ProgramService programService;
	

	@GetMapping(value="/programs")
	public List<ProgramDTO> getAllprograms()
	{
		List<ProgramDTO> programs=programService.getAllPrograms();
		return programs;
	}
	
	
	@GetMapping(value="/programs/{programId}")
	public ProgramDTO getprogramById(@PathVariable("programId") String programId)
	{
		ProgramDTO program =programService.getProgramById(programId).get();
		return program;
	}
	
	
	@DeleteMapping(value="/removeProgram")
	public String delteprogramById(@RequestBody String programId)
	{
		programService.delelteProgram(programId);
		return "Succesfully Deleted";
	}
	
	
	@PostMapping(value="/addProgram")
	public ProgramDTO  addprogram(@RequestBody ProgramDTO program)
	{
		ProgramDTO res =programService.addProgram(program);
		return res;
		
	}

}
