package com.emerson.authservice.service.user.update;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emerson.authservice.domain.user.User;

@Service
public class UpdateUserServiceFacade {
	
	private final UpdateUserUsernameUseCase updateUserUsernameUseCase;
	private final UpdateUserPasswordUseCase updateUserPasswordUseCase;
	private final UpdateUserRolesUseCase updateUserRolesUseCase;
	
	public UpdateUserServiceFacade(
			UpdateUserUsernameUseCase updateUserUsernameUseCase, 
			UpdateUserPasswordUseCase updateUserPasswordUseCase, 
			UpdateUserRolesUseCase updateUserRolesUseCase) 
	{
		this.updateUserUsernameUseCase = updateUserUsernameUseCase;
		this.updateUserPasswordUseCase = updateUserPasswordUseCase;
		this.updateUserRolesUseCase = updateUserRolesUseCase;
	}

	public User updateUsername(String id, String newUsername) {
		return this.updateUserUsernameUseCase.execute(id, newUsername);
	}
	
	public User updatePassword(String id, String currentPassword, String newPassword, String newPasswordRepeat) {
		return this.updateUserPasswordUseCase.execute(id, currentPassword, newPassword, newPasswordRepeat);
	}

	public User updateRoles(String id, List<String> roles) {
		return this.updateUserRolesUseCase.execute(id, roles);
	}

}
