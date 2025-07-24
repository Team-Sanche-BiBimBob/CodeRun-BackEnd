package com.sanchae.coderun.domain.practice.service.impl;

import com.sanchae.coderun.domain.practice.repository.PracticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PracticeService {

    private final PracticeRepository practiceRepository;


}
