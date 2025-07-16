package com.sanchae.coderun.practice.service.impl;

import com.sanchae.coderun.practice.repository.PracticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PracticeService {

    private final PracticeRepository practiceRepository;


}
