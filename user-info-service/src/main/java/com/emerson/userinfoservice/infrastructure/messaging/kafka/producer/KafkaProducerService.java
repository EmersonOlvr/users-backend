package com.emerson.userinfoservice.infrastructure.messaging.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Serviço responsável por serializar objetos em JSON e enviá-los para tópicos Kafka.
 *
 * <p>Utiliza {@link ObjectMapper} para converter os objetos e {@link KafkaTemplate} para envio das mensagens.</p>
 *
 * <p>Oferece métodos sobrecarregados para envio com ou sem chave de partição.</p>
 */
@Service
@Slf4j
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Envia uma mensagem serializada como JSON para o tópico Kafka informado.
	 *
	 * @param topic   O nome do tópico para onde a mensagem será enviada.
	 * @param message O objeto que será serializado e enviado.
	 */
	public void sendMessage(String topic, Object message) {
		try {
			String json = this.objectMapper.writeValueAsString(message);
			this.kafkaTemplate.send(topic, json);
			
		} catch (JsonProcessingException e) {
			log.error("Erro ao serializar mensagem para o tópico '{}'", topic, e);
		}
	}

	/**
	 * Envia uma mensagem serializada como JSON para o tópico Kafka informado,
	 * utilizando uma chave.
	 *
	 * @param topic   O nome do tópico Kafka.
	 * @param key     A chave da mensagem (usada para particionamento no Kafka).
	 * @param message O objeto que será serializado e enviado.
	 */
	public void sendMessage(String topic, String key, Object message) {
		try {
			String json = this.objectMapper.writeValueAsString(message);
			this.kafkaTemplate.send(topic, key, json);
			
		} catch (JsonProcessingException e) {
			log.error("Erro ao serializar mensagem com chave '{}' para o tópico '{}'", key, topic, e);
		}
	}

}
