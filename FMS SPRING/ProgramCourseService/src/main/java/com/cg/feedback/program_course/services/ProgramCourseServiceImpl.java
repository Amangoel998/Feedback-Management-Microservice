package com.cg.feedback.program_course.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.feedback.program_course.dao.ProgramCourseDAO;
import com.cg.feedback.program_course.dto.ProgramCourseDTO;
import com.cg.feedback.program_course.exceptions.CustomException;
import com.cg.feedback.program_course.id.ProgramCourseId;
@Service
public class ProgramCourseServiceImpl implements ProgramCourseService {

	@Autowired
	ProgramCourseDAO prgcrsDao;
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void removeByCourse(String courseId) {
		try {
			prgcrsDao.removeCourse(courseId);
		} catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
		}
	}

	@Override
	public void removeByProgram(String programId) {
		try {
			prgcrsDao.removeProgram(programId);
		} catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
		}
	}

	@Override
	public ProgramCourseDTO addCoursePrograms(ProgramCourseDTO courseprogram) throws CustomException {
		try {
            if (prgcrsDao.existsById(courseprogram.getprogramCourseId()))
                throw new CustomException("Program in Course Already Exists");
            ProgramCourseDTO result = prgcrsDao.save(courseprogram);
            if (result == null)
                throw new CustomException("Couldn't Add Course Program");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
	}

	@Override
	public boolean removeProgramCourse(ProgramCourseId programcourseId) throws CustomException {
		try {
            if (prgcrsDao.existsById(programcourseId)) {
                prgcrsDao.deleteById(programcourseId);
                return true;
            } else
                throw new CustomException("Program in Course Doesn't Exists");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
	}

	@Override
	public List<ProgramCourseDTO> getCoursePrograms() throws CustomException {
		try {
            List<ProgramCourseDTO> result = prgcrsDao.findAll();
            if (result == null || result.size() < 1)
                throw new CustomException("Programs Not Found");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
	}


}
