package com.emerson.authservice.infrastructure.service.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.authservice.application.usecase.user.UpdateUserPasswordUseCase;
import com.emerson.authservice.application.usecase.user.UpdateUserRolesUseCase;
import com.emerson.authservice.application.usecase.user.UpdateUserUsernameUseCase;
import com.emerson.authservice.domain.model.User;
import com.emerson.authservice.infrastructure.messaging.kafka.producer.UserEventProducer;
import com.emerson.authservice.infrastructure.util.TransactionUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UpdateUserServiceFacade {
	
	private final UpdateUserUsernameUseCase updateUserUsernameUseCase;
	private final UpdateUserPasswordUseCase updateUserPasswordUseCase;
	private final UpdateUserRolesUseCase updateUserRolesUseCase;
	private final UserEventProducer userEventProducer;
	
	@Transactional
	public User updateUsername(String id, String newUsername) {
		return this.updateUserUsernameUseCase.execute(id, newUsername);
	}

	@Transactional
	public User updatePassword(String id, String currentPassword, String newPassword, String newPasswordRepeat) {
		return this.updateUserPasswordUseCase.execute(id, currentPassword, newPassword, newPasswordRepeat);
	}

	@Transactional
	public User updateRoles(String id, List<String> roles) {
		User user = this.updateUserRolesUseCase.execute(id, roles);
		
		// publica um evento com as novas roles desse usuário
		TransactionUtils.runAfterCommit(() -> this.userEventProducer.sendUserRolesUpdatedEvent(id, roles));
		
		return user;
	}

}
