package com.emerson.userinfoservice.infrastructure.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.userinfoservice.application.usecase.UpdateUserPersonalInfoUseCase;
import com.emerson.userinfoservice.domain.model.User;
import com.emerson.userinfoservice.infrastructure.controller.dto.UpdatePersonalInfoRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UpdateUserServiceFacade {
	
	private final UpdateUserPersonalInfoUseCase updateUserPersonalInfoUseCase;
	
	@Transactional
	public User updatePersonalInfo(String id, UpdatePersonalInfoRequest personalInfoRequest) {
		return this.updateUserPersonalInfoUseCase.execute(id, personalInfoRequest.fullName(), personalInfoRequest.cpf());
	}
	
}
