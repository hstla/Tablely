package com.Tablely.Tablely.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Tablely.Tablely.global.exception.ErrorCode;
import com.Tablely.Tablely.user.UserException;
import com.Tablely.Tablely.user.domain.User;
import com.Tablely.Tablely.user.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserQueryService {
	@Autowired
	private UserRepository userRepository;

	public void checkEmailDuplicated(String email) {
		Optional<User> userOptional = userRepository.findByEmail(email);
		if (userOptional.isPresent()) {
			throw new UserException(ErrorCode.USER_DUPLICATED);
		}
	}

}
