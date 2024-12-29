package com.Tablely.Tablely.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Tablely.Tablely.user.dto.UserAddReqDto;
import com.Tablely.Tablely.user.dto.UserAddResDto;
import com.Tablely.Tablely.user.facede.UserFacade;

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