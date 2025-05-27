package com.emerson.authservice.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import com.emerson.authservice.application.gateways.JwtServiceGateway;
import com.emerson.authservice.application.gateways.PasswordServiceGateway;
import com.emerson.authservice.application.gateways.RoleRepositoryGateway;
import com.emerson.authservice.application.gateways.UserRepositoryGateway;
import com.emerson.authservice.infrastructure.gateways.JwtServiceGatewayImpl;
import com.emerson.authservice.infrastructure.gateways.PasswordServiceGatewayImpl;
import com.emerson.authservice.infrastructure.gateways.RoleRepositoryGatewayImpl;
import com.emerson.authservice.infrastructure.gateways.UserRepositoryGatewayImpl;
import com.emerson.authservice.infrastructure.persistence.mapper.RoleMapper;
import com.emerson.authservice.infrastructure.persistence.mapper.UserMapper;
import com.emerson.authservice.infrastructure.persistence.repository.JpaRoleRepository;
import com.emerson.authservice.infrastructure.persistence.repository.JpaUserRepository;

@Configuration
public class GatewayConfig {

	@Bean
	JwtServiceGateway jwtServiceGateway(JwtEncoder jwtEncoder) {
		return new JwtServiceGatewayImpl(jwtEncoder);
	}

	@Bean
	PasswordServiceGateway passwordServiceGateway(PasswordEncoder passwordEncoder) {
		return new PasswordServiceGatewayImpl(passwordEncoder);
	}

	@Bean
	UserRepositoryGateway userRepositoryGateway(JpaUserRepository repository, UserMapper userEntityMapper, RoleMapper roleEntityMapper) {
		return new UserRepositoryGatewayImpl(repository, userEntityMapper, roleEntityMapper);
	}

	@Bean
	RoleRepositoryGateway roleRepositoryGateway(JpaRoleRepository repository, RoleMapper roleEntityMapper) {
		return new RoleRepositoryGatewayImpl(repository, roleEntityMapper);
	}

}
