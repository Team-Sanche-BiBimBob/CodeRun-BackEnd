package com.sanchae.coderun.global.config;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfig {


    @Bean
    public OpenAiApi openAiApi(@Value("${spring.ai.openai.api-key}") String apiKey) {
        return new OpenAiApi(apiKey);
    }


    @Bean
    public OpenAiChatModel openAiChatModel(OpenAiApi openAiApi) {
        return new OpenAiChatModel(openAiApi);
    }
}
