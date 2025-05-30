package com.sanchae.coderun.classes;

import org.hibernate.sql.ast.tree.update.Assignment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
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
