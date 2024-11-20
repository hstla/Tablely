package com.Tablely.Tablely.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Tablely.Tablely.user.domain.User;
import com.Tablely.Tablely.user.domain.UserType;
import com.Tablely.Tablely.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public void join(String name, String email, UserType userType, String password) {
		User saveUser = new User(email, password, name, userType);
		userRepository.save(saveUser);
	}
}
