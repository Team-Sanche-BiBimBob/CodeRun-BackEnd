package com.sanchae.coderun.global.service;

import com.sanchae.coderun.domain.auth.service.AuthService;
import com.sanchae.coderun.domain.auth.service.LocalUserDetailsService;
import com.sanchae.coderun.domain.user.repository.UserRepository;
import com.sanchae.coderun.global.properties.JwtProperties;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@Slf4j
public class JwtService {

    private final JwtProperties jwtProperties;
    private final SecretKey secretKey;
    private final JwtParser jwtParser;
    private final LocalUserDetailsService userDetailsService;

    private final UserRepository userRepository;

    private final Long TOKEN_EXPIRE_TIME = 24 * 60 * 60 * 1000L;

    public JwtService(JwtProperties jwtProperties, LocalUserDetailsService userDetailsService, UserRepository userRepository) {
        this.jwtProperties = jwtProperties;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;

        secretKey = new SecretKeySpec(
                jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS512.key().build().getAlgorithm()
        );
        jwtParser = Jwts.parser().verifyWith(secretKey).build();
    }

    public String generateToken(String email) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                .issuer(jwtProperties.getIssuer())
                .subject(email)
                .claim("username", userRepository.findByEmail(email).getUsername())
                .expiration(expiration)
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();
    }

    public Authentication verifyToken(String token) throws AuthenticationException {
        log.info("token = {}", token);
        log.info("jwtProperties.secretKey = {}", jwtProperties.getSecretKey());
        String email = jwtParser.parseSignedClaims(token).getPayload().getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
