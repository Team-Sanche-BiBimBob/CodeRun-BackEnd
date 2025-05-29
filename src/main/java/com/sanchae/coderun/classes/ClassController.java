package com.sanchae.coderun.classes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    @GetMapping
    public String getAllClasses() {
        return "this is class list";
    }

    @PostMapping
    public String createClass() {
        return "Class created";
    }

    @PatchMapping("/{classId}")
    public String updateClass(@PathVariable String classId) {
        return "Class updated";
    }

    @PostMapping("/folders")
    public String createFolder() {
        return "Folder created";
    }
}
