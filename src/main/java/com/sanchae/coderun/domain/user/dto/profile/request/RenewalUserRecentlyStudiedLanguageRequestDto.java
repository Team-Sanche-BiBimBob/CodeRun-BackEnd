package com.sanchae.coderun.domain.user.dto.profile.request;

import com.sanchae.coderun.domain.language.entity.Language;
import lombok.Getter;

@Getter
public class RenewalUserRecentlyStudiedLanguageRequestDto {
    private Long id;
    private Language recentlyStudiedLanguage;
    private Long recentlyStudiedLanguageProgress;
    private Long recentlyStudiedLanguageScore;
}
