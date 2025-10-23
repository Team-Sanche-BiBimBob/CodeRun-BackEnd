package com.sanchae.coderun.global.service;

import com.sanchae.coderun.domain.auth.entity.RefreshToken;
import com.sanchae.coderun.domain.auth.repository.RefreshTokenRepository;
import com.sanchae.coderun.domain.auth.entity.TokenType;
import com.sanchae.coderun.domain.user.entity.User;
import com.sanchae.coderun.domain.user.repository.UserRepository;
import com.sanchae.coderun.global.dto.ResponseAccessToken;
import com.sanchae.coderun.global.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtProperties jwtProperties;
    private final UserDetailsService userDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    private SecretKey secretKey;
    private SecretKey refreshSecretKey;
    private JwtParser accessTokenParser;
    private JwtParser refreshTokenParser;

    @PostConstruct
    public void init() {
        log.info("=== Initializing JwtService ===");
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtProperties.getJwtKey()));
        this.refreshSecretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtProperties.getRefreshKey()));
        this.accessTokenParser = Jwts.parser().verifyWith(secretKey).build();
        this.refreshTokenParser = Jwts.parser().verifyWith(refreshSecretKey).build();
        log.info("=== JwtService Initialized Successfully ===");
    }

    public String generateToken(String email, TokenType type) {
        if(type == null || !type.equals(TokenType.REFRESH)) {
            type = TokenType.ACCESS;
        }

        Date now = new Date();
        Duration duration;

        if (type.equals(TokenType.ACCESS)) {
            duration = Duration.ofDays(1);
        } else {
            duration = Duration.ofDays(7);
        }

        Date expiration = new Date(now.getTime() + duration.toMillis());

        User user = userRepository.findByEmail(email);

        return Jwts.builder()
                .issuer(jwtProperties.getIssuer())
                .subject(email)
                .claim("userid", user.getId())
                .claim("username", user.getUsername())
                .claim("profileId", user.getUserProfile().getUserId())
                .claim("role", user.getRole())
                .claim("type", type)
                .expiration(expiration)
                .signWith(type.equals(TokenType.ACCESS) ? secretKey : refreshSecretKey)
                .compact();
    }

    public Authentication verifyToken(String token) throws AuthenticationException {
        String email = accessTokenParser.parseSignedClaims(token).getPayload().getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
    @Transactional
    public ResponseAccessToken getAccessTokenByUsername(User user) throws UsernameNotFoundException {
        log.info("=== getAccessTokenByUsername START ===");
        log.info("Input user: id={}, email={}", user.getId(), user.getEmail());

        User persistentUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        log.info("Persistent user: id={}, email={}", persistentUser.getId(), persistentUser.getEmail());

        String accessToken = generateToken(persistentUser.getEmail(), TokenType.ACCESS);
        String refreshToken = generateToken(persistentUser.getEmail(), TokenType.REFRESH);

        // RT 저장 - findByUser_Email 또는 findByUserEmail 사용
        RefreshToken rtEntity = refreshTokenRepository.findByUser_email(persistentUser.getEmail())
                .orElseGet(() -> {
                    log.info("Creating new RefreshToken for user: {}", persistentUser.getEmail());
                    RefreshToken newToken = RefreshToken.builder()
                            .user(persistentUser)
                            .refreshToken(refreshToken)  // 여기서 바로 설정
                            .build();

                    log.info("Built RefreshToken - user is null? {}", newToken.getUser() == null);
                    RefreshToken saved = refreshTokenRepository.save(newToken);
                    log.info("Saved RefreshToken: id={}, user_id={}", saved.getId(),
                            saved.getUser() != null ? saved.getUser().getId() : "NULL");
                    return saved;
                });

        log.info("RefreshToken entity: id={}, user_id={}", rtEntity.getId(),
                rtEntity.getUser() != null ? rtEntity.getUser().getId() : "NULL");

        // 기존 토큰 업데이트
        rtEntity.setRefreshToken(refreshToken);
        RefreshToken savedRt = refreshTokenRepository.save(rtEntity);

        log.info("Updated RefreshToken: id={}, user_id={}", savedRt.getId(),
                savedRt.getUser() != null ? savedRt.getUser().getId() : "NULL");
        log.info("=== getAccessTokenByUsername END ===");

        return ResponseAccessToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    public ResponseAccessToken getAccessTokenByRefreshToken(String token) throws JwtException, UsernameNotFoundException {
        log.info("=== Refresh Token Reissue START ===");
        log.info("Received token: {}", token);

        String email = refreshTokenParser.parseSignedClaims(token).getPayload().getSubject();
        log.info("Token parsed successfully");


        User user = userRepository.findByEmail(email);
        log.info("Extracted email: {}", email);

        RefreshToken refreshToken = refreshTokenRepository.findByUser_email(email).orElse(null);

        if (user == null) {
            return ResponseAccessToken.builder().error("Unknown member.").build();
        }

        if (refreshToken != null && !refreshToken.getRefreshToken().equals(token)) {
            refreshTokenRepository.delete(refreshToken); // 비정상 로그인 시도로 봐서 RT 삭제.
            return ResponseAccessToken.builder().error("Refresh Failed").build();
        }

        return getAccessTokenByUsername(user);
    }
}
