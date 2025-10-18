package com.sanchae.coderun.global.config;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfig {


   @Bean
   @Value("${spring.ai.openai.api-key}")
   public OpenAiApi openAiApi() {
       return new OpenAiApi();
   }


   @Bean
   public OpenAiChatModel openAiChatModel() {
       return openAiChatModel();
   }
}
