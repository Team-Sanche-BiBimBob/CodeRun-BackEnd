package com.sanchae.coderun.domain.practice.controller;

import com.sanchae.coderun.domain.practice.dto.session.PracticeSessionRequestDto;
import com.sanchae.coderun.domain.practice.dto.session.PracticeSessionResponseDto;
import com.sanchae.coderun.domain.practice.service.PracticeSessionService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/practice")
@RequiredArgsConstructor
@Tag(name = "연습 세션 API", description = "연습 세션 기능을 구현한 API 입니다.")
public class PracticeSessionController {

    private final PracticeSessionService sessionService;

    @PostMapping("/sessions")
    public ResponseEntity<PracticeSessionResponseDto> savePracticeRecord(@RequestBody PracticeSessionRequestDto requestDto) {
        PracticeSessionResponseDto responseDto = sessionService.savePracticeRecord(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}