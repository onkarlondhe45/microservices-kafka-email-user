package com.userservice.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.userservice.dto.EmailDto;
import com.userservice.model.User;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

	private final UserRepository userRepository;
	private final KafkaProducerService kafkaProducerService;

	@Override
	@CircuitBreaker(name = "user-service", fallbackMethod = "getFallbackMessage")
	public User createUser(User user) {
		User savedUser = userRepository.save(user);
		EmailDto emailDto = new EmailDto();

		emailDto.setTo(user.getEmail());
		emailDto.setSubject("Registration Successful");
		emailDto.setBody("Hi " + savedUser.getName() + ",\n\nThank you for registering in our application.");

		kafkaProducerService.sendEmailMessage(emailDto);
		return savedUser;
	}

	public User getFallbackMessage(User user) {
		log.warn("Email service is down. Email will be sent later when the service is back up.");
        return userRepository.save(user);
    }

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

}
