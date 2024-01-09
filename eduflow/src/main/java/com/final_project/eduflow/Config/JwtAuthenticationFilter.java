package com.final_project.eduflow.Config;

import com.final_project.eduflow.Services.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.naming.AuthenticationException;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.resolveToken(request);

        if(token != null) {
            Claims claims = jwtUtil.resolveClaims(request);
            try {
                // TODO: FIX YOUR SHIT DAMMIT
                if(jwtUtil.validateClaims(claims)) {
                    String role = jwtUtil.getRole(claims);
                    Long userId = jwtUtil.getId(claims);
                }
            } catch (AuthenticationException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                throw new RuntimeException(e);
            }
        }

        filterChain.doFilter(request, response);
    }
}
