package com.cg.feedback.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;

import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

    private ObjectFactory<HttpMessageConverters> messageConverters = HttpMessageConverters::new;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	
    @Bean
    Encoder feignFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

    @Bean
    Decoder feignFormDecoder() {
        return new SpringDecoder(messageConverters);
    }
	
}
