package com.sanchae.coderun.domain.user.dto.profile.request;

import com.sanchae.coderun.domain.language.entity.Language;
import lombok.Getter;

@Getter
public class RenewalUserMostStudiedLanguageRequestDto {
    private Long id;
    private Language mostStudiedLanguage;
    private Long mostStudiedLanguageProgress;
    private Long mostStudiedLanguageScore;
}
