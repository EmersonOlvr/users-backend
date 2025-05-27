package com.emerson.authservice.application.gateways;

import com.emerson.authservice.application.dto.TokenDto;
import com.emerson.authservice.domain.model.User;

public interface JwtServiceGateway {

	TokenDto generateToken(User user);
	
}
