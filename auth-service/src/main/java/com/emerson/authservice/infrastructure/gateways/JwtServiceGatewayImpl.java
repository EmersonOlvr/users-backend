package com.emerson.authservice.infrastructure.gateways;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.emerson.authservice.application.dto.TokenDto;
import com.emerson.authservice.application.gateways.JwtServiceGateway;
import com.emerson.authservice.domain.model.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JwtServiceGatewayImpl implements JwtServiceGateway {
	
	private final JwtEncoder jwtEncoder;
	
	@Override
	public TokenDto generateToken(User user) {
		Instant now = Instant.now();
		Instant expiration = this.genExpirationDate();

		JwtClaimsSet claims = JwtClaimsSet.builder()
										.issuer("auth-service-api")
										.subject(user.getId())
										.issuedAt(now)
										.expiresAt(expiration)
										.build();

		String jwtValue = this.jwtEncoder.encode(JwtEncoderParameters.from(claims))
										.getTokenValue();
		
		return new TokenDto(jwtValue, expiration);
	}

	private Instant genExpirationDate() {
		return LocalDateTime.now()
							.plusHours(1)
							.toInstant(ZoneOffset.of("-03:00"));
	}
	
}
