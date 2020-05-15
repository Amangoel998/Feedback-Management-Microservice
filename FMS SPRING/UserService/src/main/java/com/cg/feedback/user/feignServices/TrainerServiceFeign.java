package com.cg.feedback.user.feignServices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.cg.feedback.user.dto.TrainerDTO;

@FeignClient(name = "trainer-service")
public interface TrainerServiceFeign {
	@GetMapping(value="/trainers/{trainerId}")
	public TrainerDTO gettrainerById(@PathVariable("trainerId") String trainerId);
}
