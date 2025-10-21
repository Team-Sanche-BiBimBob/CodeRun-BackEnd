package com.sanchae.coderun.domain.arcade.service.impl;

import com.sanchae.coderun.domain.arcade.dto.request.ArcadeRoomCreateRequestDto;
import com.sanchae.coderun.domain.arcade.dto.request.ArcadeRoomPvpResultRequestDto;
import com.sanchae.coderun.domain.arcade.dto.request.ArcadeRoomUpdateRequestDto;
import com.sanchae.coderun.domain.arcade.dto.response.ArcadeRoomCreateResponseDto;
import com.sanchae.coderun.domain.arcade.dto.response.ArcadeRoomPvpResultResponseDto;
import com.sanchae.coderun.domain.arcade.entity.ArcadeRoom;
import com.sanchae.coderun.domain.arcade.enums.ArcadeType;
import com.sanchae.coderun.domain.arcade.repository.ArcadeRepository;
import com.sanchae.coderun.domain.arcade.service.ArcadeService;
import com.sanchae.coderun.domain.user.entity.User;
import com.sanchae.coderun.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArcadeServiceImpl implements ArcadeService {

    private final ArcadeRepository arcadeRepository;
    private final UserRepository userRepository;

    @Override
    public ArcadeRoomCreateResponseDto updateArcadeRoom(Long roomId, ArcadeRoomUpdateRequestDto requestDto) {
        User player1 = userRepository.findById(requestDto.getPlayer1Id()).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        User player2 = userRepository.findById(requestDto.getPlayer2Id()).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        ArcadeRoom arcadeRoom = arcadeRepository.findById(roomId).orElse(null);

        if (arcadeRoom == null) {
            return null;
        }

        ArcadeRoom newArcadeRoom = arcadeRepository.save(ArcadeRoom.builder()
                .id(roomId)
                .startTime(arcadeRoom.getStartTime())
                .title(arcadeRoom.getTitle())
                .description(arcadeRoom.getDescription())
                .player1(player1)
                .player2(player2)
                .arcadeType(arcadeRoom.getArcadeType())
                .eventType(arcadeRoom.getEventType())
                .endTime(arcadeRoom.getEndTime())
                .winnerId(arcadeRoom.getWinnerId())
                .build());

        arcadeRepository.save(newArcadeRoom);

        return ArcadeRoomCreateResponseDto.builder()
                .title(newArcadeRoom.getTitle())
                .description(newArcadeRoom.getDescription())
                .roomId(arcadeRoom.getId())
                .arcadeType(arcadeRoom.getArcadeType())
                .eventType(arcadeRoom.getEventType())
                .build();
    }

    @Override
    public List<ArcadeRoomCreateResponseDto> getAllRooms() {
        return arcadeRepository.findAll().stream()
                .map(room -> ArcadeRoomCreateResponseDto.builder()
                        .title(room.getTitle())
                        .description(room.getDescription())
                        .arcadeType(room.getArcadeType())
                        .eventType(room.getEventType())
                        .roomId(room.getId())
                        .startTime(room.getStartTime())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public ArcadeRoomCreateResponseDto getRoomById(Long roomId) {
        ArcadeRoom arcadeRoom = arcadeRepository.findById(roomId).orElse(null);

        if (arcadeRoom == null) {
            return null;
        }

        return ArcadeRoomCreateResponseDto.builder()
                .title(arcadeRoom.getTitle())
                .description(arcadeRoom.getDescription())
                .arcadeType(arcadeRoom.getArcadeType())
                .eventType(arcadeRoom.getEventType())
                .roomId(roomId)
                .startTime(arcadeRoom.getStartTime())
                .build();
    }

    @Override
    @Transactional
    public ArcadeRoomCreateResponseDto createArcadeRoom(ArcadeRoomCreateRequestDto requestDto) {

        User player1 = userRepository.findById(requestDto.getPlayer1Id()).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        User player2 = userRepository.findById(requestDto.getPlayer2Id()).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        if (requestDto.getArcadeType().equals(ArcadeType.TIME_ATTACK)) {
            ArcadeRoom arcadeRoom = arcadeRepository.save(ArcadeRoom.builder()
                    .title(requestDto.getTitle())
                    .description(requestDto.getDescription())
                    .player1(player1)
                    .arcadeType(requestDto.getArcadeType())
                    .eventType(requestDto.getEventType())
                    .build());

            return ArcadeRoomCreateResponseDto.builder()
                    .title(arcadeRoom.getTitle())
                    .description(arcadeRoom.getDescription())
                    .roomId(arcadeRoom.getId())
                    .arcadeType(arcadeRoom.getArcadeType())
                    .eventType(arcadeRoom.getEventType())
                    .build();
        }

        ArcadeRoom arcadeRoom = arcadeRepository.save(ArcadeRoom.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .player1(player1)
                .player2(player2)
                .arcadeType(requestDto.getArcadeType())
                .eventType(requestDto.getEventType())
                .build());

        return ArcadeRoomCreateResponseDto.builder()
                .title(arcadeRoom.getTitle())
                .description(arcadeRoom.getDescription())
                .roomId(arcadeRoom.getId())
                .arcadeType(arcadeRoom.getArcadeType())
                .eventType(arcadeRoom.getEventType())
                .build();
    }

    @Override
    public ArcadeRoomPvpResultResponseDto getRoomsResult(Long roomId, ArcadeRoomPvpResultRequestDto requestDto) {

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

        return ArcadeRoomPvpResultResponseDto.builder()
                .finishTime(newArcadeRoom.getEndTime())
                .winnerId(user.getId())
                .build();
    }
}
