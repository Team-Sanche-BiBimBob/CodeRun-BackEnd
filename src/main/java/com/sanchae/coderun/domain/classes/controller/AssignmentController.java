package com.sanchae.coderun.domain.classes.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "과제 API", description = "과제 내기 기능을 구현한 API 입니다.")
public class AssignmentController {

    @GetMapping("/classes/{classId}/assignments")
    public String getAssignments(@PathVariable Long classId) {
        return "this is your classes' assignments";
    }

    @PutMapping("/assignment/{assignmentId}")
    public String updateAssignment(@PathVariable Long assignmentId) {
        return "assignment updated";
    }

    @DeleteMapping("/assignment/{assignmentId}")
    public String deleteAssignment(@PathVariable Long assignmentId) {
        return "assignment deleted";
    }
}
