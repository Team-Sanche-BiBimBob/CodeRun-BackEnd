package com.sanchae.coderun.domain.arcade.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanchae.coderun.domain.arcade.entity.ArcadeRoom;
import com.sanchae.coderun.domain.arcade.entity.PvpArcadeRoomResult;
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

import java.time.LocalDateTime;
import java.util.HashMap;

import static java.lang.Long.parseLong;

@RequiredArgsConstructor
public class PvpArcadeWebSocketHandler extends AbstractWebSocketHandler {

    HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
    private final ArcadeRepository arcadeRepository;
    private final ObjectMapper objectMapper; // Jackson
    private final WebSocketQueryHandler webSocketQueryHandler;
    private final ObjectParser objectParser;

    LocalDateTime startTime;
    Long theId;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);

        startTime = LocalDateTime.now();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        JSONObject arcadeRoomRequestDto = objectParser.JsonToObjectParser(msg);

        Object player1Points = arcadeRoomRequestDto.get("player1Points");
        Object player2Points = arcadeRoomRequestDto.get("player2Points");

        for (WebSocketSession wss : sessionMap.values()) {
            try {
                wss.sendMessage(new TextMessage(arcadeRoomRequestDto.toJSONString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        theId = webSocketQueryHandler.findIdByQuery(session);

        ArcadeRoom arcadeRoom = arcadeRepository.findById(theId).orElse(null);

        if (arcadeRoom == null) return;

        PvpArcadeRoomResult pvpArcadeRoomResult = PvpArcadeRoomResult.builder()
                .arcadeRoom(arcadeRoom)
                .player1Points(parseLong(player1Points.toString()))
                .player2Points(parseLong(player2Points.toString()))
                .build();

        String json = objectMapper.writeValueAsString(pvpArcadeRoomResult);

        session.sendMessage(new TextMessage(json));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);

        ArcadeRoom arcadeRoom = arcadeRepository.findById(theId).orElseThrow(() -> new RuntimeException());
        ArcadeRoom savedArcadeRoom = arcadeRoom.toBuilder()
                .startTime(startTime)
                .build();
        arcadeRepository.save(savedArcadeRoom);
    }
}