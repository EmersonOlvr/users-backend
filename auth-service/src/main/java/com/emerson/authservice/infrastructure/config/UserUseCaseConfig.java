package com.emerson.authservice.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.emerson.authservice.application.gateways.PasswordServiceGateway;
import com.emerson.authservice.application.gateways.RoleRepositoryGateway;
import com.emerson.authservice.application.gateways.UserRepositoryGateway;
import com.emerson.authservice.application.usecase.user.CreateUserUseCase;
import com.emerson.authservice.application.usecase.user.DeleteUserByIdUseCase;
import com.emerson.authservice.application.usecase.user.DeleteUserByUsernameUseCase;
import com.emerson.authservice.application.usecase.user.FindUserByIdUseCase;
import com.emerson.authservice.application.usecase.user.FindUserByUsernameUseCase;
import com.emerson.authservice.application.usecase.user.GetUserRolesByIdUseCase;
import com.emerson.authservice.application.usecase.user.UpdateUserPasswordUseCase;
import com.emerson.authservice.application.usecase.user.UpdateUserRolesUseCase;
import com.emerson.authservice.application.usecase.user.UpdateUserUsernameUseCase;

@Configuration
public class UserUseCaseConfig {

	@Bean
	CreateUserUseCase createUserUseCase(UserRepositoryGateway userRepositoryGateway, 
			RoleRepositoryGateway roleRepositoryGateway, 
			PasswordServiceGateway passwordServiceGateway) 
	{
		return new CreateUserUseCase(userRepositoryGateway, roleRepositoryGateway, passwordServiceGateway);
	}
	
	@Bean
	DeleteUserByIdUseCase deleteUserByIdUseCase(UserRepositoryGateway userRepositoryGateway) {
		return new DeleteUserByIdUseCase(userRepositoryGateway);
	}
	
	@Bean
	DeleteUserByUsernameUseCase deleteUserByUsernameUseCase(UserRepositoryGateway userRepositoryGateway) {
		return new DeleteUserByUsernameUseCase(userRepositoryGateway);
	}
	
	@Bean
	FindUserByIdUseCase findUserByIdUseCase(UserRepositoryGateway userRepositoryGateway) {
		return new FindUserByIdUseCase(userRepositoryGateway);
	}
	
	@Bean
	FindUserByUsernameUseCase findUserByUsernameUseCase(UserRepositoryGateway userRepositoryGateway) {
		return new FindUserByUsernameUseCase(userRepositoryGateway);
	}
	
	@Bean
	GetUserRolesByIdUseCase getUserRolesByIdUseCase(UserRepositoryGateway userRepositoryGateway) {
		return new GetUserRolesByIdUseCase(userRepositoryGateway);
	}
	
	@Bean
	UpdateUserPasswordUseCase updateUserPasswordUseCase(UserRepositoryGateway userRepositoryGateway, FindUserByIdUseCase findUserByIdUseCase, PasswordServiceGateway passwordServiceGateway) {
		return new UpdateUserPasswordUseCase(userRepositoryGateway, findUserByIdUseCase, passwordServiceGateway);
	}
	
	@Bean
	UpdateUserRolesUseCase updateUserRolesUseCase(FindUserByIdUseCase findUserByIdUseCase, UserRepositoryGateway userRepositoryGateway, RoleRepositoryGateway roleRepositoryGateway) {
		return new UpdateUserRolesUseCase(findUserByIdUseCase, userRepositoryGateway, roleRepositoryGateway);
	}
	
	@Bean
	UpdateUserUsernameUseCase updateUserUsernameUseCase(UserRepositoryGateway userRepositoryGateway, FindUserByIdUseCase findUserByIdUseCase) {
		return new UpdateUserUsernameUseCase(userRepositoryGateway, findUserByIdUseCase);
	}

}
