package com.sanchae.coderun.domain.user.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "언어 선택 API", description = "유저의 언어 선택 기능을 구현한 API 입니다.")
@OpenAPIDefinition(servers = {@Server(url = "https://api.coderun.site/api")})
public class UserController {

    @PostMapping("/language-selection")
    public String languageSelection() {
        return "language selection";
    }

    @PostMapping("/language-selection/clear")
    public String clearLanguageSelection() {
        return "language selection was cleared";
    }
}
