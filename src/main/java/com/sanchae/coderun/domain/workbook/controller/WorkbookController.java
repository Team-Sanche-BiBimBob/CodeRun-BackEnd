package com.sanchae.coderun.domain.workbook.controller;

import com.sanchae.coderun.domain.workbook.dto.request.WorkbookAiRequestDto;
import com.sanchae.coderun.domain.workbook.dto.response.WorkbookAiResponseDto;
import com.sanchae.coderun.domain.workbook.entity.Workbook;
import com.sanchae.coderun.domain.workbook.service.WorkbookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workbook")
@Tag(name = "문제집 API", description = "문제집 생성을 구현한 API입니다.")
public class WorkbookController {

    private final WorkbookService aiworkbookService;

    @PostMapping("")
    public WorkbookAiResponseDto generateWorkbook(@RequestBody WorkbookAiRequestDto requestDto) {
        return aiworkbookService.generateWorkbook(requestDto);
    }

    @GetMapping("")
    public List<Workbook> getAllWorkbooks() {
        return aiworkbookService.getAllWorkbooks();
    }

    @GetMapping("/{id}")
    public Workbook getWorkbookById(@PathVariable Long id) {
        return aiworkbookService.getWorkbookById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkbookById(@PathVariable Long id) {
        aiworkbookService.deleteWorkbookById(id);
    }
}
