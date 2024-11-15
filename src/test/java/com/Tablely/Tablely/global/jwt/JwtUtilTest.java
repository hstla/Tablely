package com.Tablely.Tablely.global.jwt;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.Tablely.Tablely.user.domain.UserType;

/**
 * 어노테이션 설명
 * @SpringBootTest: Spring Boot의 전체 애플리케이션 컨텍스트를 로드하는 어노테이션.
 * JwtUtil의 key값이 주입되기 위해 SpringBootTest 어노테이션 사용
 */
@SpringBootTest
class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @DisplayName("토큰을 생성하고 그 토큰을 통해 사용자 정보를 가져온다.")
    @Test
    void addToken() {
        //given
        JwtUserInfo jwtUserInfo = new JwtUserInfo(1L, UserType.CUSTOMER);
        String token = jwtUtil.createToken(jwtUserInfo);
        System.out.println("token " + token);
        //when
        JwtUserInfo userInfo = jwtUtil.getUserInfo(token);

        //then
        assertThat(userInfo.getUserId()).isEqualTo(jwtUserInfo.getUserId());
        assertThat(userInfo.getUserType()).isEqualTo(jwtUserInfo.getUserType());
    }

    @DisplayName("토큰을 생성하고 검증한다.")
    @Test
    void tokenValidate() {
        //given
        JwtUserInfo jwtUserInfo = new JwtUserInfo(1L, UserType.CUSTOMER);
        String token = jwtUtil.createToken(jwtUserInfo);

        //when
        boolean validateToken = jwtUtil.validateToken(token);

        //then
        assertThat(validateToken).isTrue();
    }
}