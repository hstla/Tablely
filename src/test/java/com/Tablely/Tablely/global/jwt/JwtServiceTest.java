package com.Tablely.Tablely.global.jwt;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.Tablely.Tablely.user.domain.UserType;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 어노테이션 설명
 * ExtendWith:
 * MockitoExtension.class:
 */

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    @MockBean
    private JwtService jwtService = new JwtService();

    @Test
    void 토큰_정상_동작() {
        //given
        JwtUserInfo jwtUserInfo = new JwtUserInfo(1L, UserType.CUSTOMER);
        String token = jwtService.createToken(jwtUserInfo);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn(token);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        //when
        JwtUserInfo result = jwtService.getUserId();
        //then
        assertThat(result.getUserId()).isEqualTo(jwtUserInfo.getUserId());
        assertThat(result.getUserType()).isEqualTo(jwtUserInfo.getUserType());
    }
}