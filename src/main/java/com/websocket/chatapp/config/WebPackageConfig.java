package com.websocket.chatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebPackageConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
        registry.addEndpoint("/ws").withSockJS();
    }


    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        return WebSocketMessageBrokerConfigurer.super.configureMessageConverters(messageConverters);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
