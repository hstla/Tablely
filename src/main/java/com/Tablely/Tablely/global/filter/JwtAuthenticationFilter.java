package com.Tablely.Tablely.global.filter;

import java.io.IOException;
import com.Tablely.Tablely.global.jwt.JwtService;
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

    private final JwtService jwtService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("request {}", request);
        log.info("response {}", response);
        String authorizationHeader = ((HttpServletRequest)request).getHeader("Authorization");
        if(!authorizationHeader.isEmpty() && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            log.info("jwtToken {}", jwtToken);
            jwtService.validateToken(jwtToken);
        }


        chain.doFilter(request, response);
    }
}