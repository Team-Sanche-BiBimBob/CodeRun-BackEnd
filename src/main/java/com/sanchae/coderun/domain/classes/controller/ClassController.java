package com.sanchae.coderun.domain.classes.controller;

import com.sanchae.coderun.domain.classes.dto.response.ClassroomRequestDto;
import com.sanchae.coderun.domain.classes.dto.response.ClassroomResponseDto;
import com.sanchae.coderun.domain.classes.service.ClassroomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classes")
@Tag(name = "클래스 API", description = "클래스 CRUD 기능을 구현한 API 입니다.")
public class ClassController {

    private final ClassroomService classroomService;

    // 클래스 전체 조회
    @GetMapping
    public ResponseEntity<List<ClassroomResponseDto>> getAllClasses() {
        return ResponseEntity.ok(classroomService.getAllClassrooms());
    }

    // 클래스 생성
    @PostMapping
    public ResponseEntity<ClassroomResponseDto> createClass(@RequestBody ClassroomRequestDto dto) {
        return ResponseEntity.ok(classroomService.createClassroom(dto));
    }

    // 클래스 수정
    @PatchMapping("/{classId}")
    public ResponseEntity<ClassroomResponseDto> updateClass(
            @PathVariable Long classId,
            @RequestBody ClassroomRequestDto dto) {
        return ResponseEntity.ok(classroomService.updateClassroom(classId, dto));
    }
}