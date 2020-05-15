package com.cg.feedback.batch_course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.feedback.batch_course.dao.BatchCourseDAO;
import com.cg.feedback.batch_course.dto.BatchCourseDTO;
import com.cg.feedback.batch_course.exceptions.CustomException;


@Service
public class BatchCourseServiceImpl implements BatchCourseService {

	@Autowired
    BatchCourseDAO batDao;
    
	@Override
	public void removeByCourse(String courseId) {
		try {
			batDao.removeCourse(courseId);
		} catch (Exception e) {
            throw new CustomException(e.getMessage());
		}
	}

	@Override
	public BatchCourseDTO addBatch(BatchCourseDTO batch) throws CustomException {
		try {
            BatchCourseDTO result = batDao.save(batch);
            if (result == null)
                throw new CustomException("Couldn't Add Batch");
            else
                return result;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
	}

	@Override
	public boolean removeBatch(String batch) throws CustomException {
		try {
            if (batDao.existsById(batch)) {
                batDao.deleteById(batch);
                return true;
            } else
                throw new CustomException("Batch Doesn't Exists");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
	}

	@Override
	public List<BatchCourseDTO> getBatches() throws CustomException {
		try {
            List<BatchCourseDTO> result = batDao.findAll();
            if (result == null || result.size() < 1)
                throw new CustomException("Programs Not Found");
            return result;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
	}

	@Override
	public BatchCourseDTO getBatch(String batch) throws CustomException {
		try {
            if (batDao.existsById(batch)) {
                return batDao.findById(batch).get();
            } else
                throw new CustomException("Batch Doesn't Exists");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
	}

}
