package com.Tablely.Tablely.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.Tablely.Tablely.global.jwt.JwtUserInfo;
import com.Tablely.Tablely.user.dto.UserAddReqDto;
import com.Tablely.Tablely.user.dto.UserAddResDto;
import com.Tablely.Tablely.user.facede.UserFacade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    // 회원가입
    @PostMapping("/members")
    private ResponseEntity<UserAddResDto> add(@Valid @RequestBody UserAddReqDto userAddReqDto) {
        // log.info("request {}", request.getAttribute("userInfo"));
        // JwtUserInfo jwtUserInfo = (JwtUserInfo) request.getAttribute("userInfo");
        // log.info("getUserId getUserType {} {}", jwtUserInfo.getUserId(), jwtUserInfo.getUserType());

        userFacade.add(userAddReqDto);

        return ResponseEntity
            .status(HttpStatus.CREATED).
            build();
    }
}