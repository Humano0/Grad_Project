package com.final_project.eduflow.Config;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

public class JwtHandshakeHandler extends DefaultHandshakeHandler{
    
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpServletRequest httpServletRequest = servletRequest.getServletRequest();
            try {
                Claims claims = JwtUtil.resolveClaims(httpServletRequest);
                Long id = JwtUtil.getId(claims);
                if (id != null) {
                    return () -> id.toString();
                }
            } catch (Exception e) {
                // Handle exception (e.g., logging, throw a more specific error, etc.)
                System.err.println("JWT validation error: " + e.getMessage());
            }
        }
        return null; // Returning null will prevent the connection if the JWT is invalid
    }
}
