package com.final_project.jwt;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.final_project.datalayer.Dto.User;

import io.jsonwebtoken.*;
import java.util.Date;


@Component
public class JwtUtil {
    
    private final String secret_key = "secretkey";
    private long accessTokenValidity = 60*60*1000;

    public String createToken(User user){
        
        Claims claims = Jwts.claims().setSubject(user.getName());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        claims.put("studentId", user.getStudentId());

        Date tokenCreateTİme = new Date();
        Date tokenExpireTime = new Date(tokenCreateTİme.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(tokenCreateTİme)
                .setExpiration(tokenExpireTime)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    private Claims parseJwtClaims(String token){
        return Jwts.parser().parseClaimsJws(token).getBody();
        //return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
    }

    public Claims resolveClaims(String token){
        return parseJwtClaims(token);
    }
}
