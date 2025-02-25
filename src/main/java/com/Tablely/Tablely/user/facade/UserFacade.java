package com.Tablely.Tablely.user.facade;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.Tablely.Tablely.global.exception.ErrorCode;
import com.Tablely.Tablely.global.jwt.JwtUserInfo;
import com.Tablely.Tablely.global.service.CipherService;
import com.Tablely.Tablely.user.UserException;
import com.Tablely.Tablely.user.domain.User;
import com.Tablely.Tablely.user.dto.UserJoinReqDto;
import com.Tablely.Tablely.user.service.UserQueryService;
import com.Tablely.Tablely.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserFacade {
	private final UserService userService;
	private final UserQueryService userQueryService;
	private final CipherService cipherService;

	@Transactional
	public void add(UserJoinReqDto userJoinReqDto) {
		String encryptPassword = cipherService.encrypt(userJoinReqDto.getPassword());
		userQueryService.checkEmailDuplicated(userJoinReqDto.getEmail());
		userService.join(userJoinReqDto.getName(), userJoinReqDto.getEmail(), userJoinReqDto.getUserType(),
			encryptPassword);
	}

	@Transactional(readOnly = true)
	public JwtUserInfo login(String email, String password) {
		User findUser = userQueryService.findByEmail(email);
		passwordCheck(password, findUser);
		return new JwtUserInfo(findUser.getId(), findUser.getUserType());
	}

	// todo soft delete를 추가하여 삭제 유무를 판단 후 삭제한다.
	@Transactional
	public void delete(Long userId) {
		User findUser = userQueryService.findById(userId);
		userService.deleteUser(findUser.getId());
	}

	private void passwordCheck(String password, User findUser) {
		if (!cipherService.match(password, findUser.getPassword())) {
			throw new UserException(ErrorCode.INVALID_PASSWORD);
		}
	}
}