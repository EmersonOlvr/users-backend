package com.emerson.gatewayservice.security;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import com.emerson.gatewayservice.service.user.UserFacade;

@Component
public class UserJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
	
	private final UserFacade userFacade;

	public UserJwtAuthenticationConverter(UserFacade userFacade) {
		this.userFacade = userFacade;
	}

	@Override
	public AbstractAuthenticationToken convert(Jwt jwt) {
		String userId = jwt.getSubject();
		List<String> roles = this.userFacade.getRolesById(userId);
		List<SimpleGrantedAuthority> authorities = roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r)).toList();

		return new JwtAuthenticationToken(jwt, authorities);
	}
	
}
