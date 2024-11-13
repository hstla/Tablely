package com.Tablely.Tablely.global.jwt;

import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.Tablely.Tablely.user.domain.UserType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final Integer EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; // 일주일

    public String createToken(JwtUserInfo jwtUserInfo) {
        return Jwts.builder()
            .setSubject(String.valueOf(jwtUserInfo.getUserId()))
            .claim("userType", jwtUserInfo.getUserType().name())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
            .signWith(key)
            .compact();
    }

    public JwtUserInfo getUserId() {
        String accessToken = getJwt();
        if (accessToken == null || accessToken.isBlank()) {
            throw new RuntimeException();
        }

        Jws<Claims> claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(accessToken);

        Long userId = Long.parseLong(claims.getBody().getSubject());
        String userTypeString  = claims.getBody().get("userType", String.class);
        UserType userType = UserType.valueOf(userTypeString);

        return new JwtUserInfo(userId, userType);
    }

    private String getJwt() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("Authorization");
    }
}