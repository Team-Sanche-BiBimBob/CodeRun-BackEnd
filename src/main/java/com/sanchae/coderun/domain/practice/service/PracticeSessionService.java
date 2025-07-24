package com.sanchae.coderun.domain.practice.service;

import com.sanchae.coderun.domain.practice.dto.session.PracticeSessionRequestDto;
import com.sanchae.coderun.domain.practice.dto.session.PracticeSessionResponseDto;

public interface PracticeSessionService {
    PracticeSessionResponseDto savePracticeRecord(PracticeSessionRequestDto requestDto);
}