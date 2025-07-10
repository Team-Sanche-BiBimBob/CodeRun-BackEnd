package com.sanchae.coderun.practice.service;

import com.sanchae.coderun.practice.dto.PracticeRequestDto;
import com.sanchae.coderun.practice.dto.PracticeResponseDto;

import java.util.List;

public interface PracticeService {

    List<PracticeResponseDto> getAllPracticeById();
    PracticeResponseDto getPracticeById(Long id);
    PracticeResponseDto searchPractice(PracticeRequestDto requestDto);

}
