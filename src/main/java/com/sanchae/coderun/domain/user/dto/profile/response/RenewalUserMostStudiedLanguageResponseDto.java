package com.sanchae.coderun.domain.user.dto.profile.response;

import com.sanchae.coderun.domain.language.entity.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RenewalUserMostStudiedLanguageResponseDto {
    private Long id;
    private Language mostStudiedLanguage;
    private Long mostStudiedLanguageProgress;
    private Long mostStudiedLanguageScore;
}
