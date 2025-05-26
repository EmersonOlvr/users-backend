package com.emerson.authservice.service.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.emerson.authservice.domain.user.User;
import com.emerson.authservice.service.auth.dto.TokenDto;

@Service
public class JwtService {
	
	private final JwtEncoder jwtEncoder;
	
	public JwtService(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}

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
