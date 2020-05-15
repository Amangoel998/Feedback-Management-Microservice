package com.cg.feedback.program.services;

import java.util.List;
import java.util.Optional;

import com.cg.feedback.program.dto.ProgramDTO;

public interface ProgramService {

	List<ProgramDTO> getAllPrograms();
	Optional<ProgramDTO> getProgramById(String programId);
	boolean delelteProgram(String programId);
	ProgramDTO addProgram(ProgramDTO program);
}
