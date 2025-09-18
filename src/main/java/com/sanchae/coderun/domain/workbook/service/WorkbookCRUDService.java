// src/main/java/com/sanchae/coderun/domain/workbook/service/WorkbookCRUDService.java
package com.sanchae.coderun.domain.workbook.service;

import com.sanchae.coderun.domain.language.entity.Language;
import com.sanchae.coderun.domain.language.repository.LanguageRepository;
import com.sanchae.coderun.domain.workbook.dto.request.AddProblemRequestDto;
import com.sanchae.coderun.domain.workbook.dto.request.UpdateProblemRequestDto;
import com.sanchae.coderun.domain.workbook.dto.request.WorkbookCreateRequestDto;
import com.sanchae.coderun.domain.workbook.dto.request.WorkbookUpdateRequestDto;
import com.sanchae.coderun.domain.workbook.dto.response.WorkbookProblemDto;
import com.sanchae.coderun.domain.workbook.dto.response.WorkbookResponseDto;
import com.sanchae.coderun.domain.workbook.entity.Workbook;
import com.sanchae.coderun.domain.workbook.entity.WorkbookProblems;
import com.sanchae.coderun.domain.workbook.repository.WorkbookProblemsRepository;
import com.sanchae.coderun.domain.workbook.repository.WorkbookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkbookCRUDService {

    private final WorkbookRepository workbookRepository;
    private final LanguageRepository languageRepository;
    private final WorkbookProblemsRepository workbookProblemsRepository;

    @Transactional
    public WorkbookResponseDto create(WorkbookCreateRequestDto req) {
        Language lang = languageRepository.findById(req.languageId())
                .orElseThrow(() -> new EntityNotFoundException("Language not found: " + req.languageId()));

        Workbook wb = Workbook.builder()
                .workbookLanguage(lang)
                .practiceType(req.practiceType())
                .build();

        return toResponse(workbookRepository.save(wb));
    }

    @Transactional(readOnly = true)
    public WorkbookResponseDto getById(Long id) {
        Workbook wb = workbookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Workbook not found: " + id));
        return toResponse(wb);
    }

    @Transactional(readOnly = true)
    public List<WorkbookResponseDto> getAll() {
        return workbookRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional
    public WorkbookResponseDto update(Long id, WorkbookUpdateRequestDto req) {
        Workbook wb = workbookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Workbook not found: " + id));

        if (req.languageId() != null) {
            Language lang = languageRepository.findById(req.languageId())
                    .orElseThrow(() -> new EntityNotFoundException("Language not found: " + req.languageId()));
            wb.setWorkbookLanguage(lang);
        }
        if (req.practiceType() != null) wb.setPracticeType(req.practiceType());

        return toResponse(wb); // 더티체킹 반영
    }

    @Transactional
    public void delete(Long id) {
        if (!workbookRepository.existsById(id)) throw new EntityNotFoundException("Workbook not found: " + id);
        workbookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<WorkbookProblemDto> listProblems(Long workbookId) {
        return workbookProblemsRepository.findByWorkbookId(workbookId).stream()
                .map(wp -> new WorkbookProblemDto(wp.getId(), wp.getContent()))
                .toList();
    }

    // Optional: 문제 추가/수정/삭제
    @Transactional
    public WorkbookProblemDto addProblem(Long workbookId, AddProblemRequestDto req) {
        workbookRepository.findById(workbookId)
                .orElseThrow(() -> new EntityNotFoundException("Workbook not found: " + workbookId));
        WorkbookProblems saved = workbookProblemsRepository.save(
                WorkbookProblems.builder().workbookId(workbookId).content(req.content()).build()
        );
        return new WorkbookProblemDto(saved.getId(), saved.getContent());
    }

    @Transactional
    public WorkbookProblemDto updateProblem(Long problemId, UpdateProblemRequestDto req) {
        WorkbookProblems wp = workbookProblemsRepository.findById(problemId)
                .orElseThrow(() -> new EntityNotFoundException("WorkbookProblem not found: " + problemId));
        wp.setContent(req.content());
        return new WorkbookProblemDto(wp.getId(), wp.getContent());
    }

    @Transactional
    public void deleteProblem(Long problemId) {
        if (!workbookProblemsRepository.existsById(problemId))
            throw new EntityNotFoundException("WorkbookProblem not found: " + problemId);
        workbookProblemsRepository.deleteById(problemId);
    }

    private WorkbookResponseDto toResponse(Workbook wb) {
        Long langId = wb.getWorkbookLanguage() != null ? wb.getWorkbookLanguage().getId() : null;
        String langName = null;
        try { langName = wb.getWorkbookLanguage() != null ? wb.getWorkbookLanguage().getName() : null; } catch (Exception ignored) {}
        int count = 0;
        try { count = workbookProblemsRepository.countByWorkbookId(wb.getId()); } catch (Exception ignored) {}
        return new WorkbookResponseDto(wb.getId(), langId, langName, wb.getPracticeType(), count);
    }
}