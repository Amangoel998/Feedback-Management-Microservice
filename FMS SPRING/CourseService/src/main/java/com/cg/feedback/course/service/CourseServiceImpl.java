package com.cg.feedback.course.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.feedback.course.dao.CourseDAO;
import com.cg.feedback.course.dto.CourseDTO;
import com.cg.feedback.course.exceptions.CustomException;

@Service
public class CourseServiceImpl implements CourseService {
	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseDAO courseDao;

	@Override
	public List<CourseDTO> getAllCourses() {
		try {
            List<CourseDTO> result = courseDao.findAll();
            if (result == null || result.size() < 1)
                throw new CustomException("Programs Not Found");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
	}

	@Override
	public Optional<CourseDTO> getCourseById(String courseId) {
		if(courseDao.existsById(courseId)) {

			logger.info("Got course with Id:"+courseId);
			return courseDao.findById(courseId);
		}
		else {
			throw new CustomException("Course with id :"+courseId+" not found");
		}
			
	}

	@Override
	public boolean removeCourse(String courseId) {
		 try {
	            if (courseDao.existsById(courseId)) {
	                courseDao.deleteById(courseId);
	                return true;
	            } else
	                throw new CustomException("Student Doesn't Exists");
	        } catch (Exception e) {
	            logger.error(e.getMessage(), e);
	            throw new CustomException(e.getMessage());
	        }
	}

	@Override
	public CourseDTO addCourse(CourseDTO course) {
		 try {
	            if (courseDao.existsById(course.getCourseId()) || course.getCourseName().isEmpty())
	                throw new CustomException("Course with given Id Already Exists");
	            CourseDTO result = courseDao.save(course);
	            if (result == null)
	                throw new CustomException("Couldn;t Add the Course");
	            return result;
	        } catch (Exception e) {
	            logger.error(e.getMessage(), e);
	            throw new CustomException(e.getMessage(), e);
	        }
	}

}
