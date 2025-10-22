package com.sanchae.coderun.domain.user.entity;
import com.sanchae.coderun.domain.user.dto.user.UserSignupRequestDto;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Data
@Entity
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private Boolean isWaitingForArcade;

    @Nullable
    private Timestamp emailVerifiedDate;

    private Role role;

    @OneToOne
    private UserProfile userProfile;
}