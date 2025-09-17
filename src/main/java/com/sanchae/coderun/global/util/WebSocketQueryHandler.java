package com.sanchae.coderun.global.util;

import org.springframework.web.socket.WebSocketSession;


public class WebSocketQueryHandler {
    public Long findIdByQuery(WebSocketSession session) {

        String queryId = null;

        if (session.getUri() == null) { return null; }

        String query = session.getUri().getQuery();

        if (query != null) {
            for (String param : query.split("&")) {
                if (param.startsWith("id=")) {
                    queryId = param.substring(3);
                    break;
                }
            }
        }

        if (queryId == null) {
            return null;
        }

        return Long.parseLong(queryId);
    }

}
