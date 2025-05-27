package com.emerson.authservice.infrastructure.service.auth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.authservice.application.dto.TokenDto;
import com.emerson.authservice.application.usecase.auth.LoginWithUsernameAndPasswordUseCase;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceFacade {
	
	private final LoginWithUsernameAndPasswordUseCase loginWithUsernameAndPasswordUseCase;

	@Transactional
	public TokenDto loginWithUsernameAndPassword(String username, String password) {
		return this.loginWithUsernameAndPasswordUseCase.execute(username, password);
	}

}
