package com.sanchae.coderun.practice.controller;

import com.sanchae.coderun.practice.dto.session.PracticeSessionRequestDto;
import com.sanchae.coderun.practice.dto.session.PracticeSessionResponseDto;
import com.sanchae.coderun.practice.service.PracticeSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/practice")
@RequiredArgsConstructor
public class PracticeSessionController {

    private final PracticeSessionService sessionService;

    @PostMapping("/sessions")
    public ResponseEntity<PracticeSessionResponseDto> savePracticeRecord(@RequestBody PracticeSessionRequestDto requestDto) {
        PracticeSessionResponseDto responseDto = sessionService.savePracticeRecord(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}