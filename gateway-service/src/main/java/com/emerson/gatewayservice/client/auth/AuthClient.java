package com.emerson.gatewayservice.client.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.emerson.gatewayservice.client.auth.dto.LoginRequest;
import com.emerson.gatewayservice.client.auth.dto.TokenDto;

@FeignClient(
	name = "auth-service-auth", 
	url = "${auth-service.url}/v1/auth"
)
public interface AuthClient {
	
	@PostMapping("/login")
	TokenDto login(@RequestBody LoginRequest request);
	
}
