package com.sanchae.coderun.language;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LanguageController {

    @GetMapping("/language")
    public String getAllLanguage() {
        return "this is language list";
    }

    @PostMapping("/v1/language")
    public String addLanguage() {
        return "u added language haha";
    }
}
