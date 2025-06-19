package com.sanchae.coderun.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanchae.coderun.auth.jwt.dto.ResponseTokenError;
import com.sanchae.coderun.auth.jwt.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final static String AUTH_HEADER = "Authorization";
    private final static String BEARER_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenString(request);
        try {
            if (token != null) {
                Authentication authentication = jwtService.verifyToken(token);
                log.info("authentication: {}", authentication.toString());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | SignatureException | MalformedJwtException | UsernameNotFoundException e) {
            log.error("token is invalid: {}", e.getMessage());
            setErrorResponse(response, e);
        }
    }

    private String getTokenString(HttpServletRequest request) {
        String header = request.getHeader(AUTH_HEADER);

        if (header != null && header.startsWith(BEARER_PREFIX)) {
            return header.substring(BEARER_PREFIX.length());
        }
        return null;
    }

    public void setErrorResponse(HttpServletResponse response, Throwable ex) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json; charset=utf-8");
        ResponseTokenError responseTokenError = new ResponseTokenError(ex.getMessage());
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(responseTokenError));
    }
}
