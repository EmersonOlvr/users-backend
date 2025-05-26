package com.emerson.gatewayservice.kafka.listener;

import java.util.List;
import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.emerson.gatewayservice.service.user.UpdateUserRolesInCacheUseCase;

@Component
public class UserEventListener {
	
	private final UpdateUserRolesInCacheUseCase updateUserRolesInCacheUseCase;

	public UserEventListener(UpdateUserRolesInCacheUseCase updateUserRolesInCacheUseCase) {
		this.updateUserRolesInCacheUseCase = updateUserRolesInCacheUseCase;
	}

	@KafkaListener(topics = "auth.user.roles.updated", groupId = "gateway-group")
	public void listenUserRolesUpdated(Map<String, Object> message) {
		String userId = (String) message.get("userId");

		@SuppressWarnings("unchecked")
		List<String> roles = (List<String>) message.get("roles");
		
		// atualiza as roles desse usuário no cache
		this.updateUserRolesInCacheUseCase.execute(userId, roles);
	}
	
}
