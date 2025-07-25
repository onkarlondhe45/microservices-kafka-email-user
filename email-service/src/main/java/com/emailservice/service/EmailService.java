package com.emailservice.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.emailservice.dto.EmailDto;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender javaMailSender;

	public void sendEmail(EmailDto emailDto) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		// true = multipart message (allows HTML)
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

		helper.setTo(emailDto.getTo());
		helper.setSubject(emailDto.getSubject());
		helper.setText(emailDto.getBody(), true);

		javaMailSender.send(message);
	}

}
