package com.plataformae.ws.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plataformae.ws.dto.ApiResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;

    @Value("#{'${auth.prefixes}'.split(',')}")
    private Set<String> prefixes;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService, ObjectMapper objectMapper) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String user;

        String path = request.getRequestURI();
        Set<String> prefixSet = new HashSet<>(prefixes);

        if (prefixSet.stream().anyMatch(path::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authHeader == null ) {
            sendErrorResponse(response, "Error, Header Authorization es requerido");
            return;
        }

        if (!authHeader.startsWith("Bearer ")) {
            sendErrorResponse(response, "Token Invalido");
            return;
        }

        jwt = authHeader.substring(7);
        user = jwtService.extractUsername(jwt);
        if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(user);
            if (Boolean.TRUE.equals(jwtService.validateToken(jwt, userDetails))) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                sendErrorResponse(response, "Token Invalido o expirado");
                return;
            }
        } else {
            sendErrorResponse(response, "Token Invalido o expirado");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        ApiResponse<Object> apiResponse = new ApiResponse<>(message, null, HttpStatus.UNAUTHORIZED.value());
        String jsonResponse = objectMapper.writeValueAsString(apiResponse);
        response.getWriter().write(jsonResponse);
    }
}