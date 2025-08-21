package com.sanchae.coderun.domain.classes.service;

import com.sanchae.coderun.domain.classes.dto.ClassroomRequestDto;
import com.sanchae.coderun.domain.classes.dto.ClassroomResponseDto;
import com.sanchae.coderun.domain.classes.entity.Classroom;
import com.sanchae.coderun.domain.classes.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    //클래스 생성
    public ClassroomResponseDto createClassroom(ClassroomRequestDto dto) {
        Classroom classroom = new Classroom(dto.getName(), dto.getDescription());
        Classroom saved = classroomRepository.save(classroom);
        return new ClassroomResponseDto(saved.getId(), saved.getName(), saved.getDescription());
    }

    //클래스 전체 조회
    public List<ClassroomResponseDto> getAllClassrooms() {
        return classroomRepository.findAll().stream()
                .map(c -> new ClassroomResponseDto(c.getId(), c.getName(), c.getDescription()))
                .collect(Collectors.toList());
    }

    //클래스 수정
    public ClassroomResponseDto updateClassroom(Long id, ClassroomRequestDto dto) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 클래스가 존재하지 않습니다. id=" + id));
        classroom.setName(dto.getName());
        classroom.setDescription(dto.getDescription());
        Classroom updated = classroomRepository.save(classroom);
        return new ClassroomResponseDto(updated.getId(), updated.getName(), updated.getDescription());
    }
}