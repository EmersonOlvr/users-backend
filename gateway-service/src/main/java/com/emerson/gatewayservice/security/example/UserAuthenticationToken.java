package com.emerson.gatewayservice.security.example;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import lombok.Getter;

/**
 * Classe apenas de modelo, para microserviços que gerem e validem seus próprios tokens.
 * Serve para obter mais facilmente qual usuário do banco está logado.
 */
public class UserAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 9200765670721407457L;

	// classe apenas de exemplo, pode ser uma entidade do sistema
	@Getter
	public class User {
		private String id;
	}
	
	private final User principal;
	private final Jwt jwt;

	public UserAuthenticationToken(User principal, Jwt jwt, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.jwt = jwt;
		setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return jwt.getTokenValue();
	}

	@Override
	public User getPrincipal() {
		return this.principal;
	}
	
}