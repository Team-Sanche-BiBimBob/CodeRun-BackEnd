package com.sanchae.coderun.practice.controller;

import com.sanchae.coderun.practice.dto.session.PracticeSessionRequestDto;
import com.sanchae.coderun.practice.dto.session.PracticeSessionResponseDto;
import com.sanchae.coderun.practice.service.PracticeSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/practice")
@RequiredArgsConstructor
public class PracticeSessionController {

    private final PracticeSessionService sessionService;

    @PostMapping("/sessions")
    public ResponseEntity<PracticeSessionResponseDto> savePracticeRecord(
            @RequestBody PracticeSessionRequestDto requestDto,
            Principal principal) {
        PracticeSessionResponseDto responseDto = sessionService.savePracticeRecord(requestDto, principal.getName());
        return ResponseEntity.ok(responseDto);
    }
}