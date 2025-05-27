package com.emerson.authservice.application.gateways;

public interface PasswordServiceGateway {
	
	String encode(CharSequence rawPassword);
	boolean matches(CharSequence rawPassword, String encodedPassword);

}
