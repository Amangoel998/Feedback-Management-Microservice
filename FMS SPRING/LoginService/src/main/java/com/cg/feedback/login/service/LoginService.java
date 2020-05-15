package com.cg.feedback.login.service;

import com.cg.feedback.login.dto.LoginCredentials;

public interface LoginService {
	 LoginCredentials AuthenticateUser(String userId, String password);
	 LoginCredentials addCredentials(LoginCredentials credentials);
	 boolean deleteCredentials(String userId);
	

}
