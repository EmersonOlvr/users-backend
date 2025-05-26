package com.emerson.authservice.service.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emerson.authservice.domain.user.User;
import com.emerson.authservice.exception.http.ForbiddenException;
import com.emerson.authservice.exception.http.InvalidCredentialsException;
import com.emerson.authservice.repository.user.UserRepository;
import com.emerson.authservice.service.auth.dto.TokenDto;
import com.emerson.authservice.service.jwt.JwtService;

@Service
public class LoginWithUsernameAndPasswordUseCase {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public LoginWithUsernameAndPasswordUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}
	
	public TokenDto execute(String username, String password) {
		User user = this.userRepository.findByUsername(username)
										.orElseThrow(InvalidCredentialsException::new);
		
		if (!user.isEnabled())
			throw new ForbiddenException("Esta conta foi desativada.");
		
		if (!this.passwordEncoder.matches(password, user.getPassword()))
			throw new InvalidCredentialsException();
		
		return this.jwtService.generateToken(user);
	}

}
