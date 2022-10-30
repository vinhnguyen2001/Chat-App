package com.websocket.chatapp.config;

import com.websocket.chatapp.models.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event){
        logger.info("Receive a new socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectionListener(SessionConnectedEvent event){

        StompHeaderAccessor headerAccessor  = StompHeaderAccessor.wrap(event.getMessage());

        String username= (String) headerAccessor.getSessionAttributes().get("username");

        if(username != null){
            logger.info("User has left: "+ username);
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(ChatMessage.MessageType.LEAVE);

            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
