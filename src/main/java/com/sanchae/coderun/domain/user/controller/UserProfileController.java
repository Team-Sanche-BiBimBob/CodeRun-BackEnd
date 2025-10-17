package com.sanchae.coderun.domain.user.controller;

import com.sanchae.coderun.domain.user.dto.profile.request.UpdateUserProfileRequestDto;
import com.sanchae.coderun.domain.user.dto.profile.response.UpdateUserProfileResponseDto;
import com.sanchae.coderun.domain.user.dto.profile.response.UserProfileResponseDto;
import com.sanchae.coderun.domain.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/{id}")
    public UserProfileResponseDto getUserProfile(@PathVariable Long id) {
        return userProfileService.getUserProfile(id);
    }

    @PatchMapping("/{id}/{userId}")
    public UpdateUserProfileResponseDto updateUserProfile(@PathVariable Long userId, @PathVariable Long id, @RequestBody UpdateUserProfileRequestDto requestDto) {
        return userProfileService.updateUserProfile(id, userId, requestDto);
    }
}
