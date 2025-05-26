package com.emerson.gatewayservice.config;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import com.emerson.gatewayservice.security.UserJwtAuthenticationConverter;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	public static final String[] PUBLIC_URLS = {
			"/api/*/auth/login",
			"/api/*/user/register",
	};

	private final UserJwtAuthenticationConverter userJwtAuthenticationConverter;

	public SecurityConfig(UserJwtAuthenticationConverter userJwtAuthenticationConverter) {
		this.userJwtAuthenticationConverter = userJwtAuthenticationConverter;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(userJwtAuthenticationConverter)))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(PUBLIC_URLS).permitAll() // Rotas públicas
						.anyRequest().authenticated() // Rotas protegidas
				)
				.exceptionHandling(handling -> handling
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
				.build();
	}
	
	@Bean
	RSAPublicKey publicKey(@Value("${jwt.public.key}") String publicKeyPath) throws Exception {
		String pem = Files.readString(Paths.get(publicKeyPath));
		RSAKey rsaKey = (RSAKey) JWK.parseFromPEMEncodedObjects(pem);
		return rsaKey.toRSAPublicKey();
	}

	@Bean
	JwtDecoder jwtDecoder(RSAPublicKey publicKey) {
		return NimbusJwtDecoder.withPublicKey(publicKey)
								.build();
	}

}
