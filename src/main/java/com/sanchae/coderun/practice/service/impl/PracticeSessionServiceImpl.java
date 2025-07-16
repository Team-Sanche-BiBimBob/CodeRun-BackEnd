package com.sanchae.coderun.practice.service.impl;

import com.sanchae.coderun.practice.dto.session.PracticeSessionRequestDto;
import com.sanchae.coderun.practice.dto.session.PracticeSessionResponseDto;
import com.sanchae.coderun.practice.entity.Practice;
import com.sanchae.coderun.practice.entity.PracticeSession;
import com.sanchae.coderun.practice.repository.PracticeRepository;
import com.sanchae.coderun.practice.repository.PracticeSessionRepository;
import com.sanchae.coderun.practice.service.PracticeSessionService;
import com.sanchae.coderun.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PracticeSessionServiceImpl implements PracticeSessionService {

    private final PracticeSessionRepository sessionRepository;
    private final PracticeRepository practiceRepository;

    @Override
    public PracticeSessionResponseDto savePracticeRecord(PracticeSessionRequestDto requestDto) {
        Practice practice = practiceRepository.findById(requestDto.getPracticeId())
                .orElseThrow(() -> new IllegalArgumentException("해당 연습 문제가 존재하지 않습니다."));

        // 추후 Spring Security 적용 시 로그인한 사용자 정보로 대체
        User user = new User();
        user.setId(1L); // 임시 사용자 ID 설정

        PracticeSession session = new PracticeSession(user, practice, requestDto.getStatus());
        session.setStartTime(LocalDateTime.now());

        PracticeSession saved = sessionRepository.save(session);

        return new PracticeSessionResponseDto(
                saved.getId(),
                saved.getUser().getId(),
                saved.getPractice().getId(),
                saved.getStatus()
        );
    }
}