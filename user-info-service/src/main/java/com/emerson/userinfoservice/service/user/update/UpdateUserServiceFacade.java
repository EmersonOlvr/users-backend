package com.emerson.userinfoservice.service.user.update;

import org.springframework.stereotype.Service;

import com.emerson.userinfoservice.domain.user.UserInfo;
import com.emerson.userinfoservice.service.user.update.dto.UpdatePersonalInfoRequest;

@Service
public class UpdateUserServiceFacade {
	
	private final UpdateUserPersonalInfoUseCase updateUserPersonalInfoUseCase;
	
	public UpdateUserServiceFacade(UpdateUserPersonalInfoUseCase updateUserPersonalInfoUseCase) {
		this.updateUserPersonalInfoUseCase = updateUserPersonalInfoUseCase;
	}

	public UserInfo updatePersonalInfo(String id, UpdatePersonalInfoRequest personalInfoRequest) {
		return this.updateUserPersonalInfoUseCase.execute(id, personalInfoRequest);
	}
	
}
