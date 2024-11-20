package com.Tablely.Tablely.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Tablely.Tablely.global.exception.ErrorCode;
import com.Tablely.Tablely.user.UserException;
import com.Tablely.Tablely.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryService {

	private final UserRepository userRepository;

	public void checkEmailDuplicated(String email) {
		boolean userExists = userRepository.existsByEmail(email);
		if (userExists) {
			throw new UserException(ErrorCode.USER_DUPLICATED);
		}
	}
}
