package com.cg.feedback.admin_user.feignServices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.feedback.admin_user.dto.ProgramDTO;

@FeignClient(name = "program-service")
public interface ProgramServiceFeign {
	@GetMapping(value="/programs")
	public List<ProgramDTO> getAllprograms();
	
	@GetMapping(value="/programs/{programId}")
	public ProgramDTO getprogramById(@PathVariable("programId") String programId);
	
	@DeleteMapping(value="/removeProgram")
	public String delteprogramById(@RequestBody String programId);
	
	@PostMapping(value="/addProgram")
	public ProgramDTO  addprogram(@RequestBody ProgramDTO program);
}
