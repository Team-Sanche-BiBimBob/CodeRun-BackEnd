package com.sanchae.coderun.domain.classes.service;

import com.sanchae.coderun.domain.classes.dto.AddProblemsInFolderRequestDto;
import com.sanchae.coderun.domain.classes.dto.ClassroomFolderRequestDto;
import com.sanchae.coderun.domain.classes.dto.ClassroomFolderResponseDto;
import com.sanchae.coderun.domain.classes.dto.ProblemsInFolderResponseDto;
import com.sanchae.coderun.domain.classes.entity.Classroom;
import com.sanchae.coderun.domain.classes.entity.ClassroomFolder;
import com.sanchae.coderun.domain.classes.repository.ClassroomFolderRepository;
import com.sanchae.coderun.domain.classes.repository.ClassroomRepository;
import com.sanchae.coderun.domain.problem.entity.Problem;
import com.sanchae.coderun.domain.problem.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassroomFolderService {

    private final ClassroomRepository classroomRepository;
    private final ClassroomFolderRepository classroomFolderRepository;
    private final ProblemRepository problemRepository;

    public List<ClassroomFolderResponseDto> getAllFoldersByClassroomId(Long classroomId) {

        Classroom classroom = classroomRepository.findById(classroomId).orElse(null);

        if (classroom == null) {
            return null;
        }

        return classroomFolderRepository.findAll().stream()
                .filter(folder -> folder.getClassroomId().equals(classroomId)) // 특정 id만 필터링
                .map(ClassroomFolderResponseDto::from) // Entity → ResponseDto 변환
                .collect(Collectors.toList());
    }

    public ClassroomFolderResponseDto getFolderById(Long id) {
        ClassroomFolder classroomFolder = classroomFolderRepository.findById(id).orElse(null);

        if (classroomFolder == null) {
            return null;
        }

        return ClassroomFolderResponseDto.builder()
                .id(classroomFolder.getId())
                .classroomId(classroomFolder.getId())
                .title(classroomFolder.getTitle())
                .description(classroomFolder.getDescription())
                .build();
    }

    public ClassroomFolderResponseDto createFolder(ClassroomFolderRequestDto classroomFolderRequestDto) {

        Classroom classroom = classroomRepository.findById(classroomFolderRequestDto.getClassroomId()).orElse(null);

        if (classroom == null) {
            return null;
        }

        ClassroomFolder classroomFolder = ClassroomFolder.builder()
                .classroomId(classroom.getId())
                .title(classroomFolderRequestDto.getTitle())
                .description(classroomFolderRequestDto.getDescription())
                .build();

        ClassroomFolder savedClassroomFolder = classroomFolderRepository.save(classroomFolder);

        return ClassroomFolderResponseDto.builder()
                .classroomId(savedClassroomFolder.getId())
                .id(savedClassroomFolder.getId())
                .title(savedClassroomFolder.getTitle())
                .description(savedClassroomFolder.getDescription())
                .build();
    }

    public ClassroomFolderResponseDto updateFolder(Long id, ClassroomFolderRequestDto classroomFolderRequestDto) {
        ClassroomFolder classroomFolder = classroomFolderRepository.findById(classroomFolderRequestDto.getClassroomId()).orElse(null);

        if (classroomFolder == null) {
            return null;
        }

        ClassroomFolder newClassroomFolder = ClassroomFolder.builder()
                .id(id)
                .classroomId(classroomFolderRequestDto.getClassroomId())
                .title(classroomFolderRequestDto.getTitle())
                .description(classroomFolderRequestDto.getDescription())
                .build();

        ClassroomFolder savedClassroomFolder = classroomFolderRepository.save(newClassroomFolder);

        return ClassroomFolderResponseDto.builder()
                .classroomId(savedClassroomFolder.getId())
                .id(savedClassroomFolder.getId())
                .title(savedClassroomFolder.getTitle())
                .description(savedClassroomFolder.getDescription())
                .build();
    }

    public String deleteFolderById(Long id) {
        ClassroomFolder classroomFolder = classroomFolderRepository.findById(id).orElse(null);

        if (classroomFolder == null) {
            return "찾으시려는 폴더가 없습니다.";
        }

        classroomFolderRepository.delete(classroomFolder);

        return "성공적으로 삭제되었습니다.";
    }

    public ProblemsInFolderResponseDto addProblemsInFolder(Long folderId, AddProblemsInFolderRequestDto addProblemsInFolderRequestDto) {
        ClassroomFolder classroomFolder = classroomFolderRepository.findById(folderId).orElse(null);

        if (classroomFolder == null) {
            return null;
        }

        List<Problem> problems = classroomFolder.getProblems();

        Problem problemToAdd = problemRepository.findById(addProblemsInFolderRequestDto.getProblemId()).orElse(null);

        problems.add(problemToAdd);

        classroomFolder.setProblems(problems);

        classroomFolderRepository.save(classroomFolder);

        return ProblemsInFolderResponseDto.builder()
                .problems(problems)
                .build();
    }

    public String deleteProblemsInFolder(Long folderId, Long problemId) {
        ClassroomFolder classroomFolder = classroomFolderRepository.findById(folderId).orElse(null);

        if (classroomFolder == null) {
            return null;
        }

        List<Problem> problems = classroomFolder.getProblems();

        problems.remove(problemRepository.findById(problemId).orElse(null));

        return "성공적으로 삭제되었습니다.";
    }
}
