package com.sanchae.coderun.domain.arcade.dto.request;

import lombok.Data;
import org.springframework.web.socket.TextMessage;

@Data
public class ArcadeRoomRequestDto {
    private TextMessage player1Points;
    private TextMessage player2Points;
}
