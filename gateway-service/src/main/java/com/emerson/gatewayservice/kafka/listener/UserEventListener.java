package com.emerson.gatewayservice.kafka.listener;

import java.util.List;
import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.emerson.gatewayservice.service.user.UpdateUserRolesInCacheUseCase;

/**
 * Componente responsável por escutar eventos Kafka relacionados ao gerenciamento de usuários.
 */
@Component
public class UserEventListener {
	
	private final UpdateUserRolesInCacheUseCase updateUserRolesInCacheUseCase;

	public UserEventListener(UpdateUserRolesInCacheUseCase updateUserRolesInCacheUseCase) {
		this.updateUserRolesInCacheUseCase = updateUserRolesInCacheUseCase;
	}

	/**
	 * Listener Kafka que escuta o tópico {@code auth.user.roles.updated}.
	 * 
	 * <p>Extrai o ID do usuário e a nova lista de roles da mensagem, 
	 * e executa a atualização das roles no cache.</p>
	 *
	 * @param message mensagem contendo os dados no formato {@code { "userId": "...", "roles": [...] }}
	 */
	@KafkaListener(topics = "auth.user.roles.updated", groupId = "gateway-group")
	public void listenUserRolesUpdated(Map<String, Object> message) {
		String userId = (String) message.get("userId");

		@SuppressWarnings("unchecked")
		List<String> roles = (List<String>) message.get("roles");
		
		// atualiza as roles desse usuário no cache
		this.updateUserRolesInCacheUseCase.execute(userId, roles);
	}
	
}
