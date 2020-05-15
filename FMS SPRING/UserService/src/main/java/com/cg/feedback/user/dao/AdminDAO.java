package com.cg.feedback.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.feedback.user.dto.AdminDTO;

public interface AdminDAO extends JpaRepository<AdminDTO, String>{
	
}
