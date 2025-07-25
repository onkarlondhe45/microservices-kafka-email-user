package com.emailservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emailservice.dto.EmailDto;
import com.emailservice.service.EmailService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

	private final EmailService emailService;
	
	@PostMapping("/send")
	public ResponseEntity<String> sendEmail(@RequestBody EmailDto emailDto) throws MessagingException{
		emailService.sendEmail(emailDto);
		return ResponseEntity.ok("Email has been successfully sent to " + emailDto.getTo());
	}
}
