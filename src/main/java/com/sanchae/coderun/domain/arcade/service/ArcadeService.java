package com.sanchae.coderun.domain.arcade.service;

import com.sanchae.coderun.domain.arcade.dto.request.ArcadeRoomCreateRequestDto;
import com.sanchae.coderun.domain.arcade.dto.request.ArcadeRoomResultRequestDto;
import com.sanchae.coderun.domain.arcade.dto.response.ArcadeRoomCreateResponseDto;
import com.sanchae.coderun.domain.arcade.dto.response.ArcadeRoomResultResponseDto;
import com.sanchae.coderun.domain.arcade.entity.ArcadeRoom;
import com.sanchae.coderun.domain.arcade.repository.ArcadeRepository;
import com.sanchae.coderun.domain.user.entity.User;
import com.sanchae.coderun.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArcadeService {

    private final ArcadeRepository arcadeRepository;
    private final UserRepository userRepository;

    @Transactional
    public ArcadeRoomCreateResponseDto createArcadeRoom(ArcadeRoomCreateRequestDto requestDto) {

        User player1 = userRepository.findById(requestDto.getPlayer1Id()).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        User player2 = userRepository.findById(requestDto.getPlayer2Id()).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        log.info("{}", player1.getEmail());
        log.info("{}", player2.getEmail());

        ArcadeRoom arcadeRoom = arcadeRepository.save(ArcadeRoom.builder()
                .player1(player1)
                .player2(player2)
                .arcadeType(requestDto.getArcadeType())
                .eventType(requestDto.getEventType())
                .startTime(LocalDateTime.now())
                .build());

        return ArcadeRoomCreateResponseDto.builder()
                .roomId(arcadeRoom.getId())
                .startTime(arcadeRoom.getStartTime())
                .arcadeType(arcadeRoom.getArcadeType())
                .eventType(arcadeRoom.getEventType())
                .build();
    }

    public ArcadeRoomResultResponseDto getRoomsResult(Long roomId, ArcadeRoomResultRequestDto requestDto) {

        ArcadeRoom arcadeRoom = arcadeRepository.findById(roomId).orElseThrow(() -> new RuntimeException("아케이드 방을 찾을 수 없습니다."));
        User user = userRepository.findById(requestDto.getWinnerId()).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        boolean isRoomExist = arcadeRepository.existsById(roomId);

        if (!isRoomExist) {
            return null;
        }

        ArcadeRoom newArcadeRoom = arcadeRoom.toBuilder()
                .winnerId(user.getId())
                .endTime(LocalDateTime.now())
                .build();

        arcadeRepository.save(newArcadeRoom);

        return ArcadeRoomResultResponseDto.builder()
                .finishTime(newArcadeRoom.getEndTime())
                .winnerId(user.getId())
                .build();
    }
}
