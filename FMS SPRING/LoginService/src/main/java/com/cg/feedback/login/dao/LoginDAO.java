package com.cg.feedback.login.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.feedback.login.dto.LoginCredentials;
@Repository
public interface LoginDAO extends JpaRepository<LoginCredentials, String> {
	@Query("SELECT p from LoginCredentials p  where p.id= :id AND p.password= :pass")
    LoginCredentials validateUser(@Param("id")String id,@Param("pass") String pass);
}