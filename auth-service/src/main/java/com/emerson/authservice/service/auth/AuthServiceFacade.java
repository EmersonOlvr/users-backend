package com.emerson.authservice.service.auth;

import org.springframework.stereotype.Service;

import com.emerson.authservice.service.auth.dto.TokenDto;

@Service
public class AuthServiceFacade {
	
	private final LoginWithUsernameAndPasswordUseCase loginWithUsernameAndPasswordUseCase;

	public AuthServiceFacade(LoginWithUsernameAndPasswordUseCase loginWithUsernameAndPasswordUseCase) {
		this.loginWithUsernameAndPasswordUseCase = loginWithUsernameAndPasswordUseCase;
	}
	
	public TokenDto loginWithUsernameAndPassword(String username, String password) {
		return this.loginWithUsernameAndPasswordUseCase.execute(username, password);
	}

}
