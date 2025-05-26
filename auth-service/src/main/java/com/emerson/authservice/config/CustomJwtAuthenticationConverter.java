package com.emerson.authservice.config;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

	@SuppressWarnings("unchecked")
	@Override
	public Collection<GrantedAuthority> convert(Jwt jwt) {
		Map<String, Object> realmAccess = jwt.getClaim("realm_access");
		if (realmAccess == null || realmAccess.get("roles") == null) {
			return Collections.emptyList();
		}

		List<String> roles = (List<String>) realmAccess.get("roles");

		return roles.stream()
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
	}
	
}