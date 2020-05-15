package com.cg.feedback.program.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.feedback.program.dao.ProgramDAO;
import com.cg.feedback.program.dto.ProgramDTO;
import com.cg.feedback.program.exceptions.CustomException;
@Service
public class ProgramServiceImpl implements ProgramService {

	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProgramDAO programDao;
	
	@Override
	public List<ProgramDTO> getAllPrograms() {
		try {
            List<ProgramDTO> result = programDao.findAll();
            if (result == null || result.size() < 1)
                throw new CustomException("Programs Not Found");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
	}

	@Override
	public Optional<ProgramDTO> getProgramById(String programId) {
		logger.info("Fetching Program for ID "+programId);
		if(programDao.existsById(programId))
			return programDao.findById(programId);
		else
			throw new CustomException("Program with ID: "+programId+" not present.");
	}

	@Override
	public boolean delelteProgram(String programId) {
		try {
            if (programDao.existsById(programId)) {
                programDao.deleteById(programId);
                return true;
            } else
                throw new CustomException("Program Doesn't Exists");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
	}

	@Override
	public ProgramDTO addProgram(ProgramDTO program) {
		 try {
	            if (programDao.existsById(program.getProgramId()))
	                throw new CustomException("Program with given Id Already Exists");
	            ProgramDTO result = programDao.save(program);
	            if (result == null)
	                throw new CustomException("Couldn't Add Program");
	            else
	                return result;
	        } catch (Exception e) {
	            logger.error(e.getMessage(), e);
	            throw new CustomException(e.getMessage());
	        }
	}
}
