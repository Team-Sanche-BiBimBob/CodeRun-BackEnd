package com.sanchae.coderun.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanchae.coderun.domain.arcade.handler.PvpArcadeWebSocketHandler;
import com.sanchae.coderun.domain.arcade.handler.TimeAttackArcadeWebSocketHandler;
import com.sanchae.coderun.domain.arcade.repository.ArcadeRepository;
import com.sanchae.coderun.global.util.ObjectParser;
import com.sanchae.coderun.global.util.WebSocketQueryHandler;
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
    private final ObjectMapper objectMapper;
    private final WebSocketQueryHandler webSocketQueryHandler;
    private final ObjectParser objectParser;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new PvpArcadeWebSocketHandler(arcadeRepository, objectMapper, webSocketQueryHandler, objectParser), "/ws/pvp")
                .setAllowedOrigins("*");

        registry.addHandler(new TimeAttackArcadeWebSocketHandler(arcadeRepository, webSocketQueryHandler, objectParser, objectMapper), "/ws/time-attack")
                .setAllowedOrigins("*");
    }
}
