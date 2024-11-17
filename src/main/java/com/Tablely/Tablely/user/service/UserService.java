package com.Tablely.Tablely.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Tablely.Tablely.global.exception.ErrorCode;
import com.Tablely.Tablely.user.UserException;
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
