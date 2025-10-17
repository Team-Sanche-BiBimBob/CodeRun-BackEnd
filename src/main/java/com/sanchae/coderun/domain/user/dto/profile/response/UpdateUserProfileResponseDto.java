package com.sanchae.coderun.domain.user.dto.profile.response;

import lombok.*;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserProfileResponseDto {

    private Long userId;

    private String userDescription;
}
