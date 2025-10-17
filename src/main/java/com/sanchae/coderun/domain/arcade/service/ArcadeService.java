package com.sanchae.coderun.domain.arcade.service;

import com.sanchae.coderun.domain.arcade.dto.request.ArcadeRoomCreateRequestDto;
import com.sanchae.coderun.domain.arcade.dto.request.ArcadeRoomPvpResultRequestDto;
import com.sanchae.coderun.domain.arcade.dto.response.ArcadeRoomCreateResponseDto;
import com.sanchae.coderun.domain.arcade.dto.response.ArcadeRoomPvpResultResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface ArcadeService {
    public ArcadeRoomCreateResponseDto createArcadeRoom(ArcadeRoomCreateRequestDto requestDto);
    public ArcadeRoomPvpResultResponseDto getRoomsResult(Long roomId, ArcadeRoomPvpResultRequestDto requestDto);
}
