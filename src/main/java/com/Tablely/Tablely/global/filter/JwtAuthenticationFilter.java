package com.Tablely.Tablely.global.filter;

import static com.Tablely.Tablely.global.jwt.JwtUtil.*;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import com.Tablely.Tablely.global.exception.ErrorCode;
import com.Tablely.Tablely.global.jwt.JwtException;
import com.Tablely.Tablely.global.jwt.JwtUserInfo;
import com.Tablely.Tablely.global.jwt.JwtUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements Filter {

    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = ((HttpServletRequest)request).getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new JwtException(ErrorCode.JWT_INVALID_TOKEN);
        }
        String jwtToken = authorizationHeader.substring(7);
        if(!jwtUtil.validateToken(jwtToken)) {
            throw new JwtException(ErrorCode.JWT_INVALID_TOKEN);
        }

        JwtUserInfo userInfo = jwtUtil.getUserInfo(jwtToken);
        request.setAttribute("userInfo", userInfo);
        chain.doFilter(request, response);
    }
}