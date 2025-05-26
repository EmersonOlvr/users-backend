package com.emerson.userinfoservice.kafka.producer;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class UserEventProducer {
	
	private final KafkaProducerService kafkaProducerService;

	public UserEventProducer(KafkaProducerService kafkaProducerService) {
		this.kafkaProducerService = kafkaProducerService;
	}
	
	public void sendUserCreatedEvent(String userId, String fullName, String cpf) {
		Map<String, Object> data = Map.of(
			"userId", userId,
			"fullName", fullName,
			"cpf", cpf
		);

		this.kafkaProducerService.sendMessage("user-info.user.created", userId, data);
	}
	
}
