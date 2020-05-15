package com.cg.feedback.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.feedback.login.dto.LoginCredentials;
import com.cg.feedback.login.exceptions.CustomException;
import com.cg.feedback.login.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	LoginService loginService;
	
	
	@PostMapping(value="/login")
	public LoginCredentials login(@RequestBody LoginCredentials credentials) throws CustomException{
		return loginService.AuthenticateUser(credentials.getId(), credentials.getPassword());
	}
	
	@PostMapping(value="/addCredentials")
	public LoginCredentials newCredentials(@RequestBody LoginCredentials credentials) throws CustomException{
		LoginCredentials cred=loginService.addCredentials(credentials);
		return cred;
	}
	
	@DeleteMapping("/removeCredentials")
	public String deleteCredentials(@RequestBody String userId) throws CustomException{
		loginService.deleteCredentials(userId);
		return "Successfully Deleted";
	}
}
