package com.sanchae.coderun.practice.service.impl;

import com.sanchae.coderun.practice.dto.session.PracticeSessionRequestDto;
import com.sanchae.coderun.practice.dto.session.PracticeSessionResponseDto;
import com.sanchae.coderun.practice.entity.Practice;
import com.sanchae.coderun.practice.entity.PracticeSession;
import com.sanchae.coderun.practice.repository.PracticeRepository;
import com.sanchae.coderun.practice.repository.PracticeSessionRepository;
import com.sanchae.coderun.practice.service.PracticeSessionService;
import com.sanchae.coderun.user.entity.User;
import com.sanchae.coderun.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PracticeSessionServiceImpl implements PracticeSessionService {

    private final PracticeSessionRepository sessionRepository;
    private final PracticeRepository practiceRepository;
    private final UserRepository userRepository;

    @Override
    public PracticeSessionResponseDto savePracticeRecord(PracticeSessionRequestDto requestDto, String principal) {
        Practice practice = practiceRepository.findById(requestDto.getPracticeId())
                .orElseThrow(() -> new IllegalArgumentException("해당 연습 문제가 존재하지 않습니다."));

        User user = userRepository.findByUsername(principal);

        if (user == null) {
            throw new RuntimeException("찾으시려는 사용자가 존재하지 않습니다.");
        }

        PracticeSession session = PracticeSession.builder()
                .practice(practice)
                .user(user)
                .startTime(requestDto.getStartTime())
                .endTime(LocalDateTime.now())
                .status(requestDto.getStatus())
                .typesPerMinute(requestDto.getTypesPerMinute())
                .correctRate(requestDto.getCorrectRate())
                .build();

        PracticeSession saved = sessionRepository.save(session);

        return new PracticeSessionResponseDto(
                saved.getId(),
                saved.getUser().getId(),
                saved.getPractice().getId(),
                saved.getStatus()
        );
    }
}