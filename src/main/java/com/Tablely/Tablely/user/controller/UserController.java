package com.Tablely.Tablely.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Tablely.Tablely.user.dto.UserAddResDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    // 회원가입
    @PostMapping("/members")
    private ResponseEntity<UserAddResDto> add() {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(null);
    }
}