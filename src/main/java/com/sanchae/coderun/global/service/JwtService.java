package com.sanchae.coderun.global.service;

import com.sanchae.coderun.domain.auth.service.LocalUserDetailsService;
import com.sanchae.coderun.global.properties.JwtProperties;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final JwtProperties jwtProperties;
    private final SecretKey secretKey;
    private final JwtParser jwtParser;
    private final LocalUserDetailsService userDetailsService;

    private final Long TOKEN_EXPIRE_TIME = 24 * 60 * 60 * 1000L;

    public JwtService(JwtProperties jwtProperties, LocalUserDetailsService userDetailsService) {
        this.jwtProperties = jwtProperties;
        this.userDetailsService = userDetailsService;

        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretKey()));
        jwtParser = Jwts.parser().verifyWith(secretKey).build();
    }

    public String generateToken(String email) {

        Date now = new Date();
        Date expiration = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                .issuer(jwtProperties.getIssuer())
                .subject(email)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    public Authentication verifyToken(String token) throws AuthenticationException, UsernameNotFoundException {
        String email = jwtParser.parseSignedClaims(token).getPayload().getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }


}
