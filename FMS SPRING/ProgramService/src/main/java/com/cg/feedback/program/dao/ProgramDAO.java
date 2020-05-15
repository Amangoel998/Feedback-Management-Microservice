package com.cg.feedback.program.dao;
import com.cg.feedback.program.dto.ProgramDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProgramDAO extends JpaRepository<ProgramDTO,String>{
}
