// com.sanchae.coderun.domain.user.service.UserProfileService
package com.sanchae.coderun.domain.user.service;

import com.sanchae.coderun.domain.user.dto.profile.request.UpdateUserProfileRequestDto;
import com.sanchae.coderun.domain.user.dto.profile.response.UpdateUserProfileResponseDto;
import com.sanchae.coderun.domain.user.dto.profile.response.UserProfileResponseDto;
import com.sanchae.coderun.global.dto.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UserProfileService {
    UserProfileResponseDto getUserProfile(Long id);

    UpdateUserProfileResponseDto updateUserProfile(Long userId, UpdateUserProfileRequestDto dto);

    // 추가
    String updateProfileImage(CustomUserDetails userDetails, MultipartFile file);

    ResponseEntity<byte[]> getProfileImage(Long userId);
}