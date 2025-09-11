package com.sanchae.coderun.domain.practice.service;

import com.sanchae.coderun.domain.practice.dto.PracticeResponseDto;
import com.sanchae.coderun.domain.practice.entity.PracticeType;

import java.util.List;

public interface PracticeService {
    List<PracticeResponseDto> getAllPractice();
    List<PracticeResponseDto> getAllPracticeByLanguageIdAndType(Long languageId, PracticeType type);
    // List<ProblemResponseDto> getPracticeProblems(Long practiceId);
    PracticeResponseDto getPracticeById(Long id);
    // PracticeResponseDto searchPractice(PracticeRequestDto requestDto);
}
