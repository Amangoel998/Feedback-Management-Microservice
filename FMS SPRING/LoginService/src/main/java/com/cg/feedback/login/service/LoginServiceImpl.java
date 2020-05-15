package com.cg.feedback.login.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.feedback.login.dao.LoginDAO;
import com.cg.feedback.login.dto.LoginCredentials;
import com.cg.feedback.login.exceptions.CustomException;
@Service
public class LoginServiceImpl implements LoginService {

	Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	LoginDAO loginDao;

	@Override
	public LoginCredentials AuthenticateUser(String userId, String pass) {
		LoginCredentials user=loginDao.validateUser(userId,pass);
		if(user!=null)
			return user;
		else
			throw new CustomException("Credentials Invalid");
			
	}

	@Override
	public LoginCredentials addCredentials(LoginCredentials credentials) {
		if(!loginDao.existsById(credentials.getId())) {	
			log.info("Adding credentials"+credentials.getId());
			return loginDao.save(credentials);
		}
		else
			throw new CustomException("User Already Exists");
	}

	@Override
	public boolean deleteCredentials(String userId) {
		if(loginDao.existsById(userId)) {	
			log.info("Adding credentials"+userId);
			loginDao.deleteById(userId);
			return true;
		}
		else
			throw new CustomException("user Does not Exist");

	}

}
