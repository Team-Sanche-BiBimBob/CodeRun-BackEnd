package com.sanchae.coderun.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sanchae.coderun.global.util.ObjectParser;
import com.sanchae.coderun.global.util.WebSocketQueryHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModuleConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public WebSocketQueryHandler webSocketQueryHandler() {
        return new WebSocketQueryHandler();
    }

    @Bean
    public ObjectParser objectParser() {
        return new ObjectParser();
    }
}
