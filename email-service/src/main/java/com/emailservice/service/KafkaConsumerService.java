package com.emailservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.emailservice.dto.EmailDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KafkaConsumerService {

	private final ObjectMapper objectMapper;
	private final EmailService emailService;

	@KafkaListener(topics = "send-email-topic", groupId = "email-group")
	public void consumeEmailService(String message) {
		try {
			EmailDto emailDto = objectMapper.readValue(message, EmailDto.class);
			emailService.sendEmail(emailDto);
			System.out.println("Email sent via Kafka to: " + emailDto.getTo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
