package com.sanchae.coderun.domain.user.dto.profile.response;

import com.sanchae.coderun.domain.language.entity.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RenewalUserRecentlyStudiedLanguageResponseDto {
    private Long id;
    private Language recentlyStudiedLanguage;
    private Long recentlyStudiedLanguageProgress;
    private Long recentlyStudiedLanguageScore;
}
