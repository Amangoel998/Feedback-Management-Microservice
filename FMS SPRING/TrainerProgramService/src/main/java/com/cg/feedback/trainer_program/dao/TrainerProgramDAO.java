package com.cg.feedback.trainer_program.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.feedback.trainer_program.dto.TrainerProgramDTO;
import com.cg.feedback.trainer_program.id.TrainerProgramId;


@Repository
public interface TrainerProgramDAO extends JpaRepository<TrainerProgramDTO, TrainerProgramId> {
	@Modifying
    void removeTrainer(String id) throws Exception;
    @Modifying
    void removeProgram(String id) throws Exception;
    @Query(value ="SELECT trnP.trainerProgramId from TrainerProgramDTO trnP where trnP.trainerProgramId.programId=:id AND trnP.trainerProgramId.batch=:batch")
    TrainerProgramId findTrainer(@Param("id") String id,@Param("batch") String batch) throws Exception;
}