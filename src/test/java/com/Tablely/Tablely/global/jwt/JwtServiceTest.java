package com.Tablely.Tablely.global.jwt;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import com.Tablely.Tablely.user.domain.UserType;

/**
 * 어노테이션 설명
 * @ExtendWith(MockitoExtension.class): mockito를 사용할 수 있도록 하는 어노테이션 @Mock, @InjectMocks 사용 가능
 * @InjectMocks: mock 객체들을 실제 객체에 자동으로 주입하는 어노테이션이다.
 */
@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @DisplayName("토큰을 생성하고 그 토큰을 통해 사용자 정보를 가져온다.")
    @Test
    void createToken() {
        //given
        JwtUserInfo jwtUserInfo = new JwtUserInfo(1L, UserType.CUSTOMER);
        String token = jwtService.createToken(jwtUserInfo);

        //when
        JwtUserInfo userInfo = jwtService.getUserInfo(token);

        //then
        assertThat(userInfo.getUserId()).isEqualTo(jwtUserInfo.getUserId());
        assertThat(userInfo.getUserType()).isEqualTo(jwtUserInfo.getUserType());
    }

    @DisplayName("토큰을 생성하고 검증한다.")
    @Test
    void validateToken() {
        //given
        JwtUserInfo jwtUserInfo = new JwtUserInfo(1L, UserType.CUSTOMER);
        String token = jwtService.createToken(jwtUserInfo);

        //when
        boolean validateToken = jwtService.validateToken(token);

        //then
        assertThat(validateToken).isTrue();
    }
}