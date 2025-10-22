package com.sanchae.coderun.global.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {
    private String issuer;
    private String jwtKey;
    private String refreshKey;
}
