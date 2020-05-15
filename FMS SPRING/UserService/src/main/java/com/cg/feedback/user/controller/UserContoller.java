package com.cg.feedback.user.controller;

import com.cg.feedback.user.dto.LoginCredentials;
import com.cg.feedback.user.dto.LoginResponse;
import com.cg.feedback.user.exceptions.CustomException;
import com.cg.feedback.user.feignServices.LoginServiceFeign;
import com.cg.feedback.user.feignServices.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserContoller {
	
	private LoginServiceFeign loginService;
	private UserService userService;
    
    @Autowired
    public UserContoller(LoginServiceFeign loginService, UserService userService) throws CustomException {
        this.loginService = loginService;
        this.userService = userService;
    }

 // ----------------User Login---------------------------
    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginCredentials credentials) throws CustomException {
        if (credentials.getId() == null || credentials.getPassword() == null)
            throw new CustomException("Invalid Request");
        LoginCredentials result = loginService.login(credentials);
        if (result == null)
            throw new CustomException("Validation Failed");
        LoginResponse res = userService.getUserInfo(result);
        if(res==null)
            throw new CustomException("The user info doesn't Exists");
        return new ResponseEntity<LoginResponse>(res, HttpStatus.OK);

    }
    
    
}