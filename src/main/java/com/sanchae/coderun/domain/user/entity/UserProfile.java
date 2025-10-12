package com.sanchae.coderun.domain.user.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.File;

@Entity
@Data
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키

    private Long userId;

    private File profileImage;

    private String userDescription;

}
