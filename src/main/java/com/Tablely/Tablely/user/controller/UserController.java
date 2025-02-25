package com.Tablely.Tablely.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Tablely.Tablely.global.jwt.JwtUserInfo;
import com.Tablely.Tablely.global.jwt.JwtUtil;
import com.Tablely.Tablely.user.dto.UserJoinReqDto;
import com.Tablely.Tablely.user.dto.UserLoginReqDto;
import com.Tablely.Tablely.user.facade.UserFacade;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
	private final UserFacade userFacade;
	private final JwtUtil jwtUtil;

	public static final String AUTHORIZATION_HEADER = "Authorization";

	// 회원가입
	@PostMapping("/members")
	private ResponseEntity<Void> add(@Valid @RequestBody UserJoinReqDto userJoinReqDto) {
		userJoinReqDto.checkPassword();
		userFacade.add(userJoinReqDto);
		return ResponseEntity
			.status(HttpStatus.CREATED).
			build();
	}

	// 회원탈퇴
	@DeleteMapping("/members/{userId}")
	private ResponseEntity<Void> delete(@PathVariable Long userId, HttpServletRequest request) {
		// 토큰에 있는 유저와 입력받은 유저간 맞는지 check 하고 삭제한다.
		JwtUserInfo requestUser = (JwtUserInfo) request.getAttribute("userInfo");
		if (!requestUser.getUserId().equals(userId)) {
			return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED).build();
		}

		userFacade.delete(userId);

		return ResponseEntity
			.status(HttpStatus.OK).
			build();
	}



	// 로그인
	@PostMapping("/login")
	private ResponseEntity<Void> login(@Valid @RequestBody UserLoginReqDto userLoginReqDto, HttpServletResponse response) {
		JwtUserInfo loginUser = userFacade.login(userLoginReqDto.getEmail(), userLoginReqDto.getPassword());
		String jwtToken = jwtUtil.createToken(loginUser);

		response.setHeader(AUTHORIZATION_HEADER, "Bearer " + jwtToken);

		return ResponseEntity
			.status(HttpStatus.CREATED).
			build();
	}

	/**
	 *  로그아웃
	 *  로그아웃은 프론트에서 토큰을 삭제하는 방식으로 구현
	 *  TODO: 로그아웃시 토큰과 ip를 저정하여 블랙리스트를 구현. accessToken의 유지시간 정도? 스케줄링을 사용하여 블랙리스크 삭제 예정
 	 */
}