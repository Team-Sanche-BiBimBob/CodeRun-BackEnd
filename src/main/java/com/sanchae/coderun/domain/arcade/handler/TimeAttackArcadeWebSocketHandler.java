package com.sanchae.coderun.domain.arcade.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanchae.coderun.domain.arcade.entity.ArcadeRoom;
import com.sanchae.coderun.domain.arcade.entity.TimeAttackArcadeRoomResult;
import com.sanchae.coderun.domain.arcade.repository.ArcadeRepository;
import com.sanchae.coderun.global.config.ModuleConfig;
import com.sanchae.coderun.global.util.ObjectParser;
import com.sanchae.coderun.global.util.WebSocketQueryHandler;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.time.Duration;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class TimeAttackArcadeWebSocketHandler extends AbstractWebSocketHandler {

    private final ArcadeRepository arcadeRepository;
    private final WebSocketQueryHandler webSocketQueryHandler;
    private final ObjectParser objectParser;
    private final ObjectMapper objectMapper;

    Long theId;
    Object finalPoints;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);

        theId = webSocketQueryHandler.findIdByQuery(session);

        ArcadeRoom arcadeRoom = arcadeRepository.findById(theId).orElse(null);

        ArcadeRoom newArcadeRoom = arcadeRoom.toBuilder()
                .startTime(LocalDateTime.now())
                .build();

        arcadeRepository.save(newArcadeRoom);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);

        String msg = message.getPayload();
        JSONObject jsonObject = objectParser.JsonToObjectParser(msg);

        Object finishingObject = jsonObject.get("finalPoints");

        if (finishingObject != null) {
            finalPoints = finishingObject;

            ArcadeRoom arcadeRoom = arcadeRepository.findById(theId).orElse(null);

            ArcadeRoom savedArcadeRoom = arcadeRoom.toBuilder()
                    .endTime(LocalDateTime.now())
                    .build();

            arcadeRepository.save(savedArcadeRoom);

            Duration timeAttackResultTime = Duration.between(savedArcadeRoom.getStartTime(), savedArcadeRoom.getEndTime());

            TimeAttackArcadeRoomResult result = TimeAttackArcadeRoomResult.builder()
                    .arcadeRoom(arcadeRoom)
                    .player1Points(Long.parseLong(finalPoints.toString()))
                    .timeAttackResultTime(Math.abs(timeAttackResultTime.getSeconds()))
                    .build();

            String json = objectMapper.writeValueAsString(result);

            session.sendMessage(new TextMessage(json));

            session.close(CloseStatus.NORMAL);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}
