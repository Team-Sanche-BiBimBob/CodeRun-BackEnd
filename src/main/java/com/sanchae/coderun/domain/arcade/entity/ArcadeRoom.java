package com.sanchae.coderun.domain.arcade.entity;

import com.sanchae.coderun.domain.arcade.enums.ArcadeType;
import com.sanchae.coderun.domain.practice.entity.PracticeType;
import com.sanchae.coderun.domain.user.entity.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ArcadeRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private PracticeType eventType;

    private ArcadeType arcadeType;

    @ManyToOne
    @Nullable
    private User player1;

    @ManyToOne
    @Nullable
    private User player2;

    private Long winnerId;
}
