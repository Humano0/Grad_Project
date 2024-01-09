package com.final_project.eduflow.Config;

import com.final_project.eduflow.Data.DTO.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    // TODO: ENVIRONMENT VARIABLE
    // Secret key for signing the JWT
    private static final String SECRET_KEY = "mysecretkeyacademia";
    // Expiration time for the JWT in milliseconds (1 hour)
    private static long accessTokenExpiration = 1000 * 60 * 60;

    private final JwtParser jwtParser;

    // Header where the JWT is found in the HTTP request
    private final String TOKEN_HEADER = "Authorization";
    // Prefix for the JWT in the HTTP header
    private final String TOKEN_PREFIX = "Bearer ";

    // Constructor that initializes the JWT parser
    public JwtUtil() {
        this.jwtParser = Jwts.parser().setSigningKey(SECRET_KEY);
    }

    // Creates a JWT for a given user
    public static String createToken(User user) {
        // Create the claims for the JWT, which include the user's role and ID
        Claims claims = Jwts.claims().setSubject(user.getRole());
        claims.put("id", user.getId());
        // Set the issued-at and expiration times for the JWT
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenExpiration));
        // Build and return the JWT
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(tokenCreateTime)
                .setExpiration(tokenValidity)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Parses the claims from a JWT
    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    // Resolves the claims from a JWT in an HTTP request
    public Claims resolveClaims(HttpServletRequest req) {
        try {
            // Get the JWT from the request and parse the claims
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (Exception e) {
            // If an error occurs, set an attribute in the request and rethrow the exception
            req.setAttribute("invalid", e.getMessage());
            throw e;
        }
    }

    // Resolves the JWT from an HTTP request
    public String resolveToken(HttpServletRequest req) {
        // Get the cookies from the request
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // If the cookie is named "token", return its value
                if (cookie.getName().equals("token")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    // Validates the claims from a JWT
    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            // Check if the JWT has expired
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            // If an error occurs, throw an AuthenticationException
            throw new AuthenticationException("Invalid token");
        }
    }

    // Gets the email from the claims
    public String getEmail(Claims claims) {
        return claims.getSubject();
    }

    // Gets the ID from the claims
    public Long getId(Claims claims) {
        return ((Number) claims.get("id")).longValue();
    }
}