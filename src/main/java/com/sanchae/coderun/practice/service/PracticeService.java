package com.sanchae.coderun.practice.service;

import com.sanchae.coderun.practice.dto.PracticeRequestDto;
import com.sanchae.coderun.practice.dto.PracticeResponseDto;
import com.sanchae.coderun.practice.entity.Practice;

import java.util.List;

public interface PracticeService {

    List<Practice> getAllPractice();
    Practice getPracticeById(Long id);
    Practice searchPractice(PracticeRequestDto requestDto);
    PracticeResponseDto createPractice(PracticeRequestDto requestDto);

}
