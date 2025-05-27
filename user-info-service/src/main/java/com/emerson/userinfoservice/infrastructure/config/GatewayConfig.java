package com.emerson.userinfoservice.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.emerson.userinfoservice.application.gateways.UserInfoRepositoryGateway;
import com.emerson.userinfoservice.infrastructure.gateways.UserInfoRepositoryGatewayImpl;
import com.emerson.userinfoservice.infrastructure.persistence.mapper.UserMapper;
import com.emerson.userinfoservice.infrastructure.persistence.repository.JpaUserInfoRepository;

@Configuration
public class GatewayConfig {
	
	@Bean
	UserInfoRepositoryGateway userRepositoryGateway(JpaUserInfoRepository repository, UserMapper userEntityMapper) {
		return new UserInfoRepositoryGatewayImpl(repository, userEntityMapper);
	}

}
