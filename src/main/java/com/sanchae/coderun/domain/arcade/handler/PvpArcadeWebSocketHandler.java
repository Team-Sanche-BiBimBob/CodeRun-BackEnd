package com.sanchae.coderun.domain.arcade.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanchae.coderun.domain.arcade.entity.ArcadeRoom;
import com.sanchae.coderun.domain.arcade.entity.PvpArcadeRoomResult;
import com.sanchae.coderun.domain.arcade.repository.ArcadeRepository;
import com.sanchae.coderun.global.util.ObjectParser;
import com.sanchae.coderun.global.util.WebSocketQueryHandler;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Long.parseLong;

@RequiredArgsConstructor
public class PvpArcadeWebSocketHandler extends AbstractWebSocketHandler {

    // 세션 관리
    private final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
    private final Map<WebSocketSession, Long> sessionRoomMap = new ConcurrentHashMap<>();
    private final Map<WebSocketSession, Long> sessionPlayerIdMap = new ConcurrentHashMap<>();
    private final Map<WebSocketSession, LocalDateTime> sessionStartMap = new ConcurrentHashMap<>();

    private final ArcadeRepository arcadeRepository;
    private final ObjectMapper objectMapper;
    private final WebSocketQueryHandler webSocketQueryHandler;
    private final ObjectParser objectParser;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionMap.put(session.getId(), session);
        sessionStartMap.put(session, LocalDateTime.now());

        // 세션의 Player ID 조회
        Long playerId = webSocketQueryHandler.findIdByQuery(session);
        sessionPlayerIdMap.put(session, playerId);

        // Player ID로 ArcadeRoom ID 조회
        Long roomId = findRoomIdByPlayerId(playerId);
        sessionRoomMap.put(session, roomId);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        JSONObject arcadeRoomRequestDto = objectParser.JsonToObjectParser(msg);

        // 세션별 Player ID
        Long playerId = sessionPlayerIdMap.get(session);
        arcadeRoomRequestDto.put("playerId", playerId);

        // 브로드캐스트: 같은 룸의 다른 세션에만 전송
        Long roomId = sessionRoomMap.get(session);
        for (WebSocketSession wss : sessionMap.values()) {
            if (roomId.equals(sessionRoomMap.get(wss))) {
                try {
                    wss.sendMessage(new TextMessage(arcadeRoomRequestDto.toJSONString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // 점수 저장
        ArcadeRoom arcadeRoom = arcadeRepository.findById(roomId).orElse(null);
        if (arcadeRoom == null) return;

        Object player1Points = arcadeRoomRequestDto.get("player1Points");
        Object player2Points = arcadeRoomRequestDto.get("player2Points");

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
        Long roomId = sessionRoomMap.get(session);
        LocalDateTime startTime = sessionStartMap.get(session);

        if (roomId != null) {
            ArcadeRoom arcadeRoom = arcadeRepository.findById(roomId)
                    .orElseThrow(() -> new RuntimeException("ArcadeRoom not found"));
            arcadeRoom = arcadeRoom.toBuilder()
                    .startTime(startTime)
                    .build();
            arcadeRepository.save(arcadeRoom);
        }

        // 세션 제거
        sessionMap.remove(session.getId());
        sessionStartMap.remove(session);
        sessionRoomMap.remove(session);
        sessionPlayerIdMap.remove(session);
    }

    /**
     * Player ID로 ArcadeRoom ID 조회
     */
    private Long findRoomIdByPlayerId(Long playerId) {
        // player1 또는 player2로 매칭되는 방 조회
        Optional<ArcadeRoom> roomOpt = arcadeRepository.findArcadeRoomByPlayer1_Id(playerId);
        if (roomOpt.isEmpty()) {
            roomOpt = arcadeRepository.findArcadeRoomByPlayer2_Id(playerId);
        }

        return roomOpt.map(ArcadeRoom::getId).orElse(null); // 람다로 안전하게 getId 호출
    }

}
