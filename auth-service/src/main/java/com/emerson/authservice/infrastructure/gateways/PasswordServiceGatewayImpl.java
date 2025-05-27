package com.emerson.authservice.infrastructure.gateways;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emerson.authservice.application.gateways.PasswordServiceGateway;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PasswordServiceGatewayImpl implements PasswordServiceGateway {
	
	private final PasswordEncoder passwordEncoder;

	@Override
	public String encode(CharSequence rawPassword) {
		return this.passwordEncoder.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return this.passwordEncoder.matches(rawPassword, encodedPassword);
	}
	
}
