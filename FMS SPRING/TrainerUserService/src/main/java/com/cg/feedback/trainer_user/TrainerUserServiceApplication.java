package com.cg.feedback.trainer_user;

import java.util.Collections;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
@EnableSwagger2
public class TrainerUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainerUserServiceApplication.class, args);
	}
	
    private ObjectFactory<HttpMessageConverters> messageConverters = HttpMessageConverters::new;
    
    @Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.cg"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(myApiInfo());
		
	}
private ApiInfo myApiInfo() {
	// TODO Auto-generated method stub
	ApiInfo apiInfo=new ApiInfo(
			"Trainer-user-Service", 
		     "Trainer Functionalities", 
		     "1.0", 
		     "Free to Use", 
		     new Contact("TEAM FMS","/api" ,"fmsTeam@gmail.com"),
		     "API licence",
		     "/api",
		     Collections.emptyList());
	return apiInfo;
}

    @Bean
    Encoder feignFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

    @Bean
    Decoder feignFormDecoder() {
        return new SpringDecoder(messageConverters);
    }
    @LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
		
	}
}
