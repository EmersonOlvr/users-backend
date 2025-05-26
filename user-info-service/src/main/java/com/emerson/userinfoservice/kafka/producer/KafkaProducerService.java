package com.emerson.userinfoservice.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	public void sendMessage(String topic, Object message) {
		try {
			String json = this.objectMapper.writeValueAsString(message);
			this.kafkaTemplate.send(topic, json);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String topic, String key, Object message) {
		try {
			String json = this.objectMapper.writeValueAsString(message);
			this.kafkaTemplate.send(topic, key, json);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
