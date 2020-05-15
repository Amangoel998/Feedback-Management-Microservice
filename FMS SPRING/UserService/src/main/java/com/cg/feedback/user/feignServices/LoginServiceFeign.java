package com.cg.feedback.user.feignServices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.feedback.user.dto.LoginCredentials;

@FeignClient(name = "login-service")
public interface LoginServiceFeign {
	
	@PostMapping(value="/login")
	public LoginCredentials login(@RequestBody LoginCredentials credentials);
}
