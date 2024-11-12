package com.Tablely.Tablely.global.jwt;

import java.security.Key;
import java.util.Date;
import com.Tablely.Tablely.user.domain.UserType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtService {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final Integer EXPIRE_TIME = 1000*60*60*24*7; // 일주일


    public String generateToken(String userId, UserType userType) {
        return Jwts.builder()
            .setSubject(userId)
            .claim("userType", userType)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_TIME))
            .signWith(key)
            .compact();
    }






}