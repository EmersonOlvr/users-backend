package com.emerson.authservice.infrastructure.messaging.kafka.producer;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class UserEventProducer {
	
	private final KafkaProducerService kafkaProducerService;

	public UserEventProducer(KafkaProducerService kafkaProducerService) {
		this.kafkaProducerService = kafkaProducerService;
	}

	public void sendUserCreatedEvent(String userId, String username) {
		Map<String, Object> data = Map.of(
			"userId", userId,
			"username", username
		);

		this.kafkaProducerService.sendMessage("auth.user.created", userId, data);
	}
	
	public void sendUserRolesUpdatedEvent(String userId, List<String> newRoles) {
		Map<String, Object> data = Map.of(
			"userId", userId,
			"roles", newRoles
		);

		this.kafkaProducerService.sendMessage("auth.user.roles.updated", userId, data);
	}
	
}
