package com.Tablely.Tablely.global.jwt;

import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Service;
import com.Tablely.Tablely.global.exception.ErrorCode;
import com.Tablely.Tablely.user.domain.UserType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

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

    public JwtUserInfo getUserInfo(String token) {
        if (token == null || token.isBlank()) {
            throw new JwtException(ErrorCode.JWT_INVALID_TOKEN);
        }

        Jws<Claims> claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token);

        Long userId = Long.parseLong(claims.getBody().getSubject());
        String userTypeString  = claims.getBody().get("userType", String.class);
        UserType userType = UserType.valueOf(userTypeString);

        return new JwtUserInfo(userId, userType);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}