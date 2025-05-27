package com.emerson.authservice.infrastructure.service.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.authservice.application.usecase.user.FindUserByIdUseCase;
import com.emerson.authservice.application.usecase.user.FindUserByUsernameUseCase;
import com.emerson.authservice.application.usecase.user.GetUserRolesByIdUseCase;
import com.emerson.authservice.domain.model.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FindUserServiceFacade {
	
	private final FindUserByIdUseCase findUserByIdUseCase;
	private final FindUserByUsernameUseCase findUserByUsernameUseCase;
	private final GetUserRolesByIdUseCase getUserRolesByIdUseCase;
	
	@Transactional
	public User getById(String id) {
		return this.findUserByIdUseCase.execute(id);
	}

	@Transactional
	public User getByUsername(String username) {
		return this.findUserByUsernameUseCase.execute(username);
	}

	@Transactional
	public List<String> getRolesById(String id) {
		return this.getUserRolesByIdUseCase.execute(id);
	}

}
