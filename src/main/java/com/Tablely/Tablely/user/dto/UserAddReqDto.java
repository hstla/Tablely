package com.Tablely.Tablely.user.dto;

import com.Tablely.Tablely.user.domain.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAddReqDto {
    @NotNull
    private String name;
    @Email
    private String email;
    @NotNull
    private UserType userType;
    @NotNull
    private String password;
    @NotNull
    private String checkPassword;

    // TODO 비밀번호 확인 메서드 추가
}