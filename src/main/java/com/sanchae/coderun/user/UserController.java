package com.sanchae.coderun.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @PostMapping("/language-selection")
    public String languageSelection() {
        return "language selection";
    }

    @PostMapping("/language-selection/clear")
    public String clearLanguageSelection() {
        return "language selection was cleared";
    }
}
