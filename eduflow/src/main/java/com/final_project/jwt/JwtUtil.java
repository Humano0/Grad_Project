package com.final_project.jwt;



import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.security.core.AuthenticationException;
import com.final_project.datalayer.Dto.User;
import jakarta.servlet.http.HttpServletRequest;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;


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

    public Claims resolveClaims(HttpServletRequest request){
        try{
            String token = resolveToken(request);
            if (token != null){
                return parseJwtClaims(token);
            }
            return null;
        }catch(ExpiredJwtException e){
            request.setAttribute("expired", e.getMessage());
            throw e;
        }
        catch(Exception e){
            request.setAttribute("invalid", e.getMessage());
            throw e;
        }
    }
    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try{
            return claims.getExpiration().after(new Date());
        }
        catch(Exception e){
            throw e;
        }
    }

    public String extractUsername(String token) {
        return parseJwtClaims(token).getSubject();
    }
    
    public String extractUserRole(String token) {
        return (String) parseJwtClaims(token).get("role");
    }

}
