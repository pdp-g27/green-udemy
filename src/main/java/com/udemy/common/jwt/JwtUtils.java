package com.udemy.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;

@Component
public class JwtUtils {
    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    public String generateToken(String phoneNumber){
        return generateToken(phoneNumber, Collections.emptyMap());
    }
    public String generateToken(String phoneNumber, Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(phoneNumber)
                .setIssuer("https://udemy.com")
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(6, ChronoUnit.HOURS)))
                .signWith(signKey())
                .compact();
    }

    public Claims claims(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
    private Key signKey(){
        byte[] bytes = Base64.getEncoder().encode(SECRET_KEY.getBytes());
        return Keys.hmacShaKeyFor(bytes);
    }
}
