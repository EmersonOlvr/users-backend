package com.emerson.gatewayservice.security.example;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TokenUtils {

	public String getCurrentUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication instanceof UserAuthenticationToken jwtAuth) {
			return jwtAuth.getPrincipal().getId();
		}

		throw new IllegalStateException("Usuário não autenticado ou token inválido.");
	}

	public String getBearerToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication instanceof UserAuthenticationToken jwtAuth) {
			return (String) jwtAuth.getCredentials();
		}

		throw new IllegalStateException("Token não encontrado.");
	}

}
