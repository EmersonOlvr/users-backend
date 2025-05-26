package com.emerson.gatewayservice.service.auth;

import org.springframework.stereotype.Service;

import com.emerson.gatewayservice.client.auth.AuthClient;
import com.emerson.gatewayservice.client.auth.dto.LoginRequest;
import com.emerson.gatewayservice.client.auth.dto.TokenDto;

@Service
public class AuthFacade {
	
	private final AuthClient authClient;

	public AuthFacade(AuthClient authClient) {
		this.authClient = authClient;
	}
	
	public TokenDto login(LoginRequest loginRequest) {
		return this.authClient.login(loginRequest);
	}
	
}
