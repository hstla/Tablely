package com.Tablely.Tablely.global.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.Tablely.Tablely.global.exception.ErrorCode;
import com.Tablely.Tablely.user.domain.UserType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtUtil {

    @Value("${jwt.secretKey}")
    private String secretKey;
    private static Key key;
    private static final Integer EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; // 일주일

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.key = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

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
        Jws<Claims> claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token);

        Long userId = Long.parseLong(claims.getBody().getSubject());
        String userTypeString  = claims.getBody().get("userType", String.class);
        UserType userType = UserType.valueOf(userTypeString);

        return new JwtUserInfo(userId, userType);
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}