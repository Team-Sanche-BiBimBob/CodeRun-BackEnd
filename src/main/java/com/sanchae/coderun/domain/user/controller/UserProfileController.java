package com.sanchae.coderun.domain.user.controller;


import com.sanchae.coderun.domain.user.dto.profile.request.UpdateUserProfileRequestDto;
import com.sanchae.coderun.domain.user.dto.profile.response.RenewalUserMostStudiedLanguageResponseDto;
import com.sanchae.coderun.domain.user.dto.profile.response.RenewalUserRecentlyStudiedLanguageResponseDto;
import com.sanchae.coderun.domain.user.dto.profile.response.UpdateUserProfileResponseDto;
import com.sanchae.coderun.domain.user.dto.profile.response.UserProfileResponseDto;
import com.sanchae.coderun.domain.user.service.UserProfileService;
import com.sanchae.coderun.global.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user/profile") // yml에 /api 있으니 여기엔 /api 붙이지 X
@RequiredArgsConstructor
@Slf4j
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping(
            value = "/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> uploadProfileImage(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        if (file == null || file.isEmpty()) {
            log.error("File is null or empty");
            return ResponseEntity.badRequest().body("file part is empty");
        }

        try {
            String url = userProfileService.updateProfileImage(customUserDetails, file);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            log.error("Error uploading profile image", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload image: " + e.getMessage());
        }
    }

    @GetMapping("/image")
    public ResponseEntity<byte[]> getProfileImage(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("=== Get Profile Image ===");

        try {
            return userProfileService.getProfileImage(customUserDetails.getId());
        } catch (Exception e) {
            log.error("Error loading profile image", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileResponseDto> getUserProfile(@PathVariable Long userId) {
        log.info("=== Get User Profile ===");
        log.info("userId = {}", userId);

        try {
            UserProfileResponseDto response = userProfileService.getUserProfile(userId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            log.error("User profile not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error retrieving user profile", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponseDto> getMyUserProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("=== Get User Profile ===");

        try {
            UserProfileResponseDto response = userProfileService.getUserProfile(customUserDetails.getId());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            log.error("User profile not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error retrieving user profile", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<UpdateUserProfileResponseDto> updateUserProfile(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody UpdateUserProfileRequestDto updateUserProfileRequestDto
    ) {
        log.info("=== Update User Profile ===");
        log.info("userId = {}", customUserDetails.getId());

        try {
            UpdateUserProfileResponseDto response =
                    userProfileService.updateUserProfile(customUserDetails.getId(), updateUserProfileRequestDto);

            if (response == null) {
                log.warn("User profile not found for userId = {}", customUserDetails.getId());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error updating user profile", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
