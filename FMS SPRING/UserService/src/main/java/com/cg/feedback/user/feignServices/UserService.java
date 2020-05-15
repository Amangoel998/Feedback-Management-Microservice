package com.cg.feedback.user.feignServices;

import com.cg.feedback.user.dto.LoginCredentials;
import com.cg.feedback.user.dto.LoginResponse;

public interface UserService{
	
	LoginResponse getUserInfo(LoginCredentials result);

}
