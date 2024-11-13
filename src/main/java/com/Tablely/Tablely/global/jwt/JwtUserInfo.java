package com.Tablely.Tablely.global.jwt;

import com.Tablely.Tablely.user.domain.UserType;

public class JwtUserInfo {
    private Long userId;
    private UserType userType;

    public JwtUserInfo(Long userId, UserType userType) {
        this.userId = userId;
        this.userType = userType;
    }

    public Long getUserId() {
        return userId;
    }

    public UserType getUserType() {
        return userType;
    }
}
