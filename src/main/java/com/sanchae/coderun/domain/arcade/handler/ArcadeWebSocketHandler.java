package com.sanchae.coderun.domain.arcade.handler;

import com.sanchae.coderun.domain.arcade.entity.ArcadeRoom;
import com.sanchae.coderun.domain.arcade.repository.ArcadeRepository;
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

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

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

        if (arcadeRoom == null) {
            session.close();
            return;
        }

        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        JSONObject arcadeRoomRequestDto = JsonToObjectParser(msg);

        for(String key : sessionMap.keySet()) {
            WebSocketSession wss = sessionMap.get(key);
            try {
                wss.sendMessage(new TextMessage(arcadeRoomRequestDto.toJSONString()));
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
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
