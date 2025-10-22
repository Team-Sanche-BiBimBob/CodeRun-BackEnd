package com.sanchae.coderun.domain.arcade.dto.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArcadeRoomUpdateRequestDto {
    private Long player1Id;
    private Long player2Id;
}
