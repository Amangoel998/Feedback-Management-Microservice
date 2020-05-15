package com.cg.feedback.student.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.feedback.student.dao.StudentDAO;
import com.cg.feedback.student.dto.StudentDTO;
import com.cg.feedback.student.exception.CustomException;

@Service
public class StudnetServiceImpl implements StudentService {

	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentDAO studentDao;

	@Override
	public List<StudentDTO> getAllStudents() {
		try {
            List<StudentDTO> result = studentDao.findAll();
            if (result == null || result.size() < 1)
                throw new CustomException("Programs Not Found");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
	}

	@Override
	public StudentDTO getStudentById(String studentId) {
		logger.info("Student for ID : "+studentId);
		if(studentDao.existsById(studentId))
			return studentDao.findById(studentId).get();
		else
			throw new CustomException("Student with id :"+studentId+" not found");
			
	}

	@Override
	public boolean removeStudent(String studentId) {
		  try {
	            if (studentDao.existsById(studentId)) {
	                studentDao.deleteById(studentId);
	                return true;
	            } else
	                throw new CustomException("Student Doesn't Exists");
	        } catch (Exception e) {
	            logger.error(e.getMessage(), e);
	            throw new CustomException(e.getMessage());
	        }
	}

	@Override
	public StudentDTO addStudent(StudentDTO student) {
		logger.info("Adding student : "+student);
		if(!studentDao.existsById(student.getStudentId()))
			return studentDao.save(student);
		else
			throw new CustomException("Student with Id:"+ student.getStudentId()+"already present.");
	}

}
