package com.sanchae.coderun.domain.arcade.handler;

import com.sanchae.coderun.domain.arcade.entity.ArcadeRoom;
import com.sanchae.coderun.domain.arcade.entity.ArcadeRoomResult;
import com.sanchae.coderun.domain.arcade.repository.ArcadeRepository;
import com.sanchae.coderun.global.config.ModuleConfig;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.HashMap;

@RequiredArgsConstructor
public class ArcadeWebSocketHandler extends AbstractWebSocketHandler {

    HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
    private final ArcadeRepository arcadeRepository;
    private final ModuleConfig moduleConfig; // Jackson

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        JSONObject arcadeRoomRequestDto = JsonToObjectParser(msg);

        Object player1Points = arcadeRoomRequestDto.get("player1Points");
        Object player2Points = arcadeRoomRequestDto.get("player2Points"); // ← 오타 수정 (둘 다 player1Points였음)

        // 모든 세션에 브로드캐스트
        for (WebSocketSession wss : sessionMap.values()) {
            try {
                wss.sendMessage(new TextMessage(arcadeRoomRequestDto.toJSONString()));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        if (session.getUri() == null) { return; }

        String query = session.getUri().getQuery();
        String id = null;

        if (query != null) {
            for (String param : query.split("&")) {
                if (param.startsWith("id=")) {
                    id = param.substring(3);
                    break;
                }
            }
        }

        if (id == null) { return; }

        ArcadeRoom arcadeRoom = arcadeRepository.findById(Long.parseLong(id)).orElse(null);

        if (arcadeRoom == null) return;

        // 결과 DTO 생성
        ArcadeRoomResult arcadeRoomResult = ArcadeRoomResult.builder()
                .arcadeRoom(arcadeRoom)
                .player1Points(Long.parseLong(player1Points.toString()))
                .player2Points(Long.parseLong(player2Points.toString()))
                .build();

        // DTO → JSON 변환
        String json = moduleConfig.objectMapper().writeValueAsString(arcadeRoomResult);

        // JSON 전송
        session.sendMessage(new TextMessage(json));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }

    private static JSONObject JsonToObjectParser(String jsonStr) {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(jsonStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
