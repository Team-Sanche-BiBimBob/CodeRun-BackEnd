package com.sanchae.coderun.domain.classes.controller;

import com.sanchae.coderun.domain.classes.dto.AddProblemsInFolderRequestDto;
import com.sanchae.coderun.domain.classes.dto.ClassroomFolderRequestDto;
import com.sanchae.coderun.domain.classes.dto.ClassroomFolderResponseDto;

import com.sanchae.coderun.domain.classes.dto.ProblemsInFolderResponseDto;
import com.sanchae.coderun.domain.classes.service.ClassroomFolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classes/folders")
public class ClassroomFolderController {

    private final ClassroomFolderService classroomFolderService;

    @GetMapping("/{classroomId}")
    public List<ClassroomFolderResponseDto> getAllFoldersByClassroomId(@PathVariable Long classroomId) {
        return classroomFolderService.getAllFoldersByClassroomId(classroomId);
    }

    @GetMapping("/{folderId}")
    public ClassroomFolderResponseDto getFolderById(@PathVariable Long folderId) {
        return classroomFolderService.getFolderById(folderId);
    }

    @PostMapping()
    public ClassroomFolderResponseDto createFolder(@RequestBody ClassroomFolderRequestDto folderRequestDto) {
        return classroomFolderService.createFolder(folderRequestDto);
    }

    @PutMapping("/{id}")
    public ClassroomFolderResponseDto updateFolder(@PathVariable Long id, @RequestBody ClassroomFolderRequestDto folderRequestDto) {
        return classroomFolderService.updateFolder(id, folderRequestDto);
    }

    @DeleteMapping("/{folderId}")
    public String deleteFolder(@PathVariable Long folderId) {
        return classroomFolderService.deleteFolderById(folderId);
    }

    @PostMapping("/{folderId}")
    public ProblemsInFolderResponseDto createFolder(@PathVariable Long folderId, @RequestBody AddProblemsInFolderRequestDto requestDto) {
        return classroomFolderService.addProblemsInFolder(folderId, requestDto);
    }

    @DeleteMapping("/{folderId}/{problemId}")
    public String deleteProblem(@PathVariable Long folderId, @PathVariable Long problemId) {
        return classroomFolderService.deleteProblemsInFolder(folderId, problemId);
    }

}
