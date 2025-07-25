package com.userservice.service.imp;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.userservice.dto.EmailDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;

	private final String TOPIC = "send-email-topic";

	public void sendEmailMessage(EmailDto emailDto) {
		try {
			String message = objectMapper.writeValueAsString(emailDto);
			kafkaTemplate.send(TOPIC, message);
			System.out.println("send email event to kafka...!");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
