package com.cg.feedback.admin_user.feignServices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.feedback.admin_user.dto.LoginCredentials;

@FeignClient(name = "login-service")
public interface LoginServiceFeign {
	
	@PostMapping(value="/addCredentials")
	public LoginCredentials newCredentials(@RequestBody LoginCredentials credentials);
	
	@DeleteMapping("/removeCredentials")
	public String deleteCredentials(@RequestBody String userId);
}
