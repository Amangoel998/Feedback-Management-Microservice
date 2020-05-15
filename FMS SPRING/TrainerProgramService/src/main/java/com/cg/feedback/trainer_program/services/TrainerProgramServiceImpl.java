package com.cg.feedback.trainer_program.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.feedback.trainer_program.dao.TrainerProgramDAO;
import com.cg.feedback.trainer_program.dto.TrainerProgramDTO;
import com.cg.feedback.trainer_program.exceptions.CustomException;
import com.cg.feedback.trainer_program.id.TrainerProgramId;
@Service
public class TrainerProgramServiceImpl implements TrainerProgramService {

	@Autowired
	TrainerProgramDAO trnprgDao;
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public TrainerProgramDTO addTrainerPrograms(TrainerProgramDTO trainerprogram) {
		try {
            if (trnprgDao.existsById(trainerprogram.getTrainerProgramId()))
                throw new CustomException("Trainer For Program Already Exists");
            TrainerProgramDTO result = trnprgDao.save(trainerprogram);
            if (result == null)
                throw new CustomException("Couldn't Add Trainer Program");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
	}

	@Override
    public boolean removeTrainerProgram(TrainerProgramId trainerprogramId) throws CustomException {
        try {
            if (trnprgDao.existsById(trainerprogramId)) {
                trnprgDao.deleteById(trainerprogramId);
                return true;
            } else
                throw new CustomException("Trainer in Program Doesn't Exists");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

	@Override
	public List<TrainerProgramDTO> getTrainerPrograms() {
		try {
            List<TrainerProgramDTO> result = trnprgDao.findAll();
            if (result == null || result.size() < 1)
                throw new CustomException("Trainer - Programs Not Found");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
	}

	@Override
	public void removeByTrainer(String trainerId) {
		try {
			trnprgDao.removeTrainer(trainerId);
		} catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
		}
	}

	@Override
	public void removeByProgram(String programId) {
		try {
			trnprgDao.removeProgram(programId);
		} catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
		}
	}

	@Override
	public TrainerProgramDTO findTrainer(String id, String batch) {
		try {
            TrainerProgramId result = trnprgDao.findTrainer(id, batch);
            return new TrainerProgramDTO(result);
		} catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
		}
	}


}
