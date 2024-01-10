package com.final_project.eduflow.Config;

import com.final_project.eduflow.Services.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Resolve the token from the request
        String token = jwtUtil.resolveToken(request);

        if(token != null) {
            // Resolve the claims from the token
            Claims claims = jwtUtil.resolveClaims(request);
            try {
                if(jwtUtil.validateClaims(claims)) {
                    // Get the role and user id from the claims
                    String role = jwtUtil.getRole(claims);
                    Long userId = jwtUtil.getId(claims);

                    // Create a list of granted authorities from the role
                    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

                    // Create an authentication token using the user id and authorities
                    Authentication auth = new UsernamePasswordAuthenticationToken(userId, null, authorities);

                    // Set the authentication in the SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (AuthenticationException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                throw new RuntimeException(e);
            }
        }

        filterChain.doFilter(request, response);
    }
}
