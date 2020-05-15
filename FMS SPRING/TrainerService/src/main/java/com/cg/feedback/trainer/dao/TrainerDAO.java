package com.cg.feedback.trainer.dao;

import com.cg.feedback.trainer.dto.TrainerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainerDAO extends JpaRepository<TrainerDTO, String> {
	
}