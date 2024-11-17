package com.Tablely.Tablely.user.facede;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.Tablely.Tablely.global.service.CipherService;
import com.Tablely.Tablely.user.dto.UserAddReqDto;
import com.Tablely.Tablely.user.service.UserQueryService;
import com.Tablely.Tablely.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserFacade {
	private final UserService userService;
	private final UserQueryService userQueryService;
	private final CipherService oneWayCipherService;

	@Transactional
	public void add(UserAddReqDto userAddReqDto) {
		String encryptPassword = oneWayCipherService.encrypt(userAddReqDto.getPassword());
		userQueryService.checkEmailDuplicated(userAddReqDto.getEmail());
		userService.join(userAddReqDto.getName(), userAddReqDto.getEmail(), userAddReqDto.getUserType(),
			encryptPassword);
	}
}
