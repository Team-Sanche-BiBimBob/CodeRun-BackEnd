package com.sanchae.coderun.domain.arcade.controller;

import com.sanchae.coderun.domain.arcade.dto.request.ArcadeRoomCreateRequestDto;
import com.sanchae.coderun.domain.arcade.dto.request.ArcadeRoomResultRequestDto;
import com.sanchae.coderun.domain.arcade.dto.response.ArcadeRoomCreateResponseDto;

import com.sanchae.coderun.domain.arcade.dto.response.ArcadeRoomResultResponseDto;
import com.sanchae.coderun.domain.arcade.service.ArcadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
@Tag(name = "아케이드 API", description = "아케이드 방 기능을 구현한 API 입니다.")
public class ArcadeController {

    private final ArcadeService arcadeService;

    @PostMapping()
    public ArcadeRoomCreateResponseDto createService(@RequestBody ArcadeRoomCreateRequestDto requestDto) {
        return arcadeService.createArcadeRoom(requestDto);
    }

    @PostMapping("/result/{id}")
    public ArcadeRoomResultResponseDto roomsResult(@PathVariable Long id, @RequestBody ArcadeRoomResultRequestDto requestDto) {
        return arcadeService.getRoomsResult(id, requestDto);
    }
}
