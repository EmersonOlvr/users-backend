package com.emerson.authservice.infrastructure.messaging.kafka.producer;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * Serviço responsável por publicar eventos relacionados a usuários no Kafka.
 *
 * <p>Os eventos são enviados como mensagens JSON para tópicos pré-definidos.</p>
 *
 * <p>Utiliza {@link KafkaProducerService} para o envio efetivo das mensagens.</p>
 */
@Service
public class UserEventProducer {
	
	private final KafkaProducerService kafkaProducerService;

	/**
	 * Construtor para injeção de dependência do serviço de envio Kafka.
	 *
	 * @param kafkaProducerService Serviço utilizado para envio das mensagens Kafka.
	 */
	public UserEventProducer(KafkaProducerService kafkaProducerService) {
		this.kafkaProducerService = kafkaProducerService;
	}

	/**
	 * Envia um evento para o tópico {@code auth.user.created} indicando a criação
	 * de um novo usuário.
	 *
	 * @param userId   O identificador do usuário criado.
	 * @param username O nome de usuário associado ao novo usuário.
	 */
	public void sendUserCreatedEvent(String userId, String username) {
		Map<String, Object> data = Map.of(
			"userId", userId,
			"username", username
		);

		this.kafkaProducerService.sendMessage("auth.user.created", userId, data);
	}

	/**
	 * Envia um evento para o tópico {@code auth.user.roles.updated} indicando que
	 * os papéis do usuário foram atualizados.
	 *
	 * @param userId   O identificador do usuário.
	 * @param newRoles Lista com os novos papéis atribuídos ao usuário.
	 */
	public void sendUserRolesUpdatedEvent(String userId, List<String> newRoles) {
		Map<String, Object> data = Map.of(
			"userId", userId,
			"roles", newRoles
		);

		this.kafkaProducerService.sendMessage("auth.user.roles.updated", userId, data);
	}
	
}
