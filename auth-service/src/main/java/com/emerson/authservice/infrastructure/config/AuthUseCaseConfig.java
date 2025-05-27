package com.emerson.authservice.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.emerson.authservice.application.gateways.JwtServiceGateway;
import com.emerson.authservice.application.gateways.PasswordServiceGateway;
import com.emerson.authservice.application.gateways.UserRepositoryGateway;
import com.emerson.authservice.application.usecase.auth.LoginWithUsernameAndPasswordUseCase;

@Configuration
public class AuthUseCaseConfig {
	
	@Bean
	LoginWithUsernameAndPasswordUseCase loginWithUsernameAndPasswordUseCase(
			UserRepositoryGateway userRepositoryGateway,
			PasswordServiceGateway passwordServiceGateway,
			JwtServiceGateway jwtServiceGateway) 
	{
		return new LoginWithUsernameAndPasswordUseCase(userRepositoryGateway, passwordServiceGateway, jwtServiceGateway);
	}

}
