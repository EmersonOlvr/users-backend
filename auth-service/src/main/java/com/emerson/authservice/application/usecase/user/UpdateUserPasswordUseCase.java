package com.emerson.authservice.application.usecase.user;

import com.emerson.authservice.application.gateways.PasswordServiceGateway;
import com.emerson.authservice.application.gateways.UserRepositoryGateway;
import com.emerson.authservice.domain.exception.BadRequestException;
import com.emerson.authservice.domain.model.User;

public class UpdateUserPasswordUseCase {
	
	private final UserRepositoryGateway userRepositoryGateway;
	private final FindUserByIdUseCase findUserByIdUseCase;
	private final PasswordServiceGateway passwordServiceGateway;
	
	public UpdateUserPasswordUseCase(
			UserRepositoryGateway userRepositoryGateway, 
			FindUserByIdUseCase findUserByIdUseCase, 
			PasswordServiceGateway passwordServiceGateway) 
	{
		this.userRepositoryGateway = userRepositoryGateway;
		this.findUserByIdUseCase = findUserByIdUseCase;
		this.passwordServiceGateway = passwordServiceGateway;
	}

	public User execute(String id, String currentPassword, String newPassword, String newPasswordRepeat) {
		User user = this.findUserByIdUseCase.execute(id);
		
		if (currentPassword != null && !this.passwordServiceGateway.matches(currentPassword, user.getPassword()))
			throw new BadRequestException("A senha atual informada está incorreta.");
		
		if (!newPassword.equals(newPasswordRepeat))
			throw new BadRequestException("As novas senhas informadas não são iguais.");
		
		String hashedPassword = this.passwordServiceGateway.encode(newPassword);
		user.setPassword(hashedPassword);
		this.userRepositoryGateway.save(user);
		
		return user;
	}

}
