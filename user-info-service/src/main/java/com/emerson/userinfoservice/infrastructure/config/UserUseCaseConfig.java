package com.emerson.userinfoservice.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.emerson.userinfoservice.application.gateways.UserInfoRepositoryGateway;
import com.emerson.userinfoservice.application.usecase.CreateUserUseCase;
import com.emerson.userinfoservice.application.usecase.DeleteUserByIdUseCase;
import com.emerson.userinfoservice.application.usecase.FindUserByIdUseCase;
import com.emerson.userinfoservice.application.usecase.UpdateUserPersonalInfoUseCase;

@Configuration
public class UserUseCaseConfig {
	
	@Bean
	CreateUserUseCase createUserUseCase(UserInfoRepositoryGateway userRepositoryGateway) {
		return new CreateUserUseCase(userRepositoryGateway);
	}
	
	@Bean
	DeleteUserByIdUseCase deleteUserByIdUseCase(UserInfoRepositoryGateway userRepositoryGateway) {
		return new DeleteUserByIdUseCase(userRepositoryGateway);
	}
	
	@Bean
	FindUserByIdUseCase findUserByIdUseCase(UserInfoRepositoryGateway userRepositoryGateway) {
		return new FindUserByIdUseCase(userRepositoryGateway);
	}
	
	@Bean
	UpdateUserPersonalInfoUseCase updateUserPersonalInfoUseCase(UserInfoRepositoryGateway userRepositoryGateway, FindUserByIdUseCase findUserByIdUseCase) {
		return new UpdateUserPersonalInfoUseCase(userRepositoryGateway, findUserByIdUseCase);
	}

}
