package com.userservice.service;

import java.util.List;

import com.userservice.model.User;

public interface UserService {

	User createUser(User user);

	List<User> getUsers();

}
