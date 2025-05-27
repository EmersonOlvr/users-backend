package com.emerson.authservice.application.usecase.auth;

import com.emerson.authservice.application.dto.TokenDto;
import com.emerson.authservice.application.gateways.JwtServiceGateway;
import com.emerson.authservice.application.gateways.PasswordServiceGateway;
import com.emerson.authservice.application.gateways.UserRepositoryGateway;
import com.emerson.authservice.domain.exception.ForbiddenException;
import com.emerson.authservice.domain.exception.InvalidCredentialsException;
import com.emerson.authservice.domain.model.User;

public class LoginWithUsernameAndPasswordUseCase {
	
	private final UserRepositoryGateway userRepositoryGateway;
	private final PasswordServiceGateway passwordServiceGateway;
	private final JwtServiceGateway jwtServiceGateway;

	public LoginWithUsernameAndPasswordUseCase(
			UserRepositoryGateway userRepositoryGateway, 
			PasswordServiceGateway passwordServiceGateway, 
			JwtServiceGateway jwtServiceGateway) 
	{
		this.userRepositoryGateway = userRepositoryGateway;
		this.passwordServiceGateway = passwordServiceGateway;
		this.jwtServiceGateway = jwtServiceGateway;
	}
	
	public TokenDto execute(String username, String password) {
		User user = this.userRepositoryGateway.findByUsername(username)
										.orElseThrow(InvalidCredentialsException::new);
		
		if (!Boolean.TRUE.equals(user.getActive()))
			throw new ForbiddenException("Esta conta foi desativada.");
		
		if (!this.passwordServiceGateway.matches(password, user.getPassword()))
			throw new InvalidCredentialsException();
		
		return this.jwtServiceGateway.generateToken(user);
	}

}
