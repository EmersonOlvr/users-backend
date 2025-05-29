package com.emerson.userinfoservice.infrastructure.messaging.kafka.producer;

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
	public void sendUserCreatedEvent(String userId, String fullName, String cpf) {
		Map<String, Object> data = Map.of(
			"userId", userId,
			"fullName", fullName,
			"cpf", cpf
		);

		this.kafkaProducerService.sendMessage("user-info.user.created", userId, data);
	}
	
}
