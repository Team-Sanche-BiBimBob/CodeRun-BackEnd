package com.sanchae.coderun.domain.workbook.controller;

import com.sanchae.coderun.domain.workbook.dto.request.WorkbookAiRequestDto;
import com.sanchae.coderun.domain.workbook.dto.response.WorkbookAiResponseDto;
import com.sanchae.coderun.domain.workbook.entity.Workbook;
import com.sanchae.coderun.domain.workbook.entity.WorkbookProblems;
import com.sanchae.coderun.domain.workbook.service.WorkbookService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workbook-ai")
@Tag(name = "문제집 API", description = "문제집 생성을 구현한 API입니다.")
@OpenAPIDefinition(servers = {@Server(url = "https://api.coderun.site/api")})
public class WorkbookAiController {

    private final WorkbookService workbookService;

    @PostMapping("")
    public WorkbookAiResponseDto generateWorkbook(@RequestBody WorkbookAiRequestDto requestDto) {
        return workbookService.generateWorkbook(requestDto);
    }

    @GetMapping("")
    public List<Workbook> getAllWorkbooks() {
        return workbookService.getAllWorkbooks();
    }

    @GetMapping("/{id}")
    public Workbook getWorkbookById(@PathVariable Long id) {
        return workbookService.getWorkbookById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkbookById(@PathVariable Long id) {
        workbookService.deleteWorkbookById(id);
    }

    @GetMapping("/problems/{workbookId}")
    public List<WorkbookProblems> getAllProblemInWorkbook(@PathVariable Long workbookId) {
        return workbookService.getAllProblemInWorkbook(workbookId);
    }

    @GetMapping("/problem/{id}")
    public WorkbookProblems getWorkbookProblemById(@PathVariable Long id) {
        return workbookService.getWorkbookProblems(id);
    }
}