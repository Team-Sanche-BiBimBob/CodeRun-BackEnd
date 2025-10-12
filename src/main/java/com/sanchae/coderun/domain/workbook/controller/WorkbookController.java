package com.sanchae.coderun.domain.workbook.controller;

import com.sanchae.coderun.domain.workbook.dto.request.WorkbookAiRequestDto;
import com.sanchae.coderun.domain.workbook.dto.response.WorkbookAiResponseDto;
import com.sanchae.coderun.domain.workbook.entity.Workbook;
import com.sanchae.coderun.domain.workbook.entity.WorkbookProblems;
import com.sanchae.coderun.domain.workbook.service.WorkbookService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.sanchae.coderun.domain.workbook.dto.request.WorkbookCreateRequestDto;
import com.sanchae.coderun.domain.workbook.dto.request.WorkbookUpdateRequestDto;
import com.sanchae.coderun.domain.workbook.dto.response.WorkbookProblemDto;
import com.sanchae.coderun.domain.workbook.dto.response.WorkbookResponseDto;
import com.sanchae.coderun.domain.workbook.service.WorkbookCRUDService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workbook")
@Tag(name = "문제집 API", description = "문제집 생성을 구현한 API입니다.")
public class WorkbookController {

    private final WorkbookCRUDService workbookService; // 타입: WorkbookCRUDService

    /* 생성 */
    @PostMapping
    public ResponseEntity<WorkbookResponseDto> create(@Valid @RequestBody WorkbookCreateRequestDto req) {
        WorkbookResponseDto created = workbookService.create(req);

        // DTO가 record면 created.id(), 클래스면 created.getId()
        Long id = null;
        try { id = (Long) created.getClass().getMethod("id").invoke(created); } catch (Exception ignore) {}
        if (id == null) {
            try { id = (Long) created.getClass().getMethod("getId").invoke(created); } catch (Exception ignore) {}
        }

        return ResponseEntity.created(URI.create("/workbook/" + id)).body(created);
    }

    /* 단건 조회 */
    @GetMapping("/{id}")
    public ResponseEntity<WorkbookResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(workbookService.getById(id));
    }

    /* 전체 조회 */
    @GetMapping
    public ResponseEntity<List<WorkbookResponseDto>> list() {
        return ResponseEntity.ok(workbookService.getAll()); // getALL 아님
    }

    /* 수정(부분 업데이트) */
    @PatchMapping("/{id}")
    public ResponseEntity<WorkbookResponseDto> update(@PathVariable Long id,
                                                      @Valid @RequestBody WorkbookUpdateRequestDto req) {
        return ResponseEntity.ok(workbookService.update(id, req));
    }

    /* 삭제 */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workbookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /* 문제 목록 */
    @GetMapping("/{id}/problems")
    public ResponseEntity<List<WorkbookProblemDto>> listProblems(@PathVariable Long id) {
        return ResponseEntity.ok(workbookService.listProblems(id)); // listproblem 아님
    }
}