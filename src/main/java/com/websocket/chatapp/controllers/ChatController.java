package com.websocket.chatapp.controllers;


import com.websocket.chatapp.models.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMsg")
    @SendTo("/messages/group")
    public ChatMessage sendMsg(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addAcc")
    @SendTo("/messages/group")
    public ChatMessage addAcc(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){

        headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
        return chatMessage;
    }
}
