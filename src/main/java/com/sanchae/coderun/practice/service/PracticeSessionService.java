package com.sanchae.coderun.practice.service;

import com.sanchae.coderun.practice.dto.session.PracticeSessionRequestDto;
import com.sanchae.coderun.practice.dto.session.PracticeSessionResponseDto;

public interface PracticeSessionService {
    PracticeSessionResponseDto startPractice(PracticeSessionRequestDto requestDto);
}