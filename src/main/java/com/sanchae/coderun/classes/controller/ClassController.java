package com.sanchae.coderun.classes.controller;

import com.sanchae.coderun.classes.dto.ClassroomRequestDto;
import com.sanchae.coderun.classes.dto.ClassroomResponseDto;
import com.sanchae.coderun.classes.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/classes")
public class ClassController {

    private final ClassroomService classroomService;

    //클래스 전체 조회
    @GetMapping
    public ResponseEntity<List<ClassroomResponseDto>> getAllClasses() {
        return ResponseEntity.ok(classroomService.getAllClassrooms());
    }

    //클래스 생성
    @PostMapping
    public ResponseEntity<ClassroomResponseDto> createClass(@RequestBody ClassroomRequestDto dto) {
        return ResponseEntity.ok(classroomService.createClassroom(dto));
    }

    //클래스 수정
    @PatchMapping("/{classId}")
    public ResponseEntity<ClassroomResponseDto> updateClass(
            @PathVariable Long classId,
            @RequestBody ClassroomRequestDto dto) {
        return ResponseEntity.ok(classroomService.updateClassroom(classId, dto));
    }

    //폴더 생성
    @PostMapping("/folders")
    public ResponseEntity<String> createFolder() {
        return ResponseEntity.ok("Folder created");
    }
}