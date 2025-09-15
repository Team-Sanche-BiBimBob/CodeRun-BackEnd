package com.sanchae.coderun.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanchae.coderun.domain.arcade.handler.ArcadeWebSocketHandler;
import com.sanchae.coderun.domain.arcade.repository.ArcadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@EnableWebSocket
@Configuration
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final ArcadeRepository arcadeRepository;
    private final ModuleConfig moduleConfig;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ArcadeWebSocketHandler(arcadeRepository, moduleConfig), "/ws/arcade")
                .setAllowedOrigins("*");
    }
}
